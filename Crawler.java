<<<<<<< HEAD
class Crawler{ // This is our movable dude
=======
abstract class Crawler{
	private int xloc;
	private int yloc;
	private int xIncr;
	private int yIncr;

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
	// move() will be implemented differently for InterObj and Crab
	public abstract void move();


<<<<<<< HEAD



//	public void onCollision(Crab crab, InterObj interObj) {
//		if (interObj.getName() == "trash") {
//			crab.disappear();
//			crab.removeLife();
//			//interObj.disappear();
//			//Code to make the crab disappear
//			//Code to remove one life from crab
//			//Code to make trash disappear
//		}
//		else if (interObj.getName() == "invasive") {
//			crab.addScore();
//			//interObj.disappear();
//			//Code to add score to crab
//			//Code to make invasive disappear
//		}
//		else if (interObj.getName() == "power up") {
//			//interObj.disappear();
//			crab.powerUp(true);
//			//Code to make power up disappear
//			//Code to change the abilities of the crab
//			//code to handle the shift to power up and back from power up
//		}
//		else if (interObj.getName() == "native") {
//			crab.subScore();
//			//interObj.disappear();
//			//Code to remove score from crab
//			//Code to make native disappear
//		}
//	}
//
>>>>>>> 2ce471109ff2313b0a42d57c6dc8f792c5d373c6
=======
>>>>>>> b767b367e0739e28edf13b1b113b1d4b106afef2
}
