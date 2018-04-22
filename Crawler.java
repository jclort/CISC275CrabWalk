abstract class Crawler{
	int xloc;
	int yloc;
	int xIncr;
	int yIncr;

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
	// move() will be implemented differently for InterObj and Crab
	public abstract void move();


}
