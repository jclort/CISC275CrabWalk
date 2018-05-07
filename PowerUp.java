import java.util.Random;

public class PowerUp extends InterObj {
	public PowerUp(int frameSize, int name) {
		super(frameSize, name);
		// TODO Auto-generated constructor stub
	}

	Random rand = new Random();
	private int max = 3;
	final private int GLOBAL = 6;


	public void onCollision(Crab crab) {
      this.collisionBool = (this.getXLoc() == crab.getXLoc() && this.getYLoc() == crab.getYLoc());
        }
	
	public void move() {
		this.setXLoc(this.getXLoc() + getIncr());
		this.setYLoc(this.getYLoc() + getIncr());
	}

	public int getIncr() {
		return rand.nextInt(max + 1 + max) - max - GLOBAL;
	}
}

