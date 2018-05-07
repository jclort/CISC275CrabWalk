
abstract class Crawler{
	protected int xloc;
	protected int yloc;
	protected int xIncr;
	protected int yIncr;

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
	
	public int getXIncr() {
		return xIncr;
	}
	public int getYIncr() {
		return yIncr;
	}
	
	// move() will be implemented differently for InterObj and Crab
	public abstract void move();
	
	@Override
	public boolean equals(Object other){
		if (!(other instanceof Crawler)){
			return false;
		}
		else{
			Crawler o = (Crawler)other;
			return (getXLoc() == o.getXLoc()) &&
					(getYLoc() == o.getYLoc()) &&
					(getXIncr() == o.getXIncr()) &&
					(getYIncr() == o.getYIncr());
		}
	}
}