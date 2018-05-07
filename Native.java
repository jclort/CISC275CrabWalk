import java.util.Random;

public class Native extends InterObj {
	/**
	 * This is the constructor for the Native class
	 * @param frameSize The size of the frame that is in play
	 * @param name The type of interactive object that it will be. It will likely be "Native" for this one
	 */
	public Native(int frameSize, int name) {
		super(frameSize, name);
		// TODO Auto-generated constructor stub
	}

	Random rand = new Random();
	private int max = 3;
	final private int GLOBAL = 5;
	
	/**
	 * This handles the collision logic for the Native object
	 * @param crab This is the player object
	 */
	public void onCollision(Crab crab) {
      this.collisionBool = (this.getXLoc() == crab.getXLoc() && this.getYLoc() == crab.getYLoc());
	}
	
	/**
	 * This handles the move logic for the native object. It changes both X and Y location by the incrementor
	 */
	public void move() {
		this.setXLoc(this.getXLoc() + getIncr());
		this.setYLoc(this.getYLoc() + getIncr());
	}
	/**
	 * This returns the incrementor of the native object
	 * @return The incrementor of the native object
	 */
	public int getIncr() {
		return rand.nextInt(max + 1 + max) - max - GLOBAL;
	}	
}
