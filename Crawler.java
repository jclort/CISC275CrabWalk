import java.awt.Rectangle;
public abstract class Crawler{
	protected int xloc;
	protected int yloc;
	protected int xIncr;
	protected int yIncr;
    	public boolean gone;

	/**
	 * This method will set the value of xloc
	 *
	 * @param newX The new value of xloc
	 * 
	 **/
	public void setXLoc(int newX) {
		xloc = newX;
	}
	/**
	 * This method will set the value of yloc
	 *
	 * @param newY The new value of yloc
	 *
	 **/
	public void setYLoc(int newY) {
		if (newY > 200){
		yloc = newY;
		}else{
			return;
		}
		
		//System.out.println(yloc);
	}
	/**
	 * This method will get the value of the Crawler's xloc
	 *
	 * @return xloc - The int x location of the Crawler.
	 * 
	 **/
	public int getXLoc() {
		return xloc;
	}
	/**
	 * This method will get the value of the Crawler's yloc
	 *
	 * @return yloc - The int y location of the Crawler.
	 * 
	 **/
	public int getYLoc() {
		return yloc;
		
	}
	/**
	 * This method will set the Incrementor of the y location of the Crawler
	 *
	 * @param newYIncr The increment of the y location of the Crawler.
	 * 	It can be negative or positive depending on the direction of the Crawler.
	 **/
	public void setYIncr(int newYIncr) {
		yIncr = newYIncr;
		
	}
	/**
	 * This method will set the Incrementor of the x location of the Crawler
	 *
	 * @param newXIncr The increment of the x location of the Crawler.
	 * 	It can be negative or positive depending on the Direction of the Crawler.
	 **/ 
	public void setXIncr(int newXIncr) {
		xIncr = newXIncr;
	}
	/**
	 * This method will move the Crawler and will be implemented in the lowest classes.
	 *
	 * @see Crab
	 * @see Trash
	 * @see Invasive
	 * @see Native
	 * @see PowerUp
	 **/
	public abstract void move();

	
	public int getXIncr() {
		return xIncr;
	}
	public int getYIncr() {
		return yIncr;
	}	
	@Override
	public boolean equals(Object other){
		if (!(other instanceof Crawler)){
			return false;
		}
		else{
			Crawler o = (Crawler)other;
			return (this.getXLoc() == o.getXLoc()) &&
				(this.getYLoc() == o.getYLoc()) && 
				(this.getXIncr() == o.getXIncr()) && 
				(this.getYIncr() == o.getYIncr());
		}
	}
}
