import java.util.Random;

public class Trash extends InterObj {
	Random rand = new Random();
	private int max = 1;
	final private int GLOBAL = 3;
	final private int INCR = rand.nextInt(max + 1 + max) - max - GLOBAL;


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
		this.setXLoc(this.getXLoc() + INCR);
		this.setYLoc(this.getYLoc() + INCR);
	}

	public int getIncr() {
		return rand.nextInt(max + 1 + max) - max - GLOBAL;
	}
	
	public int getRIGHT() {
		return this.INCR;
	}

	public static void main(String[] args) {
		Trash trash = new Trash();
		System.out.println(getIncr());
	}



}

