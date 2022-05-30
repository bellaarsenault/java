/* Frame.java
 * name: bella arsenault
 * date:
 * 
 * purpose:
 * 
 * methods:
 * 
 */
package buttons;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

///import assignment9.TicTacToeWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JFrame frame;
    private JPanel panel, mainPanel, menuLabelPane, menuSubLabelPane, menu, menuButtonPanel, gameAI;
    private JLabel menuL, menuSubL;
    private Font font, font2;
    private BufferedImage image, stone1, stone2, stone3, stone4, stone5, stone5side;
    private static int boardHeight, boardWidth, fivewidth, fiveheight, stone1Height, stone1Width, twowidth, twoheight, threeWidth, threeHeight, fourheight, fourwidth, fivesidewidth, fivesideheight; 
    private static int x, y, bWidth, bHeight, ebWidth, ebHeight;
    private JButton[][] button = new JButton[2][7]; //fix amount in this button
    private JButton[][] button2 = new JButton[2][7];
    private JButton[] endButton = new JButton[2];
    private JLabel[][] stoneNums = new JLabel[2][6];
    private JLabel[] endStoneNums = new JLabel[2];
    
    private JLabel title, playerTurn, winnerStatus, player1, player2, playerTurnAI;
    private JButton quit, playAgain, seeHighscores, menuB, quitB;
    private CardLayout cardLayout;
    private String holder1, holder2;
	Border emptyBorder = BorderFactory.createEmptyBorder();
	MancalaRules play = new MancalaRules();
	AI ai = new AI();

	public Frame(String title) {
		super(title);
		
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		
		menuLabelPane = new JPanel();
		menuLabelPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); //creating a border because without the border when everything is added to the main "screens" there isn't any space underneath each of the panels that are holding the main label, and the sub label
		
		menuSubLabelPane = new JPanel();
		menuSubLabelPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 160, 20)); //need border created so that when they are added to the layout there will be space in between the separate panels 
		
		menu = new JPanel();
		
		menuLabelPane.setBackground(Color.getHSBColor(10, 500, 225)); //each panel needs to have the same colour, that way it will look like the combined background is the same
		menuL = new JLabel("Welcome to ICS4U Summative!");
		font = new Font("Monaco", Font.BOLD, 25);
		menuL.setFont(font);
		menuL.setLocation(200, 200);
		menuLabelPane.add(menuL);

		menuSubLabelPane.setBackground(Color.getHSBColor(10, 500, 225));
		menuSubL = new JLabel("Mancala - ICS4U - Bella Arsenault - June 3, 2022");
		font2 = new Font("Skia", Font.PLAIN, 18); //noteworthy is a nice font, optima
		menuSubL.setFont(font2);
		menuSubL.setLocation(200, 200);
		menuSubLabelPane.add(menuSubL);
		
		menu.add(menuLabelPane, BorderLayout.NORTH);
		menu.add(menuSubLabelPane, BorderLayout.CENTER);
		
		menuButtonPanel = new JPanel();
		
		menuButtonPanel.setBackground(Color.getHSBColor(10, 500, 225));
		menuB = new JButton("Play Mancala");
		menuB.setLocation(500, 500); 
	    menuB.setSize(100,60);
	    menuB.setBounds(300, 300, 100, 40);
		menuB.addActionListener(this);
	    menuButtonPanel.add(menuB);
	    
	    menuButtonPanel.setBackground(Color.getHSBColor(10, 500, 225));
		menuB = new JButton("Play Mancala - Against AI");
		menuB.setLocation(500, 500); 
	    menuB.setSize(100,60);
	    menuB.setBounds(300, 300, 100, 40);
		menuB.addActionListener(this);
	    menuButtonPanel.add(menuB);
	    
	    quitB = new JButton("Quit Program");
	    quitB.setSize(100, 60);
	    quitB.addActionListener(this);
	    menuButtonPanel.add(quitB);
	    
	    menu.add(menuButtonPanel, BorderLayout.SOUTH);
	    
	    menu.setOpaque(true);
		menu.setBackground(Color.getHSBColor(10, 500, 225));
		
		try { //creating all the images
        	image = ImageIO.read(new File("mancalaBoard.png"));
        	boardHeight = image.getHeight();
        	boardWidth = image.getWidth();
        	
        	stone1 = ImageIO.read(new File("1stone.png"));
        	stone1Height = stone1.getHeight();
        	stone1Width = stone1.getWidth();
        	
        	stone2 = ImageIO.read(new File("2stones.png"));
        	twoheight = stone2.getHeight(); 
        	twowidth = stone2.getWidth();	
        	
        	stone3 = ImageIO.read(new File("3stones.png"));
        	threeHeight = stone3.getHeight();
        	threeWidth = stone3.getWidth();
        	
        	stone4 = ImageIO.read(new File("4stoneUP.png"));
        	fourheight = stone4.getHeight();
        	fourwidth = stone4.getWidth();
        	
        	stone5 = ImageIO.read(new File("5stoneUP.png"));
        	fiveheight = stone5.getHeight();
        	fivewidth = stone5.getWidth();
        	
        	stone5side = ImageIO.read(new File("5stoneSide.png"));
        	fivesidewidth = stone5side.getWidth();
        	fivesideheight = stone5side.getHeight();
        	
        	
        } catch(IOException e){
        	System.out.println("uh oh");
        }

        panel = new JPanel() {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //g.drawImage(image, 0, 0, null);
                g.drawImage(image, 0, boardHeight/2, boardWidth, boardHeight, null);
               
                int x = 0;
                int boardRow = 0;
                int stones = 0;
                
                for (int columns = 0; columns < 6; columns++) {
                	int boardColumn = columns;
                	stones = play.getStonesOnBoard(boardRow, boardColumn);
                	
                	switch (stones) {
                	case 0:
                		break;
                		
                	case 1:
                		x = -145;
                		g.drawImage(stone1, (x + (columns * 210)), 130, stone1Width, stone1Height, null);
                		break;
                		
                	case 2:
                		x = -122;
                		g.drawImage(stone2, x + (columns * 210), 155, twowidth -50, twoheight - 50, null);
                		break;
                		
                	case 3:
                		x = -100;
                		g.drawImage(stone3, x + (columns * 210), 165, threeWidth - 85, threeHeight - 85, null);
                		break;
                		
                	case 4:
                		x = -73;
                		g.drawImage(stone4, x + (columns * 210), 202, fourwidth - 145, fourheight - 145, null);
                		break;
                		
                	default:
                		x = -72;
                		g.drawImage(stone5, x + (columns * 210), 203, fivewidth -145, fiveheight - 145, null);
                		break;
                	}
                }
                
                stones = play.getStonesOnBoard(boardRow, 6);
                
                switch (stones) {
                case 0:
                	break;
                	
                case 1:
                	g.drawImage(stone1, 1130, 30, stone1Width, stone1Height, null);
                	break;
                	
                case 2:
                	g.drawImage(stone2, 1160, 60, twowidth -50, twoheight - 50, null);
                	break;
                	
                case 3:
                	g.drawImage(stone3, 1190, 70, threeWidth - 140, threeHeight - 140, null);
                	break;
                	
                case 4:
                	g.drawImage(stone4, 1213, 115, fourwidth - 165, fourheight - 165, null);
                	break;
                	
                default:	
                	 g.drawImage(stone5side, 1209, 115, fivesidewidth -155, fivesideheight - 155, null);
                	 break;
                }
                
                boardRow = 1;
                
                for (int columns = 0; columns < 6; columns++) {
                	int boardColumn = 5 - columns;
                	
                	stones = play.getStonesOnBoard(boardRow, boardColumn);
                	
                	switch (stones) {
                	case 0:
                		break;
                		
                	case 1:
                		x = -145;
                		g.drawImage(stone1, x + (columns * 210), -50, stone1Width, stone1Height, null);
                		break;
                		
                	case 2:
                		x = -122;
                		g.drawImage(stone2, x + (columns * 210), -25, twowidth -50, twoheight - 50, null);
                		break;
                		
                	case 3:
                		x = -100;
                		g.drawImage(stone3, x + (columns * 210), -15, threeWidth - 85, threeHeight - 85, null);
                		break;
                		
                	case 4:
                		x = -73;
                		g.drawImage(stone4, x + (columns * 210), 22, fourwidth - 145, fourheight - 145, null);
                		break;
                		
                	default:	
                		x = -72;
                		g.drawImage(stone5, x + (columns * 210), 23, fivewidth - 145, fiveheight - 145, null);
                		break;
                	}
                }
                
                stones = play.getStonesOnBoard(boardRow, 6);
                
                switch (stones) {
                case 0:
                	break;
                	
                case 1:
                	g.drawImage(stone1, -375, 30, stone1Width, stone1Height, null);
                	break;
                	
                case 2:
                	g.drawImage(stone2, -350, 60, twowidth -50, twoheight - 50, null);
                	break;
                	
                case 3:
                	g.drawImage(stone3, -278, 115, threeWidth - 195, threeHeight - 195, null);
                	break;
                	
                case 4:
                	g.drawImage(stone4, -292, 115, fourwidth - 165, fourheight - 165, null);
                	break;
                	
                default:	
                	 g.drawImage(stone5side, -300, 115, fivesidewidth -155, fivesideheight - 155, null);
                	 break;
                }
                
           	}                
        };

        panel.setLayout(null);
        
        this.title = new JLabel("Play Mancala");//set font for title
        this.title.setBounds(820, 0, 100, 50); //almost centered will need to change anyways once the label has had the font and size increased
        player1 = new JLabel();
        player2 = new JLabel();
        
        player1.setBounds(1600,535,100,50);
        player2.setBounds(160, 100, 100, 50);
        
        
        playerTurn = new JLabel("HELLO");
        playerTurn.setBounds(820, 100, 200, 50);
        panel.add(playerTurn);
        //player1.setText((String) JOptionPane.showInputDialog(null, "Please enter your name,", "Mancala - Player 1", JOptionPane.INFORMATION_MESSAGE, null, null, null)); //third null from the end can be made into an image at one point
        //player2.setText((String) JOptionPane.showInputDialog(null, "Please enter your name,", "Mancala - Player 2", JOptionPane.INFORMATION_MESSAGE, null, null, null)); //third null from the end can be made into an image at one point

        //playerTurn.setText("Player 1");
        //winnerStatus.setText("");
        
        quit = new JButton("Quit Game");
        quit.setBounds(820, 600, 100, 50); //create actual variables for the height/width
        quit.addActionListener(this);
        panel.add(quit);      
        
        playAgain = new JButton("Play Again");
        playAgain.setBounds(400, 600, 100, 50);
        playAgain.addActionListener(this);
        panel.add(playAgain);
        
        seeHighscores = new JButton("See High Scores");
        seeHighscores.setBounds(1190, 600, 200, 50);
        panel.add(seeHighscores);
        
        panel.add(this.title); //adding it where the bounds have been set (no label so not using any layouts like borderlayout)
        panel.add(player1);
        panel.add(player2);
        
        bHeight = 160;
        bWidth = 170;
        y = 70; //-130 -> 70 //changed by 200
        x = 0;
        for(int rows = 0; rows < 2; rows++) {
        	x = 80;
        	for (int columns = 0; columns < 6; columns++) {
        		button[rows][columns] = new JButton("");
        		button[rows][columns].setLocation(x + bWidth, y + bHeight);
        		button[rows][columns].setSize(bWidth, bHeight);
        		button[rows][columns].setOpaque(false);
        		//button[rows][columns].setOpaque(true);
        		//button[rows][columns].setBackground(SystemColor.control);
				button[rows][columns].setBorder(emptyBorder);
				if (rows == 0) {
					button[rows][columns].setToolTipText("" + play.stonesOnBoard[rows + 1][5 - columns]);

				}
				if (rows == 1) {
					button[rows][columns].setToolTipText("" + play.stonesOnBoard[rows - 1][columns]);

				}
        		//button[rows][columns].setBorder(emptyBorder);
        		button[rows][columns].addActionListener(this);
				
        		panel.add(button[rows][columns]);
        		
        		x += 210;
        	}
        	y += 180; 
        }
       
        ebWidth = 150;
        ebHeight = 320;
        
        x = -120; //check to make sure end game buttons got changed
        y = -80;//dont change the x only the y
        for (int ends = 0; ends < 2; ends++) {
        	endButton[ends] = new JButton("");
        	endButton[ends].setLocation(x + ebWidth, y + ebHeight);
        	endButton[ends].setSize(ebWidth, ebHeight);
        	endButton[ends].setOpaque(false);
			//endButton[ends].setOpaque(true);
			//endButton[ends].setBackground(SystemColor.control);
        	endButton[ends].setBorder(emptyBorder);
        	
        	endButton[ends].addActionListener(this);
			panel.add(endButton[ends]);
        	
        	
        	x += 1510; 
        	
        }


        panel.setPreferredSize(new Dimension(boardWidth, boardHeight + 400));
        
        
        
        
        gameAI = new JPanel() {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //g.drawImage(image, 0, 0, null);
                g.drawImage(image, 0, boardHeight/2, boardWidth, boardHeight, null);
               
                int x = 0;
                int boardRow = 0;
                int stones = 0;
                
                for (int columns = 0; columns < 6; columns++) {
                	int boardColumn = columns;
                	stones = play.getStonesOnBoard(boardRow, boardColumn);
                	
                	switch (stones) {
                	case 0:
                		break;
                		
                	case 1:
                		x = -145;
                		g.drawImage(stone1, (x + (columns * 210)), 130, stone1Width, stone1Height, null);
                		break;
                	
                	case 2:
                		x = -122;
                		g.drawImage(stone2, x + (columns * 210), 155, twowidth -50, twoheight - 50, null);
                		break;
                		
                	case 3:
                		x = -100;
                		g.drawImage(stone3, x + (columns * 210), 165, threeWidth - 85, threeHeight - 85, null);
                		break;
                		
                	case 4:
                		x = -73;
                		g.drawImage(stone4, x + (columns * 210), 202, fourwidth - 145, fourheight - 145, null);
                		break;
                	}
                }
                stones = play.getStonesOnBoard(boardRow, 6);
                
                switch (stones) {
                case 0:
                	break;
                	
                case 1:
                	g.drawImage(stone1, 1130, 30, stone1Width, stone1Height, null);
                	break;
                	
                case 2:
                	g.drawImage(stone2, 1160, 60, twowidth -50, twoheight - 50, null);
                	break;
                	
                case 3:
                	g.drawImage(stone3, 1190, 70, threeWidth - 140, threeHeight - 140, null);
                	break;
                	
                case 4:
                	g.drawImage(stone4, 1213, 115, fourwidth - 165, fourheight - 165, null);
                	break;
                	
                default:	
                	 g.drawImage(stone5side, 1209, 115, fivesidewidth -155, fivesideheight - 155, null);
                	 break;
                }
                
                boardRow = 1;
                
                for (int columns = 0; columns < 6; columns++) {
                	int boardColumn = 5 - columns;
                	stones = play.getStonesOnBoard(boardRow, boardColumn);
                	
                	switch (stones) {
                	case 0:
                		break;
                		
                	case 1:
                		x = -145;
                		g.drawImage(stone1, x + (columns * 210), -50, stone1Width, stone1Height, null);
                		break;
                		
                	case 2:
                		x = -122;
                		g.drawImage(stone2, x + (columns * 210), -25, twowidth -50, twoheight - 50, null);
                		break;
                		
                	case 3:
                		x = -100;
                		g.drawImage(stone3, x + (columns * 210), -15, threeWidth - 85, threeHeight - 85, null);
                		break;
                		
                	case 4:
                		x = -73;
                		g.drawImage(stone4, x + (columns * 210), 22, fourwidth - 145, fourheight - 145, null);
                		break;
                		
                	default:
                		g.drawImage(stone5, x + (columns * 210), 23, fivewidth - 145, fiveheight - 145, null);
                		break;
                	}
                }
                
                stones = play.getStonesOnBoard(boardRow, 6);
                
                switch (stones) {
                case 0:
                	break;
                	
                case 1:
                	g.drawImage(stone1, -375, 30, stone1Width, stone1Height, null);
                	break;
                	
                case 2:
                	g.drawImage(stone2, -350, 60, twowidth -50, twoheight - 50, null);
                	break;
                	
                case 3:
                	g.drawImage(stone3, -278, 115, threeWidth - 195, threeHeight - 195, null);
                	break;
                	
                case 4:
                	g.drawImage(stone4, -292, 115, fourwidth - 165, fourheight - 165, null);
                	break;
                	
                default:	
                	 g.drawImage(stone5side, -300, 115, fivesidewidth -155, fivesideheight - 155, null);
                	 break;
                }
			}         
        };

        gameAI.setLayout(null);
        
        this.title = new JLabel("Play Mancala");//set font for title
        this.title.setBounds(820, 0, 100, 50); //almost centered will need to change anyways once the label has had the font and size increased
        player1 = new JLabel();
        player2 = new JLabel();
        
        player1.setBounds(1600,535,100,50);
        player2.setBounds(160, 100, 100, 50);
        
        //player1.setText((String) JOptionPane.showInputDialog(null, "Please enter your name,", "Mancala - Player 1", JOptionPane.INFORMATION_MESSAGE, null, null, null)); //third null from the end can be made into an image at one point
        //player2.setText((String) JOptionPane.showInputDialog(null, "Please enter your name,", "Mancala - Player 2", JOptionPane.INFORMATION_MESSAGE, null, null, null)); //third null from the end can be made into an image at one point

        //playerTurn.setText("Player 1");
        //winnerStatus.setText("");
        
        playerTurnAI = new JLabel("Player 1's Turn");
        playerTurnAI.setBounds(820, 100, 200, 50);
        gameAI.add(playerTurnAI);
        
        quit = new JButton("Quit Game");
        quit.setBounds(820, 600, 100, 50); //create actual variables for the height/width
        quit.addActionListener(this);
        gameAI.add(quit);      
        
        playAgain = new JButton("Play Again");
        playAgain.setBounds(400, 600, 100, 50);
        playAgain.addActionListener(this);
        gameAI.add(playAgain);
        
        seeHighscores = new JButton("See High Scores");
        seeHighscores.setBounds(1190, 600, 200, 50);
        gameAI.add(seeHighscores);
        
        gameAI.add(this.title); //adding it where the bounds have been set (no label so not using any layouts like borderlayout)
        gameAI.add(player1);
        gameAI.add(player2);
        
        bHeight = 160;
        bWidth = 170;
        y = 70; //-130 -> 70 //changed by 200
        x = 0;
        for(int rows = 0; rows < 2; rows++) {
        	x = 80;
        	for (int columns = 0; columns < 6; columns++) {
        		button2[rows][columns] = new JButton("");
        		button2[rows][columns].setLocation(x + bWidth, y + bHeight);
        		button2[rows][columns].setSize(bWidth, bHeight);
        		button2[rows][columns].setOpaque(false);
        		//button[rows][columns].setOpaque(true);
        		//button[rows][columns].setBackground(SystemColor.control);
				button2[rows][columns].setBorder(emptyBorder);
				if (rows == 0) {
					button2[rows][columns].setToolTipText("" + play.stonesOnBoard[rows + 1][5 - columns]);

				}
				if (rows == 1) {
					button2[rows][columns].setToolTipText("" + play.stonesOnBoard[rows - 1][columns]);

				}
        		//button[rows][columns].setBorder(emptyBorder);
        		button2[rows][columns].addActionListener(this);
				
        		gameAI.add(button2[rows][columns]);
        		
        		x += 210;
        	}
        	y += 180; 
        }
       
        ebWidth = 150;
        ebHeight = 320;
        
        x = -120; //check to make sure end game buttons got changed
        y = -80;//dont change the x only the y
        for (int ends = 0; ends < 2; ends++) {
        	endButton[ends] = new JButton("");
        	endButton[ends].setLocation(x + ebWidth, y + ebHeight);
        	endButton[ends].setSize(ebWidth, ebHeight);
        	endButton[ends].setOpaque(false);
			//endButton[ends].setOpaque(true);
			//endButton[ends].setBackground(SystemColor.control);
        	endButton[ends].setBorder(emptyBorder);
        	
        	endButton[ends].addActionListener(this);
			gameAI.add(endButton[ends]);
        	
        	
        	x += 1510; 
        	
        }


        gameAI.setPreferredSize(new Dimension(boardWidth, boardHeight + 400));
        
        
        
        
       
        mainPanel.add(menu, "menu");
		mainPanel.add(panel, "game");
		mainPanel.add(gameAI, "ai");
		add(mainPanel);
		setVisible(true);
        
        add(mainPanel);
        setVisible(true);
        validate();

	}

    public static void main(String[] args) { //create a better main method
    	Frame test = new Frame("Mancala window test");
		test.setLocation(0, 0);
		test.setSize(new Dimension(boardWidth, boardHeight + 400)); 
		test.setDefaultCloseOperation(EXIT_ON_CLOSE);
		test.setVisible(true);
    	/* SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Frame().create().show();
            }
        }); */
    }
    public void actionPerformed(ActionEvent e) {  
    	if (e.getActionCommand().equals("Play Mancala")) {
			//resetGame();	//reseting game to make sure if user goes back to menu, when the opt to play the game again its a new game, and won't be a game that been played mid-way through
			changeScreen(); //changes screen to the game panel
			play.restartGame();
			player1.setText((String) JOptionPane.showInputDialog(null, "Please enter your name,", "Mancala - Player 1", JOptionPane.INFORMATION_MESSAGE, null, null, null));
	        player2.setText((String) JOptionPane.showInputDialog(null, "Please enter your name,", "Mancala - Player 2", JOptionPane.INFORMATION_MESSAGE, null, null, null));

			
		}
    	if (e.getActionCommand().equals("Quit Program") ) {
			System.exit(0); //isnt working - figure out whats going on here - figure out if there is a separate method for exiting window
		}
    	if (e.getActionCommand().equals("Quit Game") ) {
			showMenu(); //isnt working - figure out whats going on here - figure out if there is a separate method for exiting window
		}
		if (e.getActionCommand().equals("Play Again") ) {
			play.restartGame(); //isnt working - figure out whats going on here - figure out if there is a separate method for exiting window
			revalidate();
			repaint();
		}
		
		if (e.getActionCommand().equals("Play Mancala - Against AI")) {
			playAI();
			play.restartGame();
		}
		
    	for (int end = 0; end < 2; end++) {
			if (endButton[end] == e.getSource()) {
				showPopup();
			}
		}
    	
    	for (int rows = 0; rows < 2; rows++) {
    		for (int columns = 0; columns < 6; columns++) {
    			if (button[rows][columns] == e.getSource()) {
    				System.out.println("FOUND IT");
    				int row = rows;
    				int column = columns; //depending on which row would determine which column
    				//play.startGame();
    				play.twoPlayerTurn(row, column);
    				changePlayerText();
    				revalidate();
    				repaint();
    				//paintComponent();
    				
    			}
    		}
    	}
    	
    	for (int rows = 0; rows < 2; rows++) {
    		for (int columns = 0; columns < 6; columns++) {
    			if (button2[rows][columns] == e.getSource()) {
    				if (rows == 0) {
    					showAIPopup();
    				}
    				else {
    					System.out.println("FOUND IT");
        				int row = rows;
        				int column = columns; //depending on which row would determine which column
        				//play.startGame();
        				play.twoPlayerTurn(row, column);
        				changePlayerTextAI();
        				new Timer(1000, new ActionListener() {
        					@Override
							public void actionPerformed(ActionEvent e) {
        						if (ai.aiTurn()) {
        							play.setPlayer(true);
        							changePlayerTextAI();
        							revalidate();
        	        				repaint();
        							return;
        						}
        						else {
        							revalidate();
        	        				repaint();
        						}
        						//System.out.println("ai turn" + ai.aiTurn());
        					}
        				}).start();
        				//paintComponent();	
    				}
    				
    			}
    		}
    	}
    }

    public void changeScreen() {
    	cardLayout.show(mainPanel, "game");
    }
   
    public void changeText() {
    	for (int columns = 0; columns < 6; columns++) {
    		
    	}
    }
    
    public void changePlayerText() {
    	if (play.getPlayer()) {
    		playerTurn.setText("Player 1's Turn");
    	}
    	else {
    		playerTurn.setText("Player 2's Turn");
    	}
    }
    
    public void changePlayerTextAI() {
    	if (play.getPlayer()) {
    		playerTurnAI.setText("Player 1's Turn");
    	}
    	else {
    		playerTurnAI.setText("AI's Turn");
    	}
    }
    
    public void showMenu() {
    	cardLayout.show(mainPanel, "menu");
    }
    
    public void playAI() {
    	cardLayout.show(mainPanel, "ai");
    }
    
    public void showPopup() {
    	JOptionPane.showMessageDialog(panel, "You cannot play a turn from either end zone");
    }  //switch up where this is being displayed
   
    public void showAIPopup() {
    	JOptionPane.showMessageDialog(panel, "You cannot play a turn from the ai's side");
    } 
}
