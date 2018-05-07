import java.io.Serializable;

public class Invasive extends InterObj implements Serializable {
	private int max = 7;
	private int global = 14;
	private int counter = 0;
	private int sign = 0;	

	public Invasive(int frameSize, int name) {         
		super(frameSize, name);         
		// TODO Auto-generated constructor stub
	}
	/**
	 * This method is implemented from InterObj, and when called, sets te value of collisionBool to true when the image outline of the passed Crab intersects with the image outline of the Invasive object.
	 **/
	public void onCollision(Crab crab) {
       		this.collisionBool = (this.getXLoc() == crab.getXLoc() && this.getYLoc() == crab.getYLoc());		
		Controller.stop();         
		View.quiztime();
	}


	@Override
	public boolean equals(Object other){
		if (!(other instanceof Invasive)){
			return false;
		}
		else{
			Invasive o = (Invasive)other;
			return super.equals(o);
		}
	}
	
	/**
	 * This method will change the location of the Invasive object by a random increment that ensures global movement to the left, however, provides a varying local speed produced by the getIncr() method.
	 **/
	public void move() {
		this.setXLoc(this.getXLoc() + getIncr(max, global));
		this.setYLoc(this.getYLoc() + getIncr(max, global));
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
