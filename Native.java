import java.io.Serializable;

public class Native extends InterObj implements Serializable {
	private int max = 3;
	private int global = 5;
	
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
		this.setXLoc(this.getXLoc() + getIncr(max, global));
		this.setYLoc(this.getYLoc() + getIncr(max, global));
	}
	
}
