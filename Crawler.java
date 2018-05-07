
abstract class Crawler{
	protected int xloc;
	protected int yloc;
	protected int xIncr;
	protected int yIncr;
    public boolean gone;

	public void setXLoc(int newX) {
		xloc = newX;
	}
	public void setYLoc(int newY) {
		if (newY > 200){
		yloc = newY;
		}else{
			return;
		}
		
		//System.out.println(yloc);
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