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

	/**
	 * This method will return the increment in the x directon, which may be positive or negative.
	 *
	 *@return int - the value of the incrementor in the x direction.
	**/	 
	public int getXIncr() {
		return xIncr;
	}
	/**
	 * This method wll return the increment in the y direction, which may be positive or negative.
	 *
	 * @return int - the value of the incrementor in the y direction.
	 **/
	public int getYIncr() {
		return yIncr;
	}
	/**
	 * This method Overrides the Object.equals(Object) method, so that we can see if two objects are in the space.
	 * This is used only for testing.
	 *
	 * @param other The Object that will be compared to a Crawler.
	 * @return boolean - The boolean that will be returned if the Crawler and the Object other are indeed the same object or not.
	 **/	
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
