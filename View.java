import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;


public class View extends JFrame implements Serializable{
    	private int score;
    	private int lives;
    	private Crab player;
    	private ArrayList<InterObj> stuff;
    	
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
    	private BufferedImage boatPic;
	private BufferedImage dockPic;
	private BufferedImage checkmark;
        private BufferedImage clampic;
	int boatX = 0;

	Quiz quiz = new Quiz();	
    	private boolean trashCollision = false;
	private BufferedImage quizPic;
    	Random rand = new Random();
	private static int quizCtr = 1;    	
    	int randnum;
    	private BufferedImage bgp;
        
    	int starttime = 50;

    	static boolean ifquiz;
        static boolean quizstart;
    	static boolean ifstart = false;
    	static boolean iflose = false;
    	boolean ifwin = false;
    	
    	final static int frameWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
   	final static int frameHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    	final static int imgWidth = 72;
	final static int imgHeight = 72;

	final int frameCount = 8;
	private int picNum = 0;
	
	private BufferedImage[][] pics;
	private int quizNum;
   	private Integer time; 
    	int titlex = 0;
   	static int crashlesstime = 0;

	private CardLayout cardLayout;
	private JPanel cards;
	private MenuPanel menu;
	private TutorialPanel tutorial;
	private GamePanel game;
   	private Graphics2D graphic;
	
	public void addPanelsToPane() {
        
   		game = new GamePanel();
   		tutorial = new TutorialPanel();
   		menu = new MenuPanel();

		cards = new JPanel(new CardLayout());
   		cards.add(game,"Game");
		cards.add(tutorial, "Tutorial");
		cards.add(menu, "Menu");
   		
		cardLayout = (CardLayout) cards.getLayout();
		
   	}
   	
   	/**
	 * This is the View constructor that creates the visual base to our game.
	 * It creates the images for all InterObjs and Crab, manages the JPanels into a CardLayout, and puts the menu jpanel first.
	 *
	 * @param p - p represents the player which is a Crab and will be created in the Model and passed to this constructor from the Controller.
	 * @param s - s represents the ArrayList of InterObjs that will represent the everything to be drawn in the tutorial and game panels except for the player.
	 * @param startTime - the time from the model to create a new time, mainly for when the view is recreated in the Controller.
	 *
	 **/
   	public View(Crab p, ArrayList<InterObj> s, Integer startTime){  
    		setView(p, s, startTime);
	}

    public void setView(Crab p, ArrayList<InterObj> s, Integer startTime){
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
    		quizCtr = 1;
            ifquiz = false;
    		quizstart = false;
        	time = startTime;
        	trashPic = createImage("images/trash1.png");
        	trashPic1 = createImage("images/trash2.png");
        	trashPic2 = createImage("images/trash3.png");
        	trashPic3 = createImage("images/trash4.png");
        	trashPic4 = createImage("images/trash5.png");
           	clampic = createImage("images/score.png");
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
        	boatX = 0;
		bgp = new BufferedImage(frameWidth, frameHeight, 2);
        	graphic = bgp.createGraphics();
        	BufferedImage toBeResizedbgp = createImage("images/background.png");
		graphic.drawImage(toBeResizedbgp, 0, 0, frameWidth, frameHeight, null);
		
		boatPic = createImage("images/boat.png");

		dockPic = createImage("images/dock.png");
		
		BufferedImage checkmark = createImage("images/checkmark.png");
		//graphic3.drawImage(toBeResizedCheck, 0,0,20,20,null);	




        	score = 0;
        	lives = 3;
        	player = p;
        	stuff = s;
		
		setSize(getWidth(), getHeight());
        	
		
		addPanelsToPane();
		cardLayout.show(cards, "Menu");
		
		TutorialButton menuTutorialButton = new TutorialButton();
		BackToMenuButton tutorialBTMB = new BackToMenuButton();
		StartButton menuStartButton = new StartButton();
		StartButton tutorialStartButton = new StartButton();
		LoadGameButton menuLoadGameButton = new LoadGameButton();
		SaveGameButton gameSaveGameButton = new SaveGameButton();

		tutorial.add(tutorialBTMB);
		tutorial.add(tutorialStartButton);
		menu.add(menuStartButton);
		menu.add(menuTutorialButton);
		//menu.add(menuLoadGameButton);
		//game.add(gameSaveGameButton);	
		
		
		
		
		menu.requestFocusInWindow();
		add(cards);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
 		setUndecorated(true);		
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                 
	    	setVisible(true);
        	pack();	
    }
	/**
	 * This method returns the player to pass on to the Model from the Controller.
	 *
	 * @return Crab The player
	 **/
	public Crab getPlayer() {
		return player;
	}
	/**
	 * This method returns the ArrayList of InterObjs for the game
	 *
	 * @return ArrayList<InterObj> The ArrayList of InterObjs
	 **/
    	public ArrayList<InterObj> getStuff(){
        	return stuff;
    	}


