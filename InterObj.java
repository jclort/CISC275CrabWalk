import java.util.Random;

abstract class InterObj extends Crawler {
	Random rand = new Random();
	int name;
	int randnum;
	protected boolean collisionBool = false;

    
    
	public InterObj(int frameSize, int name){
		randnum = rand.nextInt(frameSize-700);
		
    	this.setYLoc(randnum+200);
    	this.setXLoc(frameSize);
    	this.name = name;
	}
	public boolean getCollisionBool() {
		return this.collisionBool;
	}
	
	public abstract void onCollision(Crab crab);

	public abstract int getIncr();

}
