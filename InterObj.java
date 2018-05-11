import java.io.Serializable;
import java.util.Random;
import java.awt.Rectangle;

public abstract class InterObj extends Crawler implements Serializable {

	//protected boolean collisionBool = false;
	Random rand = new Random();
	int name;
	int randnum;
	protected Rectangle hitBox;
    	/**
	 * This is the constructor for the Interactive Object
	 * @param frameSize This is the size of the playable frame
	 * @param name This will be the determinant of the type of object that the Interactive Object will be
	 */
	public InterObj(int frameSize, int name){
		randnum = rand.nextInt(frameSize-700);
    		this.setYLoc(randnum+200);
    		this.setXLoc(frameSize);
    		this.name = name;
	}
	/**
	 * This method returns the collisionBool for the InterObj(Interactive Object), which describes whether the Crawler has collided with a Crab.
	 * 
	 * @return boolean - the boolean value of collisionBool
	 **/
	/*public boolean getCollisionBool() {
		return this.collisionBool;
	}*/

	public Rectangle getHitBox(){
		return hitBox;
	}
	
	/**
	 * This method determines whether the InterObj has collided with a Crab or not and sets the value of collisionBool to true if it has collided with a Crab.
	 *
	 * @param crab The user in the game that may have collided with the InterObj
	 **/ 
	public abstract void onCollision(Crab crab);
	
	/**
	 * This method will return the random increment for each different InterObj and will consider the parameters of max and global integers for returning an integer between the range of (-max)-global to (+max)-global.  This ensures a global moveent of the InterObj, however, encourages local movement by varying its speed.
	 *
	 * @param max An integer that initally determines the range from -max to +max
	 * @param global An integer that ensures the InterObj is moving to the left for each and every time the move method is called.  Must be greater than max to ensure this global movement. 
	 *
	 * @return int - The random integer generated between the range desired as a result of the passed parameters.
	 **/
	public int getIncr(int max, int global) {
		return rand.nextInt(max + 1 + max) - max -global;
	}

}
