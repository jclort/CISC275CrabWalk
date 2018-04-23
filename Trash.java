import java.util.Random;

public class Trash extends InterObj {
	Random rand = new Random();
	private int max = 3;
	final private int GLOBAL = 7;
	int ctr = 0;
	
    	public Trash(int frameSize){
        	this.setYLoc(rand.nextInt()%frameSize);
        	this.setXLoc(frameSize);
    	}

	public void onCollision(Crab crab) {
		this.collisionBool = true;
	}

	public void move() {
		this.setXLoc(this.getXLoc() + getIncr());
	}


	public int getIncr() {
		return rand.nextInt(max + 1 + max) - max - GLOBAL;
	}
}

