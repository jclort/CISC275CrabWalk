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
   	/**
	* Constructor for SavedGame. Saves all current data.
	* @param view The view that we are looking at
	* @param timerCtr The current counter for the timer
	*/
	
	public SavedGame(View view, int timerCtr) { 
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
