class Crab extends Crawler {
	private boolean powerUp = false;
	private int score = 0;
	private int lives = 4;
	
	public Crab() {
		xIncr = 0;
		yIncr = 0;
		//@Jeffrey, go ahead and put getFrameSize() in view plz
		this.setYLoc(view.getFrameSize()/2);
		this.setXLoc(view.getFrameSize()/2);
	}

	public void move() {
		yloc += yIncr;
		xloc += xIncr;
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
