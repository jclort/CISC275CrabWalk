import java.io.Serializable;
import java.util.ArrayList;

public class SavedGame {
   	int time;
   	ArrayList<InterObj> objList;
   	Crab player;
   	boolean ifquiz;
   	//Quiz quiz;      
	public SavedGame(View view, int timerCtr) {
   		this.time = timerCtr;
   		this.objList = view.getStuff();
   		this.player = view.getPlayer();
   		this.ifquiz = view.ifquiz;
   		//this.quiz = View.quiz;
	}  
}
