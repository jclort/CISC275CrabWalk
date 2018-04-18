abstract class InterObj extends Crawler {
	
	private String name;


	InterObj(String objName) {
		this.name = objName;
		//randomize the yIncr and xIncr and then also location of spawn
		//coming out of the right side screen
	}

	public String getName() {
		return this.name;
	}

	public void disappear() {
		//I think this is the same code that should go in either Crawler or the View
		//for both InterObj and Crab
	}


	


}
