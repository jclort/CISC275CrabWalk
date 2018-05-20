import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import javax.imageio.ImageIO;


public class SavedGame implements Serializable {
   	int time;
   	transient ArrayList<InterObj> objList;
   	boolean powerUp;
   	int score;
   	int lives;
   	int xloc;
   	int yloc;
   	boolean ifquiz;
   	boolean ifwin;
   	//Quiz quiz;      
	public SavedGame(View view, int timerCtr) { //Serializing the parts of the game needed to save the game: the timer, object list, powerup, score, lives, player location and quiz/win booleans
   		this.time = timerCtr;
   		this.objList = view.getStuff();
   		this.powerUp = view.getPlayer().getPowerUp();
   		this.score = view.getPlayer().getTotalScore();
   		this.lives = view.getPlayer().getLives();
   		this.xloc = view.getPlayer().xloc;
   		this.yloc = view.getPlayer().yloc;
   		this.ifquiz = view.ifquiz;
   		this.ifwin = view.ifwin;
   		//this.quiz = View.quiz;
	}  
}
