import java.util.Random;

public class Invasive extends InterObj {
	Random rand = new Random();
	private int max = 3;
	final private int GLOBAL = 5;

	public boolean onCollision(Crab crab) {
		if (crab.getXLoc() == this.getXLoc()) {
			reurn true;
		}
		else {
			return false;
		}
	}

	public void move() {
		this.setXLoc(this.getXLoc() + getIncr());
		this.setYLoc(this.getYLoc() + getIncr());
	}
	public int getIncr() { 
		return rand.nextInt(max + 1 + max) - max - GLOBAL;
	}
	
}
