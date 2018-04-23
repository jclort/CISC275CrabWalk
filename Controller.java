
import java.awt.EventQueue; 
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.Timer;


class Controller{
    final int DRAW_DELAY = 75; //msec

    private Model model;
	private View view;
    private Action drawAction;
    private Timer timer;

    public Controller(){
        model = new Model();
		view = new View(model.getPlayer(), model.getStuff());
		drawAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				model.update();
				view.drawPanel();
                // something interacting with the view's startButton
			}
		};
	}
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            	public void run() {
                	Controller ctrllr = new Controller();
			        ctrllr.timer = new Timer(ctrllr.drawDelay, ctrllr.drawAction);
                	ctrllr.timer.start();
            	}
        });
	}
}