public class Crab extends Crawler {
    	private Direction dir;
	private boolean powerUp = false;
	private int score = 0;
	private int lives = 4;
    	private int boundary;
    /**
     * Constructor for the Crab
     * @param frameSize The size of the frame the crab will be in
     * @return Nothing. It's just the constructor
     */
	public Crab(int frameSize) {
		xIncr = 0;
		yIncr = 0;
        	boundary = frameSize/2;
    this.setYLoc(boundary); // These two set the location to be the middle of the screen
		this.setXLoc(boundary);
        	dir = Direction.STILL;
	}
    /**
     * This will be responsible for moving the crab across the screen
     *@param None
     *@return None
     */
	public void move() {
        	if (xloc <= boundary-xIncr){
			yloc += yIncr;
			xloc += xIncr;
        	}
	}
    /**
       Meant for adding to the score of the game
     */
	public void addScore() {
		score += 2;
	}
    /**
       Subtracting from the score of the game
    */
	public void subScore() {
		score -= 1;
	}
    /**
       Takes a life away if your health runs out
     */
	public void removeLife() {
		lives -= 1;
	}
    /**
       Gets the total score of the game
       @return  The total score of the game
    */
	public int getTotalScore() {
		return score+(lives*5);
	}

    	public Direction getDir(){
        	return dir;
    	}

    	public void setDir(Direction newDir) {
		dir = newDir;
    	}

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
