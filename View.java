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
    	final static int frameWidth = Toolkit.getDefaultToolkit().getScreenSize().width;//500
   	final static int frameHeight = Toolkit.getDefaultToolkit().getScreenSize().height;//300
    	final static int imgWidth = 72;//165
	final static int imgHeight = 72;

	final int frameCount = 8;
	private int picNum = 0;
	
	private BufferedImage[][] pics;
	private BufferedImage quiz;
	
	
	//private DrawPanel drawPanel= new DrawPanel();
   	private Integer time; 
    	int titlex = 0;
   	static int crashlesstime = 0;

	private CardLayout cardLayout;
	private JPanel cards;
	private MenuPanel menu;
	private TutorialPanel tutorial;
	private HighScoresPanel highScores;
	private GamePanel game;
   	
	
	public void addPanelsToPane() {
   		game = new GamePanel();
   		tutorial = new TutorialPanel();
   		highScores = new HighScoresPanel();
   		menu = new MenuPanel();

		cards = new JPanel(new CardLayout());
   		cards.add(game,"Game");
		cards.add(tutorial, "Tutorial");
		cards.add(highScores, "HighScores");
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
        	
		bgp = new BufferedImage(frameWidth, frameHeight, 2);
        	Graphics2D graphic = bgp.createGraphics();
        	BufferedImage toBeResizedbgp = createImage("images/background.png");
		graphic.drawImage(toBeResizedbgp, 0, 0, frameWidth, frameHeight, null);

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
		
		tutorial.add(tutorialBTMB);
		tutorial.add(tutorialStartButton);
		menu.add(menuStartButton);
		menu.add(menuTutorialButton);
		
		
		
		
		menu.requestFocusInWindow();
		//game.requestFocusInWindow();
		add(cards);
        	//setExtendedState(JFrame.MAXIMIZED_BOTH);
 		//setUndecorated(true);		
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
	 * This method will draw the panel
	 *
	 * @return GamePanel the drawPanel that is currently on the top of the Card layout
	 **/
    	public GamePanel getGame(){
        	return game;
    	}
    
	/**
	 * This method returns the ArrayList of InterObjs for the game
	 *
	 * @return ArrayList<InterObj> The ArrayList of InterObjs
	 **/
    	public ArrayList<InterObj> getStuff(){
        	return stuff;
    	}
	/**
	 * This method will repaint the panel that is curently on the top of the CardLayout.
	 *
	 **/
    	public void drawTopCard() {
		for (Component comp : cards.getComponents()) {
			if (comp.isVisible() == true) {
				if (((JPanel)comp).getName() == "Menu") {
					menu.repaint();
				}
				else if (((JPanel)comp).getName() == "Game") {
					game.repaint();
				}
				else if (((JPanel)comp).getName() == "Tutorial") {
					tutorial.repaint();
				}
				else if (((JPanel)comp).getName() == "HighScores") {
					highScores.repaint();
				}
			}
		}
		game.repaint();
	}
	
	/**
	 * This method will take in an Integer and set the time of the game equal to the time from the Controller.
	 **/
	public void setTime(Integer time) {
		this.time = time;
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
					}else if (e.getKeyCode() == KeyEvent.VK_Y){
						if (ifquiz){
							answerQuiz(1);
							Controller.start();
							notQuizTime();
						}

					}else if (e.getKeyCode() == KeyEvent.VK_N){
						if (ifquiz) {
							answerQuiz(0);
							Controller.start();
						}

					}else if (e.getKeyCode() == KeyEvent.VK_R){
						if (iflose == true | ifwin == true){
							iflose = false;
							ifwin = false;
							Controller.restart();
						}
							
							
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
        		g.drawImage(quiz, 400,250 , this);
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
		 * This method will draw the win screen.
		 *
		 * @param g The Graphics object that the win image will be drawn on.
		 **/
        	public void drawwin(Graphics g){
        		g.drawImage(win, 200,150 , this);
        	}
		/**
		 * This method will draw the replay screen and prompt the user if they want to replay the game when it is over.
		 *
		 * @param g The Graphics object that the replay image will be drawn on.
		 **/
        	public void drawreplay(Graphics g){
        		g.drawImage(replay, 500,550 , this);
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
        		g.drawImage(title, titlex, 10, this);
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
		 * This method will draw the time (THIS WILL CHANGE INTO A BOAT RUNNING ACROSS THE WAVES IN THE BACKGROUND).
		 **/
		public void drawTime(Graphics g) {
			g.drawString(time.toString(),player.getXLoc()+(imgWidth*2/5), player.getYLoc()+(imgHeight-4));
		}
		/**
		 * This method will draw the background image(s).
		 **/
		public void drawbgp(Graphics g){
			g.drawImage(bgp, 0, 0, this);
		}
	}

	@SuppressWarnings("serial")
	private class HighScoresPanel extends JPanel {
		/**
		 * This constructor will create the JPanel that will create the JPanel for the game and add the key listeners for the crab/quizzes.
		 **/
        	public HighScoresPanel(){
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
					}else if (e.getKeyCode() == KeyEvent.VK_Y){
						if (ifquiz){
							answerQuiz(1);
							Controller.start();
							notQuizTime();
						}

					}else if (e.getKeyCode() == KeyEvent.VK_N){
						if (ifquiz) {
							answerQuiz(0);
							Controller.start();
						}

					}else if (e.getKeyCode() == KeyEvent.VK_R){
						if (iflose == true | ifwin == true){
							iflose = false;
							ifwin = false;
							Controller.restart();
						}
							
							
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
        		g.drawImage(quiz, 400,250 , this);
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
		 * This method will draw the win screen.
		 *
		 * @param g The Graphics object that the win image will be drawn on.
		 **/
        	public void drawwin(Graphics g){
        		g.drawImage(win, 200,150 , this);
        	}
		/**
		 * This method will draw the replay screen and prompt the user if they want to replay the game when it is over.
		 *
		 * @param g The Graphics object that the replay image will be drawn on.
		 **/
        	public void drawreplay(Graphics g){
        		g.drawImage(replay, 500,550 , this);
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
        		g.drawImage(title, titlex, 10, this);
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
		 * This method will draw the time (THIS WILL CHANGE INTO A BOAT RUNNING ACROSS THE WAVES IN THE BACKGROUND).
		 **/
		public void drawTime(Graphics g) {
			g.drawString(time.toString(),player.getXLoc()+(imgWidth*2/5), player.getYLoc()+(imgHeight-4));
		}
		/**
		 * This method will draw the background image(s).
		 **/
		public void drawbgp(Graphics g){
			g.drawImage(bgp, 0, 0, this);
		}
	}

	@SuppressWarnings("serial")
	private class MenuPanel extends JPanel {
		/**
		 * This constructor will create the JPanel that will create the JPanel for the game and add the key listeners for the crab/quizzes.
		 **/
        	public MenuPanel(){
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
					}else if (e.getKeyCode() == KeyEvent.VK_Y){
						if (ifquiz){
							answerQuiz(1);
							Controller.start();
							notQuizTime();
						}

					}else if (e.getKeyCode() == KeyEvent.VK_N){
						if (ifquiz) {
							answerQuiz(0);
							Controller.start();
						}

					}else if (e.getKeyCode() == KeyEvent.VK_R){
						if (iflose == true | ifwin == true){
							iflose = false;
							ifwin = false;
							Controller.restart();
						}
							
							
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
        		g.drawImage(quiz, 400,250 , this);
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
		 * This method will draw the win screen.
		 *
		 * @param g The Graphics object that the win image will be drawn on.
		 **/
        	public void drawwin(Graphics g){
        		g.drawImage(win, 200,150 , this);
        	}
		/**
		 * This method will draw the replay screen and prompt the user if they want to replay the game when it is over.
		 *
		 * @param g The Graphics object that the replay image will be drawn on.
		 **/
        	public void drawreplay(Graphics g){
        		g.drawImage(replay, 500,550 , this);
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
        		g.drawImage(title, titlex, 10, this);
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
		 * This method will draw the time (THIS WILL CHANGE INTO A BOAT RUNNING ACROSS THE WAVES IN THE BACKGROUND).
		 **/
		public void drawTime(Graphics g) {
			g.drawString(time.toString(),player.getXLoc()+(imgWidth*2/5), player.getYLoc()+(imgHeight-4));
		}
		/**
		 * This method will draw the background image(s).
		 **/
		public void drawbgp(Graphics g){
			g.drawImage(bgp, 0, 0, this);
		}
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
					}else if (e.getKeyCode() == KeyEvent.VK_Y){
						if (ifquiz){
							answerQuiz(1);
							Controller.start();
							notQuizTime();
						}

					}else if (e.getKeyCode() == KeyEvent.VK_N){
						if (ifquiz) {
							answerQuiz(0);
							Controller.start();
						}

					}else if (e.getKeyCode() == KeyEvent.VK_R){
						if (iflose == true | ifwin == true){
							iflose = false;
							ifwin = false;
							Controller.restart();
						}
							
							
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
        		g.drawImage(quiz, 400,250 , this);
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
		 * This method will draw the win screen.
		 *
		 * @param g The Graphics object that the win image will be drawn on.
		 **/
        	public void drawwin(Graphics g){
        		g.drawImage(win, 200,150 , this);
        	}
		/**
		 * This method will draw the replay screen and prompt the user if they want to replay the game when it is over.
		 *
		 * @param g The Graphics object that the replay image will be drawn on.
		 **/
        	public void drawreplay(Graphics g){
        		g.drawImage(replay, 500,550 , this);
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
        		g.drawImage(title, titlex, 10, this);
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
		 * This method will draw the time (THIS WILL CHANGE INTO A BOAT RUNNING ACROSS THE WAVES IN THE BACKGROUND).
		 **/
		public void drawTime(Graphics g) {
			g.drawString(time.toString(),player.getXLoc()+(imgWidth*2/5), player.getYLoc()+(imgHeight-4));
		}
		/**
		 * This method will draw the background image(s).
		 **/
		public void drawbgp(Graphics g){
			g.drawImage(bgp, 0, 0, this);
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
    	 		cardLayout.show(cards, "Game");
			game.requestFocusInWindow();
			Controller.start();
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
    			cardLayout.show(cards, "Tutorial");
			tutorial.requestFocusInWindow();
			Controller.start();
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
    			cardLayout.show(cards, "Menu");
			menu.requestFocusInWindow();
			Controller.stop();
			Controller.restart();
    		}
    	
    	}
	/**
	 * This Mehtod will create an Image and return a BufferedImage.
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
	       	ifquiz = true;
	}
	/**
	 * This method will set the notquiztime boolean equal to to false and create a time that the crab cannot collide with someone else, sort of like a grace Period.
	 **/ 
	public void notQuizTime(){
	     	ifquiz = false;
	       	crashlesstime = 20;
	}

	public void answerQuiz(int answer) {
		//logic that will determine which quiz is currently viewing and test the correct answer depending on the integer provided, no will give 0, yes will give 1, can have a false/true questions too based off of this, which wont be too hard to implement, will probably need another object class called Quiz which has image and correctAnswer attributes that we can get and compare against answer here.
	}
	 

}
