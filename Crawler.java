abstract class Crawler{
	int xloc;
	int yloc;
	int xIncr;
	int yIncr;

	public void setX(int newX) {
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
	public void move() {	}





	public void onCollision(Crab crab, InterObj interObj) {
		if (interObj.getName() == "trash") {
			crab.disappear();
			crab.removeLife();
			interObj.disappear();
			//Code to make the crab disappear
			//Code to remove one life from crab
			//Code to make trash disappear
		}
		else if (interObj.getName() == "invasive") {
			crab.addScore();
			interObj.disappear();
			//Code to add score to crab
			//Code to make invasive disappear
		}
		else if (interObj.getName() == "power up") {
			interObj.disappear();
			crab.powerUp(true);
			//Code to make power up disappear
			//Code to change the abilities of the crab
			//code to handle the shift to power up and back from power up
		}
		else if (interObj.getName() == "native") {
			crab.subScore();
			interObj.disappear();
			//Code to remove score from crab
			//Code to make native disappear
		}
	}

}
