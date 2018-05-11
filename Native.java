import java.io.Serializable;

public class Native extends InterObj implements Serializable {
	private int max = 3;
	private int global = 5;
	
	/**
	 * This is the constructor for the Native class
	 * @param frameSize The size of the frame that is in play
	 * @param name The type of interactive object that it will be. It will likely be "Native" for this one
	 */
	public Native(int frameSize, int name) {
		super(frameSize, name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This handles the collision logic for the Native object
	 * @param crab This is the player object
	 */
	public void onCollision(Crab crab) {
		this.collisionBool = (this.getXLoc() == crab.getXLoc() && this.getYLoc() == crab.getYLoc());
	}


	@Override
	public boolean equals(Object other){
		if (!(other instanceof Native)){
			return false;
		}
		else{
			Native o = (Native)other;
			return super.equals(o);
		}
	}
	
	/**
	 * This method will change the location of the Native object depending on the random increments returned by the getIncr method.
	 **/
	public void move() {
		this.setXLoc(this.getXLoc() + getIncr(max, global));
		this.setYLoc(this.getYLoc() + getIncr(max, global));
	}
	
	/**
	 * This returns the incrementor of the native object
	 * @return The incrementor of the native object
	 */
	public int getIncr() {
		return rand.nextInt(max + 1 + max) - max - global;
	}	
}
