import java.util.ArrayList;

import java.util.Random;

import java.util.Iterator;


public class Model{
    	// This is where all of our logic is going to go for the game
    	// This will also be where our game state is handled
    	private int objXIncr; // All objects will only move left, not up or down
    	static int left; // Again, object is only moving left
    	int frameWidth;
    	private final int NORTHSTEP = -30;
    	private final int SOUTHSTEP = 30;
    	private final int EASTSTEP = 30;
    	private final int WESTSTEP = -30;
    	int frameHeight;
    	int imgSize;
    	private int noIncr = 0;
    	ArrayList<InterObj> stuff; // all of the objects with their methods and properties
    	Crab player;               // the player with all his methods and properties
    	int trashCtr = 0;
    	Random rand = new Random();
    	int randnum;
    	boolean crash;
    	

	
    	public Model(int frameWidth, int frameHeight, int imgSize){
        	this.frameWidth = frameWidth;
        	this.frameHeight = frameHeight;
        	this.imgSize = imgSize;
        	stuff = new ArrayList<InterObj>();
        	player = new Crab(frameWidth); 
    	}

    	public Crab getPlayer(){
        	return player;
    	}

    	public boolean getCrash(){
        	return crash;
    	}
    	public void setCrash(boolean y){
        	crash = y;
    	}
    	
    	public ArrayList<InterObj> getStuff(){
        	return this.stuff;
    	}

    	public boolean crash(InterObj object){ // tells whether the player has hit this object
        	
    		return (Math.abs(player.getXLoc()-object.getXLoc()) < 50 && (Math.abs(player.getYLoc()-object.getYLoc()) < 50));
    	}

    	public void handleCollisions(ArrayList<InterObj> objects){
        	// checks each InterObj whether the player has hit it
        	// if it has, it calls the object's onCollision method
        	// This method largely depends on other collision methods already being created

            Iterator it = stuff.iterator();
        	while(it.hasNext()){
                    InterObj o = (InterObj)it.next();
            		if(crash(o) & View.crashlesstime == 0 ){
                	    o.onCollision(player);
                	    if (o.gone){
                            it.remove();
                        }
            	    }
        	}
    	}

    	public void generateNewStuff(){
    		randnum = rand.nextInt(7);
    		switch (randnum) {
            case 0: 
            	stuff.add(new Trash(frameHeight,1));
            	break;
            case 1: 
            	stuff.add(new Trash(frameHeight,2));
            	break;
            case 2: 
            	stuff.add(new Trash(frameHeight,3));
            	break;
            case 3: 
            	stuff.add(new Trash(frameHeight,4));
            	break;
            case 4: 
            	if (rand.nextInt(10) > 6){
            	stuff.add(new Trash(frameHeight,5));
            	}
            	break;
            case 5: 
            	stuff.add(new Invasive(frameHeight,6));
            	break;
            case 6: 
            	stuff.add(new Invasive(frameHeight,7));
            	break;
    		}
        	
        	
    	}

    	public void update(Crab newPlayer){
        	// moves the player in the specified direction
        	// deals with collisions
        	// moves the InterObjs
        	// deals with collisions again
        	// Will Be Called From Controller With The Dir From View
		player = newPlayer;
        	switch(player.getDir()){
        	case NORTH:	 
			player.setYIncr(NORTHSTEP);
			player.setXIncr(noIncr);
		    	break;
       		case SOUTH: 
			player.setYIncr(SOUTHSTEP);
		    	player.setXIncr(noIncr);
		    	break;
        	case EAST:
			player.setXIncr(EASTSTEP);
		   	player.setYIncr(noIncr);
		   	break;
        	case WEST:
			player.setXIncr(WESTSTEP);
		   	player.setYIncr(noIncr);
		   	break;
        	default:
			player.setXIncr(noIncr);
                player.setYIncr(noIncr);
        	}
        	
        	
        	player.move();
        	handleCollisions(stuff);
        	
        	for(InterObj o : stuff){
            	o.move();
        	}
        	
        	handleCollisions(stuff);
        	
        	if (View.crashlesstime != 0){
        		View.crashlesstime --;
        	}
        	//System.out.println(crash);
        	
        	player.setDir(Direction.STILL);
        	
        	if (trashCtr++ %15 == 0) {
			generateNewStuff();
		}
        /* The following is pseudocode that will be implemented tomorrow for this method

           First thing that should be done is a check to see the directions that we are going
           to go in. The result of each case will set the xIncr and the yIncr to either
           positive or negative numbers. Afterwards, the crab's location will increment
           by xIncr, and yIncr. We will then check and see if a collision has occurred, and
           handle it accordingly. Once it is dealt with, we will then increment the
           Interactive Objects locations, and check for collisions, and handle them accordingly*/
    	}

}