        public JPanel getCards(){
            return cards;
        }
	/**
	 * This method will repaint the panel that is curently on the top of the CardLayout.
	 *
	 **/
    	public void drawTopCard() {
		for (Component comp : cards.getComponents()) {
			if (comp.isVisible() == true) {
				comp.repaint();
			}
		}
		game.repaint();
	}
	/**
	 * This method will return the top card in the CardLayout so that the controller knows which is currently happening in the View and tell the model.
	 *
	 * @return String The name of the JPanel that is on the top card.
	 **/
	public String getTopCard() {
		for (Component comp: cards.getComponents()) {
			if (comp.isVisible() == true) {
				return comp.getName();
			}
		}
		return "this case will never happen, but just for compiling purposes";
	}
	
	/**
	 * This method will take in an Integer and set the time of the game equal to the time from the Controller.
	 **/
	public void setTime(Integer time) {
		this.time = time;
	}
    	@SuppressWarnings("serial")
	private class TutorialPanel extends JPanel {
		/**
		 * This constructor will create the JPanel that will create the JPanel for the game and add the key listeners for the crab/quizzes.
		 **/
        	public TutorialPanel(){
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
					} else if (e.getKeyCode() == KeyEvent.VK_A){
						if (ifquiz){
							answerQuiz(0);
						}
					}else if (e.getKeyCode() == KeyEvent.VK_B){
						if (ifquiz) {
							answerQuiz(1);
						}
					} else if (e.getKeyCode() == KeyEvent.VK_C) {
						if (ifquiz) {
							answerQuiz(2);
						}
					} else if (e.getKeyCode() == KeyEvent.VK_D) {
						if (ifquiz) {
							answerQuiz(3);
						}
					}else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						Controller.start();
						notQuizTime();
					}
                		}
            		});
        	}
		/**
		 * This method will override paintComponent from JPanel specific to the objects that need to be drawn on the JPanel
		 *
		 * @param g The Graphics object that will be passed to other draw functions for the other objects in the game. 
		 **/
        	@Override
        	protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawbgp(g);
			drawCrab(g);
            		drawInterObjs(g);
			drawTime(g);
            //drawLives(g);
			drawDirections(g);
        		
        		if (ifquiz){
				//Controller.stop();
                if (quizCtr%5 == 0) {
                    Controller.stop();
				    drawquiz(g);
                }
                quizstart = false;
			}
			
		}
		/**
		 * This method will return a Dimension for the JPanel to resize
		 *
		 * @return Dimension The new Dimension for the JPanel/JFrame.
		 **/
        	@Override
		public Dimension getPreferredSize() {
			return new Dimension(frameWidth, frameHeight);
		}
        	
       		/**
		 * This method will draw the quiz on the screen.
		 *
		 * @param g The Graphics object that the quiz will be drawn on.
		 **/
        	public void drawquiz(Graphics g){
			//if (quizCtr++%5 == 0) {
				quizNum = quiz.getQuiz();
				quizPic = quiz.getPic(quizNum);
        	    g.drawImage(quizPic, 400,250 , this);
			//}
        	}
		/**
		 * This method will draw the player Crab.
		 *
		 * @param g The Graphics object that the crab image will be drawn on.
		 **/
        	public void drawCrab(Graphics g){
 		       	picNum = (picNum + 1) % 8;
 		       	g.drawImage(pics[0][picNum], player.getXLoc(), player.getYLoc(), this);
 	       }
		/**
		 * This method will draw the title moving across the screen, part of the menu JPanel.
		 *
		 * @param g The Graphics object that the Title image will be drawn on.
		 **/
        	public void drawtitle(Graphics g){
        		if (titlex<300){
        			titlex = titlex + 20;
        		}else{
        			ifstart = true;
        		}
        		g.drawImage(title, titlex, 350, this);
        	}
		/**
		 * This method will draw the InterObjs by running through the ArrayList and drawing each one.
		 *
		 * @param g The Graphics object that the InterObj images will be drawn on.
		 **/
        	public void drawInterObjs(Graphics g){
			String drawCaption = "Just a place holder";
        		picNum = (picNum + 1) % 3;
        		for (InterObj object: stuff){
        			int objname = object.name;
        			BufferedImage objp = null;
            			           			
            			switch (objname) {
            	        	case 1:  objp = trashPic;
					 drawCaption = "CATCH ME FOR POINTS!";
            	              		break;
            	        	case 2:  objp = trashPic1;
					 drawCaption = "CATCH ME FOR POINTS!";
            	        		break;
            	        	case 3:  objp = trashPic2;
					 drawCaption = "CATCH ME FOR POINTS!";
   	            		 	break;
            	            	case 4:  objp = trashPic3;
					 drawCaption = "CATCH ME FOR POINTS!";
   	            		 	break;
            	            	case 5:  objp = trashPic4;
					 drawCaption = "CATCH ME FOR POINTS!";
   	            			break;   
            	           	case 6:  g.drawImage(invaPic1[picNum], object.getXLoc(), object.getYLoc(), this);
					 drawCaption = "AVOID ME!";
            	          		break;		 
            	        	case 7:  g.drawImage(invaPic2[picNum], object.getXLoc(), object.getYLoc(), this);
					 drawCaption = "AVOID ME!";
            	        		break; 	            		 			 
            	           		 
            			}
	            		g.drawImage(objp, object.getXLoc(), object.getYLoc(), this);
				g.drawString(drawCaption, object.getXLoc(), object.getYLoc()-2);
            		}		
        	}
		public void drawTime(Graphics g) {
			int distance = frameWidth-15-125;
			g.drawImage(dockPic,frameWidth-220,85,this);
			boatX = boatX + distance/500;
			g.drawImage(boatPic, boatX, 50, this);
		}

		/**
		 * This method will draw the background image(s).
		 **/
		public void drawbgp(Graphics g){
			g.drawImage(bgp, 0, 0, this);
		}
		/**
		 * This method will draw the directions necessary to play the game!
		 **/
		public void drawDirections(Graphics g) {
			g.drawString("Use the Arrow Keys to Move the Crab", 100, 350);
			g.drawString("Pick up Trash and Dodge the Invasive Species", 100, 365);
			g.drawString("Answer a Quiz Right to Receive a Power-Up", 100, 380);
			g.drawString("After Answering a Quiz, Press Enter to Continue", 100, 395);
			g.drawString("The Game Ends when the Boat Reaches the Dock", 100, 410);
		}
	}

	@SuppressWarnings("serial")
	private class MenuPanel extends JPanel {
		/**
		 * This constructor will create the JPanel that will create the JPanel for the game and add the key listeners for the crab/quizzes.
		 **/
        	public MenuPanel(){
        	    	super();
        	}
		/**
		 * This method will override paintComponent from JPanel specific to the objects that need to be drawn on the JPanel
		 *
		 * @param g The Graphics object that will be passed to other draw functions for the other objects in the game. 
		 **/
        	@Override
        	protected void paintComponent(Graphics g) {
        		drawbgp(g);	
        		drawtitle(g);
		}
		/**
		 * This method will return a Dimension for the JPanel to resize
		 *
		 * @return Dimension The new Dimension for the JPanel/JFrame.
		 **/
        	@Override
		public Dimension getPreferredSize() {
			return new Dimension(frameWidth, frameHeight);
		}
        	
		/**
		 * This method will draw the title moving across the screen, part of the menu JPanel.
		 *
		 * @param g The Graphics object that the Title image will be drawn on.
		 **/
        	public void drawtitle(Graphics g){
        		if (titlex<300){
        			titlex = titlex + 20;
        		}else{
        			ifstart = true;
        		}
        		g.drawImage(title, titlex, 350, this);
        	}
		/**
		 * This method will draw the background image(s).
		 **/
		public void drawbgp(Graphics g){
			g.drawImage(bgp, 0, 0, this);
		}
	}

	@SuppressWarnings("serial")
	private class GamePanel extends JPanel {
		/**
		 * This constructor will create the JPanel that will create the JPanel for the game and add the key listeners for the crab/quizzes.
		 **/
        	public GamePanel(){
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
					}else if (e.getKeyCode() == KeyEvent.VK_A){
						if (ifquiz){
							answerQuiz(0);
						}
					}else if (e.getKeyCode() == KeyEvent.VK_B){
						if (ifquiz) {
							answerQuiz(1);
						}
					} else if (e.getKeyCode() == KeyEvent.VK_C) {
						if (ifquiz) {
							answerQuiz(2);
						}
					} else if (e.getKeyCode() == KeyEvent.VK_D) {
						if (ifquiz) {
							answerQuiz(3);
						}
					} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						Controller.start();
						notQuizTime();
					}
                		}
            		});
        	}
		/**
		 * This method will override paintComponent from JPanel specific to the objects that need to be drawn on the JPanel
		 *
		 * @param g The Graphics object that will be passed to other draw functions for the other objects in the game. 
		 **/
        	@Override
        	protected void paintComponent(Graphics g) {
        		if (ifstart == false){
        			drawbgp(g);	
        			drawtitle(g);
        		}else{
        			if (time > 0){
					drawbgp(g);
                    drawTime(g);
                    drawScore(g);
					drawCrab(g);
            				drawInterObjs(g);
        			} else if (time == 0){
        				drawgameover(g);
        				Controller.stop();
					Controller.restart();
        		    }
                    if (ifquiz){
                    if (quizCtr%5 == 0) {
					    Controller.stop();
					    drawquiz(g);
                    }

                    quizstart = false;
				}
			}
		}
		/**
		 * This method will return a Dimension for the JPanel to resize
		 *
		 * @return Dimension The new Dimension for the JPanel/JFrame.
		 **/
        	@Override
		public Dimension getPreferredSize() {
			return new Dimension(frameWidth, frameHeight);
		}
        	
       		/**
		 * This method will draw the quiz on the screen.
		 *
		 * @param g The Graphics object that the quiz will be drawn on.
		 **/
        	public void drawquiz(Graphics g){
			//if (quizCtr++%25 == 0 || trashCollision == true) {
				quizNum = quiz.getQuiz();
				quizPic = quiz.getPic(quizNum);
				g.drawImage(quizPic, 400, 250, this);
        		//}
		}
		/**
		 * This method will draw the game over screen.
		 *
		 * @param g The Graphics object that the game over image will be drawn on.
		 **/
        	public void drawgameover(Graphics g){
        		g.drawImage(gameover, 300,175 , this);
        	}
		/**
		 * This method will draw the player Crab.
		 *
		 * @param g The Graphics object that the crab image will be drawn on.
		 **/
        	public void drawCrab(Graphics g){
 		       	picNum = (picNum + 1) % 8;
 		       	g.drawImage(pics[0][picNum], player.getXLoc(), player.getYLoc(), this);
 	       }
		/**
		 * This method will draw the title moving across the screen, part of the menu JPanel.
		 *
		 * @param g The Graphics object that the Title image will be drawn on.
		 **/
        	public void drawtitle(Graphics g){
        		if (titlex<300){
        			titlex = titlex + 20;
        		}else{
        			ifstart = true;
        		}
        		g.drawImage(title, titlex, 350, this);
        	}

        public void drawTime(Graphics g) {
			int distance = frameWidth-15-125;
			g.drawImage(dockPic,frameWidth-220,85,this);
			boatX = boatX + distance/500;
			g.drawImage(boatPic, boatX, 50, this);
		}
		/**
		 * This method will draw the InterObjs by running through the ArrayList and drawing each one.
		 *
		 * @param g The Graphics object that the InterObj images will be drawn on.
		 **/
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
		/**
		 * This method will draw the background image(s).
		 **/
		public void drawbgp(Graphics g){
			// Add more drawImages, but in this order, draw the sky, then the dock, then the boat, then the ocean(bgp)
			g.drawImage(bgp, 0, 0, this);
		}
		/**
		 * This method will draw the score for the user.
		 **/
		public void drawScore(Graphics g) {
			g.drawString("Score: ", 0, 0);
			int xHolder = 10;
			int score = player.getTotalScore();
			int numberOfClams = score % 20;
			for (int i = 0; i < numberOfClams; i++) {
				g.drawImage(clampic, xHolder, 0, this);
				xHolder = xHolder + 55;
			}
		}	
			
	}	
	
    	private class StartButton extends JButton implements ActionListener{
        	//Code for a button that starts the game
    		JButton b;
    		public StartButton() {
    			b = new JButton("Start Game");
			b.addActionListener(this);
			add(b);
    	 	}
    	 	
		@Override		
    	 	public void actionPerformed(ActionEvent e) {
			Controller.restart();
			Controller.start();
    	 		cardLayout.show(cards, "Game");
			game.requestFocusInWindow();
			
    	 	}
    	}
    	
    	public class TutorialButton extends JButton implements ActionListener {
    		//two buttons, giving the player the option to start the tutorial or go back to the loading screen
    		//playing around with the idea of making this and the retry button their own separate windows rather 
    		//than part of the main jframe
    		JButton a;
    		public TutorialButton() {
    			a = new JButton("Start Tutorial");
			a.addActionListener(this);
			add(a);
    		}

    		@Override
    		public void actionPerformed(ActionEvent e) {
			//Controller.start();
    		cardLayout.show(cards, "Tutorial");
            Controller.start();
			tutorial.requestFocusInWindow();
    		}
    	}
    	
    	public class BackToMenuButton extends JButton implements ActionListener {
    		JButton a;
    		public BackToMenuButton() {
    			a = new JButton("Return to Main Menu");
    			a.addActionListener(this);
			add(a);
    		}
    		
    		public void actionPerformed(ActionEvent e) {
			Controller.stop();
			Controller.restart();
    		}
    	
    	}

	public class SaveGameButton extends JButton implements ActionListener {
		JButton a;
		public SaveGameButton() {
			a = new JButton("Save Game");
			a.addActionListener(this);
			add(a);
		}
		public void actionPerformed(ActionEvent e) {
			// method call to the logic that will save the game
			Controller.saveGame();
			Controller.stop();
			Controller.restart();
		}
	}

	public class LoadGameButton extends JButton implements ActionListener {
		JButton a;
		public LoadGameButton() {
			a = new JButton("Load Game");
			a.addActionListener(this);
			add(a);
		}
		public void actionPerformed(ActionEvent e) {
			// Method call to the logic that will retrieve the selected game state and
			// fast forward the current game state to it.
		}
	}

	/**
	 * This Method will create an Image and return a BufferedImage.
	 **/
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

	/**
	 * This method will set the ifquiz boolean equal to true.
	 **/
	 public static void quizTime(){
            if(!ifquiz){
                quizstart = true;
                quizCtr++;
            }
	       	ifquiz = true;
            
	}
	/**
	 * This method will set the notquiztime boolean equal to to false and create a time that the crab cannot collide with someone else, sort of like a grace Period.
	 **/ 
	public static void notQuizTime(){
	     	ifquiz = false;
	       	//crashlesstime = 20;
	}

	public void answerQuiz(int answer) {
		//logic that will determine which quiz is currently viewing and test the correct answer depending on the integer provided, no will give 0, yes will give 1, can have a false/true questions too based off of this, which wont be too hard to implement, will probably need another object class called Quiz which has image and correctAnswer attributes that we can get and compare against answer here.
	    if(answer != quiz.getAnswer(quizNum)){
            player.removeLife();
        }
        crashlesstime = 20;
        Controller.start();
        
    }
	 

}
