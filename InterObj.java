import java.io.Serializable;

public abstract class InterObj extends Crawler implements Serializable {

	protected boolean collisionBool = false;
	
	/**
	 * This method returns the collisionBool for the InterObj(Interactive Object), which describes whether the Crawlwer has collided with a Crab.
	 *
	 * @return boolean - the boolean value of collisionBool
	 **/
	public boolean getCollisionBool() {
		return this.collisionBool;
	}
	
	/**
	 * This method determines whether the InterObj has collided with a Crab or not and sets the value of collisionBool to true if it has collided with a Crab.
	 *
	 * @param crab The user in the game that may have collided with the InterObj
	 **/ 
	public abstract void onCollision(Crab crab);
	
	/**
	 * This method will return the random increment for each different InterObj and be implemented in their own class.
	 *
	 * @return int
	 **/
	public abstract int getIncr();

}
