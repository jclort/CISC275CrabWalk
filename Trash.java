import java.io.Serializable;

public class Trash extends InterObj implements Serializable {
	private int max = 3;
	private int global = 6;
	
	/**
	 * This constructor will create an instance of Trash with a random spawn from the right hand side moving left towards a Crab.
	 *
	 * @param frameSize This int will contribute to the randomization of the location along the right wall.
	 **/
	public Trash(int frameSize, int name){
		super(frameSize, name);
	}

	@Override
	public boolean equals(Object other){
		if (!(other instanceof Trash)){
			return false;
		}
		else{
			Trash o = (Trash)other;
			return super.equals(o);
		}
	}
	
	/** This method is implemented from InterObj and once called, will set the collisionBool value to true if the images of the Crab and the Trash collide.
	 *
	 * @param crab the Crab that will be passed to the method and determine if its outline intersects the outline of the InterObj.
	 **/
	public void onCollision(Crab crab) {
      		this.collisionBool = (this.getXLoc() == crab.getXLoc() && this.getYLoc() == crab.getYLoc());
		if(this.collisionBool){
			System.out.println("Crashed!");
		}

		Controller.stop();
		View.quiztime();
        	gone = true;
	}
	
	/**
	 * This method will change the increment of the location of the Trash by random, to ensure global movement to the left, however, there is some local movement as well, in terms of varying its speed.
	 *
	 **/	
	public void move() {
		this.setXLoc(this.getXLoc() + getIncr(max, global));
	}
}
