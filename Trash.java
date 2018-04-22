import java.util.Random;

public class Trash extends InterObj {
	Random rand = new Random();
	private int max = 1;
	final private int GLOBAL = 3;

	public boolean onCollision(Crab crab) {
		// this if statement needs to be more complex to handle if the images
		// touch, not just the corners being in the same window.
		if (crab.getXLoc() == this.getXLoc()) {
			return true;
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

