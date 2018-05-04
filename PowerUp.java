import java.util.Random;

public class PowerUp extends InterObj {
	Random rand = new Random();
	private int max = 3;
	final private int GLOBAL = 6;

	@Override
	public boolean equals(Object other){
		if (!(other instanceof PowerUp)){
			return false;
		}
		else{
			PowerUp o = (PowerUp)other;
			return super.equals(o);
		}
	}
	
	public void onCollision(Crab crab) {
		this.collisionBool = true;
	}
	
	public void move() {
		this.setXLoc(this.getXLoc() + getIncr());
		this.setYLoc(this.getYLoc() + getIncr());
	}

	public int getIncr() {
		return rand.nextInt(max + 1 + max) - max - GLOBAL;
	}
}

