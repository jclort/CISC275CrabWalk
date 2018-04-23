import java.util.Random;

public class Trash extends InterObj {
	Random rand = new Random();
	private int max = 1;
	final private int GLOBAL = 3;

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

