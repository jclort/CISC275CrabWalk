public class Model{
    // This is where all of our logic is going to go for the game
    // This will also be where our game state is handled
    final static int INCR = 10; // This is going to be the incrementor for the Crab
    final static int STOP = 0; // This is for if we want something to stop moving
    private int objXIncr; // All objects will only move left, not up or down
    int picNum;
    int picDir;
    static int left; // Again, object is only moving left
    int frameWidth;
    int frameHeight;
    int imgWidth;
    int imgHeight;

    public Model(int frameWidth, int frameHeight, int imgWidth, int imgHeight){
        left = objXIncr;
        picNum = 0;
        picDir = 0;
          }

}
