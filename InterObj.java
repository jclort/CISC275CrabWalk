import java.util.Random;

abstract class InterObj extends Crawler {
	Random rand = new Random();
	int name;
	int randnum;
	protected boolean collisionBool = false;

    
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
	 * This returns whether or not a collision has occurred
	 * @return A boolean that says whether or not a collision has occurred
	 */
	public boolean getCollisionBool() {
		return this.collisionBool;
	}
	
	public abstract void onCollision(Crab crab);

	public abstract int getIncr();

}
