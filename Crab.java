class Crab extends Crawler {
    private Direction dir;
	private boolean powerUp = false;
	private int score = 0;
	private int lives = 4;
    private int boundary;
	
	public Crab(int frameSize) {
		xIncr = 0;
		yIncr = 0;
        boundary = frameSize/2;
		this.setYLoc(boundary);
		this.setXLoc(boundary);
        dir = Direction.STILL;
	}

	public void move() {
        if (xloc <= boundary-xIncr){
		    yloc += yIncr;
		    xloc += xIncr;
        }
	}

	public void addScore() {
		score += 2;
	}

	public void subScore() {
		score -= 1;
	}

	public void removeLife() {
		lives -= 1;
	}

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
			xIncr += 10;
			yIncr += 10;
		} else {
			xIncr -= 10;
			yIncr -= 10;
		}
		powerUp = enable;
	}

}
