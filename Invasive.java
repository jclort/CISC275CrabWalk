import java.io.Serializable;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Invasive extends InterObj implements Serializable {
	private int max = 7;
	private int global = 14;
	private int counter = 0;
	private int sign = 0;	
	private Rectangle hitBox;
	private BufferedImage i;
	/**
	 * This is the Invasive constructor that will create an image attribute for the kind of invasive object after calling its parent constructor.
	 **/
	public Invasive(int frameSize, int name) {         
		super(frameSize, name);         
		switch(name){
			case 6 : i = Images.INVA1.getPic(); break;
			case 7 : i = Images.INVA2.getPic(); break;
		}
	}
	/**
	 * This method is implemented from InterObj, and when called, sets te value of collisionBool to true when the image outline of the passed Crab intersects with the image outline of the Invasive object.
	 **/
	public void onCollision(Crab crab) {
       		this.collisionBool = (this.getXLoc() == crab.getXLoc() && this.getYLoc() == crab.getYLoc());		
		//Controller.stop();         
		View.quizTime();
        crab.subScore();
        crab.removeLife();
	}
	/**
	 * This method calls the super.equals(Object) method, but this is only used for testing.  This tests to see if the Object other is indeed an Invasive Object, and if it isn't passes it to see if it is a crawler.
	 * @param other The Object that will be tested against the Invasive and Crawler Objects.
	 * @return boolean - The boolean to determine if it is Invasive and a Crawler, or neither.
	 **/
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
		this.setYLoc(this.getYLoc() + getIncr(20, 0));
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
