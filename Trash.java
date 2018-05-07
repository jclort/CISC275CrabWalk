import java.util.Random;

public class Trash extends InterObj {
	Random rand = new Random();
	private int max = 3;
	final private int GLOBAL = 6;
	int ctr = 0;
	
    	public Trash(int frameSize){
        	this.setYLoc(rand.nextInt()%frameSize);
        	this.setXLoc(frameSize);
    	}

	public void onCollision(Crab crab) {
      this.collisionBool = (this.getXLoc() == crab.getXLoc() && this.getYLoc() == crab.getYLoc());
	}

	public void move() {
		this.setXLoc(this.getXLoc() + getIncr());
	}


	public int getIncr() {
		return rand.nextInt(max + 1 + max) - max - GLOBAL;
	}
}

