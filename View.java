
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
    	private BufferedImage trashPic;

    	final static int frameStartSize = 800;
    	final static int frameWidth = 800;//500
   	final static int frameHeight = 800;//300
    	final static int imgWidth = 72;//165
	final static int imgHeight = 72;

	final int frameCount = 8;
	private int picNum = 0;
	
	private BufferedImage[][] pics;
	
	private DrawPanel drawPanel= new DrawPanel();
   	private Integer time; 
    
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
        	drawPanel.requestFocusInWindow();
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
		g.drawString(this.time.toString(),player.getXLoc()+(imgWidth*2/5),player.getYLoc()+(imgHeight-4));
    		g.drawImage(crabPic, player.getXLoc(), player.getYLoc(), this);
		for (InterObj o : stuff) {
			g.drawImage(trashPic, o.getXLoc(), o.getYLoc(), this);
		}
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
					}
                		}
            		});
        	}

        	@Override
        	protected void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			setBackground(Color.cyan);
			//g.setColor(new Color(0, 0, 0, 0));
            		drawCrab(g);
            		drawInterObjs(g);
			drawTime(g);
		}

        	@Override
		public Dimension getPreferredSize() {
			return new Dimension(frameStartSize, frameStartSize);
		}

        	public void drawCrab(Graphics g){
        		super.paintComponent(g);
        		g.setColor(Color.gray);
        		picNum = (picNum + 1) % 8;
        		g.drawImage(pics[0][picNum], player.getXLoc(), player.getYLoc(), this);
        	}

        	public void drawInterObjs(Graphics g){
            		for (InterObj object: stuff){
	            		g.drawImage(trashPic, object.getXLoc(), object.getYLoc(), this);
            		}
        	}
		public void drawTime(Graphics g) {
			g.drawString(time.toString(),player.getXLoc()+(imgWidth*2/5), player.getYLoc()+(imgHeight-4));
		}
	}	

    	private class StartButton{
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
