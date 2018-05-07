
abstract class Crawler{
	protected int xloc;
	protected int yloc;
	protected int xIncr;
	protected int yIncr;
    public boolean gone;

	/**
	 * This changes the x location of the given Crawler object
	 * @param newX The new X location of the Crawler
	 */
	public void setXLoc(int newX) {
		xloc = newX;
	}
	/**
	 * This changes the y location of the given Crawler object
	 * @param newY The new Y location of the Crawler
	 */
	public void setYLoc(int newY) {
		if (newY > 200){
		yloc = newY;
		}else{
			return;
		}
		
		//System.out.println(yloc);
	}
	/**
	 * This tells the X location of the Crawler
	 * @return The x location of the Crawler
	 */
	public int getXLoc() {
		return xloc;
	}
	/**
	 * This tells the Y location of the Crawler
	 * @return the y location of the Crawler
	 */
	public int getYLoc() {
		return yloc;
		
	}
	/**
	 * This changes the speed at which the Y location changes
	 * @param newYIncr The new incrementor for Y
	 */
	public void setYIncr(int newYIncr) {
		yIncr = newYIncr;
		
	}
	/**
	 * This changes the speed at which the X location changes
	 * @param new XIncr The new incrementor for X
	 */
	public void setXIncr(int newXIncr) {
		xIncr = newXIncr;
	}
	// move() will be implemented differently for InterObj and Crab
	public abstract void move();
}