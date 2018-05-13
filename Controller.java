
import java.awt.EventQueue; 
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;
import java.io.Serializable;


public class Controller implements Serializable {

	final static int DRAW_DELAY = 75; //msec

    	private static Model model;
    	private static View view;
    	private static Action drawAction;
    	private static Timer timer;
    	static int timerCtr = 500;//2400 is 3 minutes
    	
	public Controller(){
        	model = new Model(View.frameHeight, View.frameWidth, View.imgWidth);
        	view = new View(model.getPlayer(), model.getStuff(), timerCtr);
        	
        	drawAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){	
				model.update(view.getPlayer());
				view.setTime(timerCtr);
				view.drawTopCard();
				if (timerCtr-- == 0) {
					Controller.stop();
					view.quizTime();
					view.ifwin = true;
				}
			}
		};
	}

	public int getTime() {
		return timerCtr;
	}
	public void setTime() {
		timerCtr = 500;//2400 is 3 minutes, every 800 is one minute with 75 msec draw delay
	}
	public static void stop(){
		timer.stop();
	}
	public static void start(){
		timer.start();
	}
	public static void restart(){
           	//View v = view;
        	model = new Model(View.frameHeight, View.frameWidth, View.imgWidth);
		timerCtr = 500;
               	view.remove(view.getCards());
        	view.setView(model.getPlayer(), model.getStuff(), timerCtr);
		timer.start();
	}
    	public static void main(String[] args) {
        	javax.swing.SwingUtilities.invokeLater(new Runnable() {
        		public void run() {
               			Controller ctrllr = new Controller();
               			timer = new Timer(DRAW_DELAY, drawAction);
               			//timer.start();
        		}
        	});
	}
}
