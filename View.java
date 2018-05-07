import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;


public class View extends JFrame{
    	private int score;
    	private int lives;
    	private Crab player;
    	private ArrayList<InterObj> stuff;
    	//private StartButton startButton;
    	private BufferedImage crabPic;
    	private BufferedImage trashPic;
    	private BufferedImage trashPic1;
    	private BufferedImage trashPic2;
    	private BufferedImage trashPic3;
    	private BufferedImage trashPic4;
    	private BufferedImage[] invaPic1;
    	private BufferedImage[] invaPic2;
    	Random rand = new Random();
    	int randnum;
    	private BufferedImage bgp;
        
    	static boolean ifquiz;
    	//final static int frameStartSize = 1280;
    	final static int frameWidth = 1280;//500
   	final static int frameHeight = 720;//300
    	final static int imgWidth = 72;//165
	final static int imgHeight = 72;

	final int frameCount = 8;
	private int picNum = 0;
	
	private BufferedImage[][] pics;
	private BufferedImage quiz;
	
	private DrawPanel drawPanel= new DrawPanel();
   	private Integer time; 
    
   	static int crashlesstime = 0;
   	
    	public View(Crab p, ArrayList<InterObj> s, Integer startTime){  
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
    		
    		
        	time = startTime;
        	trashPic = createImage("images/trash1.png");
        	trashPic1 = createImage("images/trash2.png");
        	trashPic2 = createImage("images/trash3.png");
        	trashPic3 = createImage("images/trash4.png");
        	trashPic4 = createImage("images/trash5.png");
        	quiz = createImage("images/trashquiz1.png");
        	
        	invaPic1 = new BufferedImage[3];
        	for(int i = 0; i < 3; i++) {
        		invaPic1[i] = createImage("images/creature1.png").getSubimage(imgWidth*i, 0, 72, 61);
			}
        	
        	invaPic2 = new BufferedImage[3];
        	for(int i = 0; i < 3; i++) {
        		invaPic2[i] = createImage("images/creature2.png").getSubimage(90*i+17, 0, 90, 90);
			}
        	
        	
        	bgp =createImage("images/background.png");
        	score = 0;
        	lives = 3;
        	player = p;
        	stuff = s;
        	//startButton = new StartButton();
        	add(drawPanel);
        

        	               
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                 
	    	setSize(getWidth(), getHeight());
	    	setVisible(true);
        	drawPanel.requestFocusInWindow();
        	pack();	
    	}

	public Crab getPlayer() {
		return player;
	}
    	public DrawPanel getDrawPanel(){
        	return drawPanel;
    	}
    
    	//public static int getFrameSize() {
    		//return frameStartSize;
    //	}

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

	public void setTime(Integer time) {
		this.time = time;
	}


    	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel {

        	public DrawPanel(){
        	    	super();
        	        System.out.println("key");
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
					}else if (e.getKeyCode() == KeyEvent.VK_Y){
						Controller.start();
						notquiztime();
					}
                		}
            		});
        	}

        	@Override
        	protected void paintComponent(Graphics g) {
			
			
			//setBackground(Color.cyan);
			//g.setColor(new Color(0, 0, 0, 0));
			drawbgp(g);
			drawCrab(g);
            drawInterObjs(g);
			drawTime(g);
			if (ifquiz){
				drawquiz(g);
			}
		}

        	@Override
		public Dimension getPreferredSize() {
			return new Dimension(frameWidth, frameHeight);
		}
        	
       
        public void drawquiz(Graphics g){
        	g.drawImage(quiz, 400,300 , this);
        }
        
        public void drawCrab(Graphics g){
 
        		picNum = (picNum + 1) % 8;
        		g.drawImage(pics[0][picNum], player.getXLoc(), player.getYLoc(), this);
        	}

        	public void drawInterObjs(Graphics g){
        			picNum = (picNum + 1) % 3;
        			for (InterObj object: stuff){
            			int objname = object.name;
            			BufferedImage objp = null;
            			
            			
            			switch (objname) {
            	            case 1:  objp = trashPic;
            	                     break;
            	            case 2:  objp = trashPic1;
            	            		 break;
            	            case 3:  objp = trashPic2;
   	            		 			 break;
            	            case 4:  objp = trashPic3;
   	            		 			 break;
            	            case 5:  objp = trashPic4;
   	            		 			 break;   
            	            case 6:  g.drawImage(invaPic1[picNum], object.getXLoc(), object.getYLoc(), this);
            	            		 break;
   	            		 			 
            	            case 7:  g.drawImage(invaPic2[picNum], object.getXLoc(), object.getYLoc(), this);
            	            		 break; 	            		 			 
            	            		 
            		    }
	            		g.drawImage(objp, object.getXLoc(), object.getYLoc(), this);
            			}
	            	
            		
        	}
		public void drawTime(Graphics g) {
			g.drawString(time.toString(),player.getXLoc()+(imgWidth*2/5), player.getYLoc()+(imgHeight-4));
		}
		public void drawbgp(Graphics g){
			g.drawImage(bgp, 0, 0, this);
		}
	}	

    	/**private class StartButton{
        	//Code for a button that starts the game
    		TextField text = new TextField(20);
    		JButton b;
    		public StartButton() {
    			b = new JButton("Start Game");
    			add(b);
    			add(text);
    			//b.addActionListener(this);
    	 	}
    	 
    	 	public void actionPerformed(ActionEvent e) {
    	 		
    	 	}
    	}
    	
    	private class TutorialButton {
    		//two buttons, giving the player the option to start the tutorial or go back to the loading screen
    		//playing around with the idea of making this and the retry button their own separate windows rather 
    		//than part of the main jframe
    		public static void main(String[] args) {
    			JFrame tutorial = new
    		}
    		TextField tutStart = new TextField(20);
    		TextField tutBack = new TextField(20);
    		JButton a;
    		JButton b;
    		public TutorialButton() {
    			a = new JButton("Start Tutorial");
    			add(a);
    			add(tutStart);
    			b = new JButton("Back");
    			add(b);
    			add(tutBack);
    			//a.addActionListener(this);
    			//b.addActionListener(this);
    		}
    		public void actionPerformed(ActionEvent e) {
    			
    		}
    	}
    	
    	private class RetryButton {
    		TextField retry = new TextField(20);
    		TextField backToMain = new TextField(20);
    		JButton a;
    		JButton b;
    		public RetryButton() {
    			a = new JButton("Retry?");
    			add(a);
    			add(retry);
    			b = new JButton("Back to Main Menu");
    			add(b);
    			add(backToMain);
    			//a.addActionListener(this);
    			//b.addActionListener(this);
    		}
    		public void actionPerformed(ActionEvent e) {
    			
    		}
    	
    	
    	}**/

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

	
		 public static void quiztime(){
	        	ifquiz = true;
	        }
	        public void notquiztime(){
	        	ifquiz = false;
	        	crashlesstime = 20;
	        }
	 

}