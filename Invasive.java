import java.util.Random;
import java.io.Serializable;

public class Invasive extends InterObj implements Serializable {
	Random rand = new Random();
	private int max = 3;
	final private int GLOBAL = 5;
	
	/**
	 * This method is implemented from InterObj, and when called, sets te value of collisionBool to true when the image outline of the passed Crab intersects with the image outline of the Invasive object.
	 **/
	public void onCollision(Crab crab) {
		this.collisionBool = true;
	}
	
	/**
	 * This method will change the location of the Invasive object by a random increment that ensures global movement to the left, however, provides a varying local speed produced by the getIncr() method.
	 **/
	public void move() {
		this.setXLoc(this.getXLoc() + getIncr());
		this.setYLoc(this.getYLoc() + getIncr());
	}

	/**
	 * This method will return a random negative integer that ranges between (-max)-GLOBAL to (+max)-GLOBAL.
	 *
	 * @return int - The random negative number that represents the increment of the location of the Invasive object.
	 **/
	public int getIncr() { 
		return rand.nextInt(max + 1 + max) - max - GLOBAL;
	}
	
}
