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

	/**
	 * This is the collision logic for the Invasive species object
	 * If a collision happens, then the player will be quizzed with a small trivia question
	 * @param crab This is the player object that we are determining if a collision happened with it
	 */
	public void onCollision(Crab crab) {
      this.collisionBool = (this.getXLoc() == crab.getXLoc() && this.getYLoc() == crab.getYLoc());

      //this.collisionBool = (this.getXLoc() == crab.getXLoc() && this.getYLoc() == crab.getYLoc());
		Controller.stop();
		View.quiztime();
	}
	/**
	 * This method is in charge of moving the invasive object around. It will change the X and Y locations of the invasive object
	 */
	public void move() {
		this.setXLoc(this.getXLoc() + getIncr());
		this.setYLoc(this.getYLoc() + getyIncr());
	}

	 /**
	  * This tells us how fast the invasive object is currently moving. It will change the incrementor at a random scale
	  @return The incrementor for how fast the invasive object is moving 
	  */
	public int getIncr() {
		return rand.nextInt(max + 1 + max) - max - GLOBAL;
	}
	/**
	 * This tells us how fast the y incrementor is moving
	 * @return The Y incrementor
	 */
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
