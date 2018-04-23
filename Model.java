import java.util.ArrayList;



public class Model{
    // This is where all of our logic is going to go for the game
    // This will also be where our game state is handled
    private int objXIncr; // All objects will only move left, not up or down
    static int left; // Again, object is only moving left
    int frameWidth;
    private final int NORTHSTEP = 10;
    private final int SOUTHSTEP = -10;
    private final int EASTSTEP = -10;
    private final int WESTSTEP = 10;
    int frameHeight;
    int imgSize;
    ArrayList<InterObj> stuff; // all of the objects with their methods and properties
    Crab player;               // the player with all his methods and properties

    public Model(int frameWidth, int frameHeight, int imgSize){
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.imgSize = imgSize;
        stuff = new ArrayList<InterObj>();
        player = new Crab(frameWidth);
    }

    public boolean crash(InterObj object){ // tells whether the player has hit this object
        return (player.getXLoc() == object.getXLoc() && player.getYLoc() == object.getYLoc());
    }

    public void handleCollisions(ArrayList<InterObj> objects){
        // checks each InterObj whether the player has hit it
        // if it has, it calls the object's onCollision method
        // This method largely depends on other collision methods already being created
        for(InterObj o : objects){
            if(crash(o)){
                o.onCollision(player);
            }
        }
    }

    public void update(Direction dir){
        // moves the player in the specified direction
        // deals with collisions
        // moves the InterObjs
        // deals with collisions again
        // Will Be Called From Controller With The Dir From View

        switch(dir){
        case NORTH: player.setYIncr(NORTHSTEP); break;
        case SOUTH: player.setYIncr(SOUTHSTEP); break;
        case EAST: player.setXIncr(EASTSTEP); break;
        case WEST: player.setXIncr(WESTSTEP); break;
        }
        player.move();
        handleCollisions(stuff);
        for(InterObj o : stuff){
            o.move();
        }
        handleCollisions(stuff);

        /* The following is pseudocode that will be implemented tomorrow for this method

           First thing that should be done is a check to see the directions that we are going
           to go in. The result of each case will set the xIncr and the yIncr to either
           positive or negative numbers. Afterwards, the crab's location will increment
           by xIncr, and yIncr. We will then check and see if a collision has occurred, and
           handle it accordingly. Once it is dealt with, we will then increment the
           Interactive Objects locations, and check for collisions, and handle them accordingly*/
    }


}
