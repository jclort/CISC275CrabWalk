class Crab extends Crawler {
	private boolean powerUp = false;
	private int score = 0;
	private int lives = 4;
	
	public Crab() {
		xIncr = 0;
		yIncr = 0;
		yloc = view.getframeSize()/2;
		xloc = view.getframeSize()/2;
	}

	public void move() {
		yloc += yIncr;
		xloc += xIncr;
	}

	public int getYloc() {
		return yloc;
	}
	public int getXloc() {
		return xloc;
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

	public void powerUp(boolean enable) {
		if (enable) {
			xIncr += 10;
			yIncr += 10;
		} else {
			xIncr -= 10;
			yIncr -= 10;
		}
		powerUp = enable;
	}

	public void disappear() {
		//Should we have this code implemented in view, 
		//so that I can set the image visibility to false, 
		//reset the location of the crab, and make image visible again?
	}


		
}
