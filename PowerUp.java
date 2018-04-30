import java.util.Random;
import java.io.Serializable;

public class PowerUp extends InterObj implements Serializable {
	Random rand = new Random();
	private int max = 3;
	final private int GLOBAL = 6;

	/**
	 * This method is implemented here from the InterObj class and sets collisionBool to true if the image outline of the Crab passed into the method intersects the image outline of the PowerUp object.
	 **/
	public void onCollision(Crab crab) {
		this.collisionBool = true;
	}
	
	/**
	 * This method implements the abstract move method from InterObj and changes the location of the PowerUp object depending on the negative integer increment returned by the getIncr method.
	 **/
	public void move() {
		this.setXLoc(this.getXLoc() + getIncr());
		this.setYLoc(this.getYLoc() + getIncr());
	}

	/**
	 * This method implements the abstract getIncr method from InterObj and returns a negative integer that increments the location of the PowerUp object which ensures global movement to the left with varying local speeds that range from (-max)-GLOBAL to (+max)-(GLOBAL)
	 *
	 * @return int - The negative integer that will increment the position of the PowerUp object.
	 **/
	public int getIncr() {
		return rand.nextInt(max + 1 + max) - max - GLOBAL;
	}
}

