
abstract class Crawler{
	private int xloc;
	private int yloc;
	private int xIncr;
	private int yIncr;

	public void setXLoc(int newX) {
		xloc = newX;
	}
	public void setYLoc(int newY) {
		yloc = newY;
	}
	public int getXLoc() {
		return xloc;
	}
	public int getYLoc() {
		return yloc;
	}
	public void setYIncr(int newYIncr) {
		yIncr = newYIncr;
	}
	public void setXIncr(int newXIncr) {
		xIncr = newXIncr;
	}
	// move() will be implemented differently for InterObj and Crab
	public abstract void move();
}