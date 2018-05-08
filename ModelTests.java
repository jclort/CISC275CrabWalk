import static org.junit.Assert.*;

import org.junit.Test;

public class ModelTests {

	@Test
	public void basic_tests() {
		assertEquals(new Model(550, 550, 55).getPlayer(), new Crab(550));
		assertEquals(new Model(600, 300, 35).getStuff().size(), 0);
	}
	
	@Test
	public void constructor_test(){
		Model m1 = new Model(600, 300, 35);
		Model m2 = new Model(600, 300, 35);
		assertEquals(m1, m2);
	}
	
	@Test
	public void crash_test(){
		Model m1 = new Model(800, 800, 35);
		Trash t1 = new Trash(m1.getFrameWidth(), 2);
		assertFalse(m1.crash(t1));
		t1.setXLoc(m1.getFrameWidth()/2);
		t1.setYLoc(m1.getFrameWidth()/2);
		t1.setXIncr(0);
		t1.setYIncr(0);
		m1.update(m1.getPlayer());
		assertTrue(m1.crash(t1));
	}
	
	@Test
	public void update_test(){
		Model m1 = new Model(1000, 800, 70);
		Model m2 = new Model(1000, 800, 70);
		m1.getPlayer().setDir(Direction.WEST);
		m2.getPlayer().setDir(Direction.WEST);
		m1.update(m1.getPlayer());
		m2.update(m2.getPlayer());
		assertTrue(m1.getPlayer().equals(m2.getPlayer()));
		m1.getPlayer().setDir(Direction.SOUTH);
		m2.getPlayer().setDir(Direction.SOUTH);
		m1.update(m1.getPlayer());
		m2.update(m2.getPlayer());
		assertTrue(m1.getPlayer().equals(m2.getPlayer()));
		m1.getPlayer().setDir(Direction.EAST);
		m2.getPlayer().setDir(Direction.EAST);
		m1.update(m1.getPlayer());
		m2.update(m2.getPlayer());
		assertTrue(m1.getPlayer().equals(m2.getPlayer()));
		m1.getPlayer().setDir(Direction.NORTH);
		m2.getPlayer().setDir(Direction.NORTH);
		m1.update(m1.getPlayer());
		m2.update(m2.getPlayer());
		assertTrue(m1.getPlayer().equals(m2.getPlayer()));
		for (int i = 100; i > 0; i--){
			m1.update(m1.getPlayer());
			m2.update(m2.getPlayer());
			assertTrue(m1.getPlayer().equals(m2.getPlayer()));
		}
	}
}
