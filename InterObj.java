abstract class InterObj extends Crawler {

	private boolean collisionBool = false;

	public boolean getCollisionBool() {
		return this.collisionBool;
	}
	
	public abstract void onCollision(Crab crab);

	public abstract int getIncr();

}