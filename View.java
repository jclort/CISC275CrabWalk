
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class View extends JFrame{
    private int score;
    private int lives;
    private Crab player;
    private ArrayList<InterObj> stuff;
    private DrawPanel drawPanel;
    private StartButton startButton;
    private BufferedImage crabPic;

    final static int frameStartSize = 800;
    final static int frameWidth = 800;//500
   	final static int frameHeight = 800;//300
    final static int imgWidth = 165;//165
	final static int imgHeight = 165;

    
    public View(Crab p, ArrayList<InterObj> s){    
        drawPanel = new DrawPanel();
        score = 0;
        lives = 3;
        player = p;
        stuff = s;
	startButton = new StartButton();
        add(drawPanel);
        crabPic = createImage("images/crab.png");
        setBackground(Color.gray);                 
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                 
	    setSize(getWidth(), getHeight());
	    setVisible(true);
        pack();	
    }

    public DrawPanel getDrawPanel(){
        return drawPanel;
    }
    
    public static int getFrameSize() {
    	return frameStartSize;
    }

    public int getScore(){
        return score;
    }

    public int getLives(){
        return lives;
    }

    public ArrayList<InterObj> getStuff(){
        return stuff;
    }

    public void drawPanel() {
		drawPanel.repaint();
	}
    public void paint(Graphics g) {
	    g.drawImage(crabPic, player.getXLoc(), player.getYLoc(), Color.gray, this);
    }

    @SuppressWarnings("serial")
	private class DrawPanel extends JPanel {

        public DrawPanel(){
            	super();
            	setFocusable(true);
            	addKeyListener(new KeyAdapter(){
                	@Override
                	public void keyPressed(KeyEvent e){
                    		if (e.getKeyCode() == KeyEvent.VK_UP){
                        		//crab direction is up
                    		}
					        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
						        //crab direction is down
					        }
					        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
						        //crab direction is right
					        }
					        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
						        //crab direction is left
					        }
                	}
            	});
        }

        @Override
        protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(new Color(0, 0, 0, 0));
            drawCrab(g);
            drawInterObjs(g);
		}

        @Override
		public Dimension getPreferredSize() {
			return new Dimension(frameStartSize, frameStartSize);
		}

        public void drawCrab(Graphics g){
            g.drawImage(crabPic, player.getXLoc(), player.getYLoc(), new Color(0, 0, 0, 0), this);
            // System.out.println("Crab appears!");    //STUB
        }

        public void drawInterObjs(Graphics g){
            System.out.println("Objects appear!");  //STUB
        }
    }

    private class StartButton{
        //Code for a button that starts the game
    	TextField text = new TextField(20);
    	Button b;
    	 public StartButton() {
    		 b = new Button("Start Game");
    		 add(b);
    		 add(text);
    		 //b.addActionListener(this);
    	 }
    	 
    	 public void actionPerformed(ActionEvent e) {
    		 
    	 }
    }


    private BufferedImage createImage(String fname){

		BufferedImage bufferedImage;

    		try {
    			// Method now takes a String (fname) in order to get the 
			// right image to load for all the images passed through
    			bufferedImage = ImageIO.read(new File(fname));
    			return bufferedImage;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		return null;
	}

}