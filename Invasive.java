import java.io.Serializable;

public class Invasive extends InterObj implements Serializable {
	private int max = 3;
	private int global = 5;
	
	/**
	 * This method is implemented from InterObj, and when called, sets te value of collisionBool to true when the image outline of the passed Crab intersects with the image outline of the Invasive object.
	 **/
	public void onCollision(Crab crab) {
		this.collisionBool = true;
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

}
