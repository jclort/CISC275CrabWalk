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
    	private StartButton startButton;
    	private BufferedImage crabPic;
    	private BufferedImage trashPic;
    	private BufferedImage trashPic1;
    	private BufferedImage trashPic2;
    	private BufferedImage trashPic3;
    	private BufferedImage trashPic4;
    	private BufferedImage[] invaPic1;
    	private BufferedImage[] invaPic2;
    	private BufferedImage title;
    	private BufferedImage win;
    	private BufferedImage replay;
    	private BufferedImage gameover;
    	
    	Random rand = new Random();
    	
    	int randnum;
    	private BufferedImage bgp;
        
    	int starttime = 50;

    	static boolean ifquiz;
    	static boolean ifstart = false;
    	static boolean iflose = false;
    	boolean ifwin = false;
    	
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
    int titlex = 0;
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
        	title = createImage("images/startwords.png");
        	win = createImage("images/win.jpg");
        	replay = createImage("images/replay.png");
        	gameover = createImage("images/gameover.png");
        	
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
        	startButton = new StartButton();
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
						if (ifquiz){
						Controller.start();
						notquiztime();
						}
					}else if (e.getKeyCode() == KeyEvent.VK_N){
						if (ifquiz){
							iflose = true;
							Controller.start();
							notquiztime();
							
						}
					}else if (e.getKeyCode() == KeyEvent.VK_R){
							if (iflose == true | ifwin == true){
							iflose = false;
							ifwin = false;
							Controller.restart();
							player.setXLoc(400);
							player.setYLoc(400);
							}
							
							
					}
                		}
            		});
        	}

        	@Override
        	protected void paintComponent(Graphics g) {
			
			
			//setBackground(Color.cyan);
			//g.setColor(new Color(0, 0, 0, 0));
        	if (ifstart == false){
        		drawbgp(g);	
        		drawtitle(g);
        	}else{
        		if (iflose == false & ifwin == false){
			drawbgp(g);
			drawCrab(g);
            drawInterObjs(g);
			drawTime(g);
        		}else{
        			if (iflose){
        				setBackground(Color.black);
        				drawgameover(g);
        				Controller.stop();
        			}else if(ifwin & ifquiz==false){
        				setBackground(Color.white);
        				drawwin(g);
        				drawreplay(g);
        				Controller.stop();
        			}
        		}
			if (ifquiz){
				drawquiz(g);
			}
			}
		}

        	@Override
		public Dimension getPreferredSize() {
			return new Dimension(frameWidth, frameHeight);
		}
        	
       
        public void drawquiz(Graphics g){
        	g.drawImage(quiz, 400,250 , this);
        }
        public void drawgameover(Graphics g){
        	g.drawImage(gameover, 300,175 , this);
        }
        public void drawwin(Graphics g){
        	g.drawImage(win, 200,150 , this);
        }
        public void drawreplay(Graphics g){
        	g.drawImage(replay, 500,550 , this);
        }
        public void drawCrab(Graphics g){
 
        		picNum = (picNum + 1) % 8;
        		g.drawImage(pics[0][picNum], player.getXLoc(), player.getYLoc(), this);
        	}
        public void drawtitle(Graphics g){
        		if (titlex<300){
        			titlex = titlex + 20;
        		}else{
        			ifstart = true;
        		}
        	   g.drawImage(title, titlex, 10, this);
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

	
		 public static void quiztime(){
	        	ifquiz = true;
	        }
	        public void notquiztime(){
	        	ifquiz = false;
	        	crashlesstime = 20;
	        }
	 

}