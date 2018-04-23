import java.util.Random;

public class Invasive extends InterObj {
	Random rand = new Random();
	private int max = 3;
	final private int GLOBAL = 5;

	public void onCollision(Crab crab) {
		this.collisionBool = true;
	}

	public void move() {
		this.setXLoc(this.getXLoc() + getIncr());
		this.setYLoc(this.getYLoc() + getIncr());
	}
	public int getIncr() { 
		return rand.nextInt(max + 1 + max) - max - GLOBAL;
	}
	
}
