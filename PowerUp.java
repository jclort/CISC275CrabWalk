import java.io.Serializable;

public class PowerUp extends InterObj implements Serializable {
	private int max = 3;
	private int global = 6;

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
		this.setXLoc(this.getXLoc() + getIncr(max, global));
		this.setYLoc(this.getYLoc() + getIncr(max, global));
	}
}

