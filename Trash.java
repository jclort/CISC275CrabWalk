

public class Trash extends InterObj {
	
	public Trash(int frameSize, int name) {
		super(frameSize, name);
		// TODO Auto-generated constructor stub
	}


	private int max = 3;
	final private int GLOBAL = 6;
	int ctr = 0;
	


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

