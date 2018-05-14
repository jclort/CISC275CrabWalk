import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.Serializable;

public class Crab extends Crawler implements Serializable {
    	private Direction dir;
	private boolean powerUp = false;
	private int score = 0;
	private int lives = 4;
	private int boundary; // This is just how far the crab is able to go
	private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	//private transient BufferedImage crab = Images.CRAB.getPic();
    /**
     * Constructor for the Crab
     * @param frameSize The size of the frame the crab will be in
     * @return Nothing. It's just the constructor
     */
	public Crab(int frameSize) {
		xIncr = 0;
		yIncr = 0;

   		this.setYLoc(300); // These two set the location to be the middle of the screen
		this.setXLoc(300);
        	dir = Direction.STILL;
		boundary = frameSize;
	}
	/**
	 * This method will return a boolean to determine whether a powerup has been activated or not.
	 *
	 * @return boolean - The boolean value for whether the powerup has been activated.
	 **/	
	public boolean getPowerUp(){
		return this.powerUp;
	}

	public Integer getLives(){
 		return (Integer)lives;
 	}

    
	@Override
	public boolean equals(Object other){
		if (!(other instanceof Crab)){
			return false;
		}
		else{
			Crab o = (Crab)other;
			return super.equals(o) &&
					(getDir() == o.getDir()) &&
					(getPowerUp() == o.getPowerUp()) &&
					(getTotalScore() == o.getTotalScore());
		}
	}

	/**
     * This will be responsible for moving the crab across the screen
     *@param None
     *@return None
     */
	
	public void move() {
        	if ((xloc <= screenWidth-xIncr) & (xloc >= 0-xIncr) & (yloc >= 200-yIncr) & (yloc <= screenHeight-yIncr)){
			this.setYLoc(this.getYLoc()+yIncr);
			this.setXLoc(this.getXLoc()+xIncr);
		}
		
	}
    /**
      * Meant for adding to the score of the game
     */
	public void addScore() {
		score += 3;
	}
    /**
      * Subtracting from the score of the game
    */
	public void subScore() {
		if (score > 0) {
			score -= 1;
		}
	}

    /**
      * Takes a life away if your health runs out
     */
	public void removeLife() {
		lives -= 1;
	}
    /**
      * Gets the total score of the game
      * @return  The total score of the game
    */
	public int getTotalScore() {
		return score;
	}
	
	
	public void setLives(int lives) {
		this.lives = lives;
	}
		/**
		 * This returns the direction the player is currently going in
		 * @return The direction the crab is going in
		 */
    	public Direction getDir(){
        	return dir;
    	}
		/**
		 * This changes the direction that the player is going in
		 * @param newDir This is the new direction that the crab will be going in
		 */
    	public void setDir(Direction newDir) {
		dir = newDir;
    	}
	/**
	 * This changes the power up
	 * @param enable This is whether or not the powerup has been enabled
	 */
	public void setPowerUp(boolean enable) {
		if (enable) {
			xIncr += 20;
			yIncr += 20;
		} else {
			xIncr -= 20;
			yIncr -= 20;
		}
		powerUp = enable;
	}

}
