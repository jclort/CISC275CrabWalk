
import java.awt.EventQueue; 
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;


class Controller{
	final int DRAW_DELAY = 75; //msec

    	private Model model;
    	private View view;
    	private Action drawAction;
    	private static Timer timer;
    	int timerCtr = 500;
    	
	public Controller(){
        	model = new Model(View.frameHeight, View.frameWidth, View.imgWidth);
        	view = new View(model.getPlayer(), model.getStuff(), timerCtr);
        	drawAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){	
				model.update(view.getPlayer());
				view.setTime(timerCtr);
				if (view.getMenuBoolean()) {
					view.drawLayeredPane(view.getMenuLayeredPane());
				} else if (view.getGameBoolean()) {
					view.drawLayeredPane(view.getGameLayeredPane());
				}
				if (timerCtr-- == 0) {
					timer.stop();
				}
                // something interacting with the view's startButton
			}
		};

	}
		public static void stop(){
			timer.stop();
		}
		public static void start(){
			timer.start();
		}
    	public static void main(String[] args) {
        	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            		public void run() {
                		Controller ctrllr = new Controller();
			        ctrllr.timer = new Timer(ctrllr.DRAW_DELAY, ctrllr.drawAction);
                		ctrllr.timer.start();
            		}
        	});
	}
}
