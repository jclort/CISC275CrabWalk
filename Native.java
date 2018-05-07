import java.io.Serializable;

public class Native extends InterObj implements Serializable {
	private int max = 3;
	private int global = 5;
	
	public Native(int frameSize, int name) {
		super(frameSize, name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method implements onCollision method from InterObj that will set the collisionBool to true when the image outline of the crab intersects the image outline of the Native Object.
	 **/
	public void onCollision(Crab crab) {
		this.collisionBool = (this.getXLoc() == crab.getXLoc() && this.getYLoc() == crab.getYLoc());
	}


	@Override
	public boolean equals(Object other){
		if (!(other instanceof Native)){
			return false;
		}
		else{
			Native o = (Native)other;
			return super.equals(o);
		}
	}
	
	/**
	 * This method will change the location of the Native object depending on the random increments returned by the getIncr method.
	 **/
	public void move() {
		this.setXLoc(this.getXLoc() + getIncr(max, global));
		this.setYLoc(this.getYLoc() + getIncr(max, global));
	}
	
}
