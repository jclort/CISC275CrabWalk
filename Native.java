import java.util.Random;
import java.io.Serializable;

public class Native extends InterObj implements Serializable {
	Random rand = new Random();
	private int max = 3;
	final private int GLOBAL = 5;
	
	/**
	 * This method implements onCollision method from InterObj that will set the collisionBool to true when the image outline of the crab intersects the image outline of the Native Object.
	 **/	
	public void onCollision(Crab crab) {
		this.collisionBool = true;
	}
	
	/**
	 * This method will change the location of the Native object depending on the random increments returned by the getIncr method.
	 **/
	public void move() {
		this.setXLoc(this.getXLoc() + getIncr());
		this.setYLoc(this.getYLoc() + getIncr());
	}
	
	/**
	 * This method returns a negative integer that ensures global movement to the left, but with varying local speeds that range from (-max)-GLOBAL to (+max)-GLOBAL.
	 *
	 * @return int - The random negative integer that will change the location of the Native object.
	 **/
	public int getIncr() {
		return rand.nextInt(max + 1 + max) - max - GLOBAL;
	}	
}
