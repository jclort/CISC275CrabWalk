import java.util.Random;

public class Invasive extends InterObj {
	public Invasive(int frameSize, int name) {
		super(frameSize, name);
		// TODO Auto-generated constructor stub
	}
	
	private int max = 7;
	final private int GLOBAL = 14;
	int ctr = 0;
	private int counter = 0;
	private int sign = 0; 

	public void onCollision(Crab crab) {
      this.collisionBool = (this.getXLoc() == crab.getXLoc() && this.getYLoc() == crab.getYLoc());
	}

	public void move() {
		this.setXLoc(this.getXLoc() + getIncr());
		this.setYLoc(this.getYLoc() + getyIncr());
	}


	public int getIncr() {
		return rand.nextInt(max + 1 + max) - max - GLOBAL;
	}
	public int getyIncr() {
		
		if (sign == 0 & counter > -10){
			counter = counter -1;
			
		}else{
			sign = 1;
		}
			
	    if(sign == 1 & counter < 10){
		
			counter = counter +1;
		}else{
			sign = 0;
		}
	    
		return counter;
	}
	
}
