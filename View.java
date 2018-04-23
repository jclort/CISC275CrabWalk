
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
    private StartButton startButton;
    private BufferedImage crabPic;

    final static int frameStartSize = 800;
    final static int frameWidth = 800;//500
   	final static int frameHeight = 800;//300
    final static int imgWidth = 72;//165
	final static int imgHeight = 72;

	final int frameCount = 8;
	private int picNum = 0;
	
	private BufferedImage[][] pics;
	
	private DrawPanel drawPanel= new DrawPanel();
    
    
    public View(Crab p, ArrayList<InterObj> s){  
    	String[] picNames = {"images/crab.png"};
    	pics = new BufferedImage[picNames.length][frameCount];
    	for(int j = 0; j < picNames.length; j++) {
			// load the image
			BufferedImage img = createImage(picNames[j]);
    	
			for(int i = 0; i < frameCount; i++) {
				// all of the different movements of that image
				pics[j][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
			}
    	}
    
        score = 0;
        lives = 3;
        player = p;
        stuff = s;
        startButton = new StartButton();
        add(drawPanel);
        

        setBackground(Color.gray);                 
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                 
	    setSize(getWidth(), getHeight());
	    setVisible(true);
        pack();	
    }
	public Crab getPlayer() {
		return player;
	}
    public DrawPanel getDrawPanel(){
        return drawPanel;
    }
    
    public static int getFrameSize() {
    	return frameStartSize;
    }
     public void paint(Graphics g) {
    		    g.drawImage(crabPic, player.getXLoc(), player.getYLoc(), Color.gray, this);
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
					player.setDir(Direction.NORTH);
                    		}
					        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
						        //crab direction is down
							player.setDir(Direction.SOUTH);
					        }
					        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
						        //crab direction is right
							player.setDir(Direction.EAST);
					        }
					        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
						        //crab direction is left
							player.setDir(Direction.WEST);
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
        	super.paintComponent(g);
        	g.setColor(Color.gray);
        	picNum = (picNum + 1) % 8;
        	g.drawImage(pics[0][picNum], player.getXLoc(), player.getYLoc(), new Color(0, 0, 0, 0), this);
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
