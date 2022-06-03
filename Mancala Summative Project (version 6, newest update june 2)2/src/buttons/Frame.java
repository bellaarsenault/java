/* Frame.java
 * name: bella arsenault
 * date: june 3, 2022
 * 
 * purpose: the purpose of the Frame class is that it makes a frame with panels in a cardlayout so that the user can switch easily between playing 2-person mancala to playing mancala against the ai
 * 
 * note - sorry that there are a lot of lines of code in this class, main method can be found at the very bottom
 * 
 * methods:
 * 
 * public void actionPerformed(ActionEvent e)
 * private void aiTurnMain()
 * changeScreen()
 * changePlayerText()
 * changePlayerTextAI()
 * changeStoneText()
 * changeStoneTextAI()
 * checkForWin()
 * showMenu()
 * playAI()
 * showWinnerPopup()
 * showTiePopup()
 * showPopup()
 * showAIPopup()
 * 
 * 
 * COME BACK AND ADD FINAL COMMENTS/CHECK VARIABLE NAMES
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
	private JLabel[][] stoneNums2 = new JLabel[2][6];

	private JLabel[] endStoneNums = new JLabel[2];
	private JLabel[] endStoneNums2 = new JLabel[2];

	private JLabel title, playerTurn, player1, player2, playerTurnAI, player1AI, player2AI;
	private JButton quit, playAgain, playAgainAI, menuB, quitB; 
	private CardLayout cardLayout;
	private String winnerHolder;
	private int winnerStatusNum;
	Border emptyBorder = BorderFactory.createEmptyBorder();
	MancalaRules play = new MancalaRules();
	AI ai = new AI();

	public Frame(String title) {
		super(title);

		/************
		 * 
		 ************/
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		menuLabelPane = new JPanel();
		menuLabelPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); //creating a border because without the border when everything is added to the main "screens" there isn't any space underneath each of the panels that are holding the main label, and the sub label

		menuSubLabelPane = new JPanel();
		menuSubLabelPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 160, 20)); //need border created so that when they are added to the layout there will be space in between the separate panels 

		menu = new JPanel();

		menuLabelPane.setBackground(Color.getHSBColor(10, 500, 225)); 
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

		/************
		 * tlak about use of revalidate and repaint else where in the code that relates to how it works here
		 ************/
		panel = new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
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
					g.drawImage(stone3, 1232, 115, threeWidth - 195, threeHeight - 195, null);
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

		/************
		 * 
		 ************/
		this.title = new JLabel("Play Mancala");//set font for title
		this.title.setBounds(820, 0, 100, 50); //almost centered will need to change anyways once the label has had the font and size increased
		player1 = new JLabel();
		player2 = new JLabel();

		player1.setBounds(1600,535,100,50);
		player1.setOpaque(false);
		player2.setBounds(90, 535, 100, 50);
		player2.setOpaque(false);


		playerTurn = new JLabel("HELLO");
		playerTurn.setBounds(820, 100, 200, 50);
		panel.add(playerTurn);

		quit = new JButton("Quit Game");
		quit.setBounds(820, 600, 100, 50); 
		quit.addActionListener(this);
		panel.add(quit);      

		playAgain = new JButton("Play Again");
		playAgain.setBounds(400, 600, 100, 50);
		playAgain.addActionListener(this);
		panel.add(playAgain);

		panel.add(this.title); 
		panel.add(player1);
		panel.add(player2);

		y = 180;
		for (int rows = 0; rows < 2; rows++) {
			x = 200;
			for (int columns = 0; columns < 6; columns++) {
				stoneNums[rows][columns] = new JLabel("");
				stoneNums[rows][columns].setSize(50, 50);
				stoneNums[rows][columns].setLocation(x + 50, y + 50);
				stoneNums[rows][columns].setOpaque(false);

				panel.add(stoneNums[rows][columns]);

				x += 210;
			}
			y += 180;
		}

		y = 180;
		x = -10;
		for (int columns = 0; columns < 2; columns++) {
			endStoneNums[columns] = new JLabel("");
			endStoneNums[columns].setSize(50, 50);
			endStoneNums[columns].setLocation(x + 50, y + 50);
			endStoneNums[columns].setOpaque(false);

			panel.add(endStoneNums[columns]);

			x += 1500;
		}

		/************
		 * In the for loop below I had originally
		 * tried to use tooltip for displaying the number 
		 * stones in each pile but it ended up making more sense
		 * to just have an array of labels so I could see what
		 * was going on on the board  
		 ************/
		bHeight = 160;
		bWidth = 170;
		y = 70; 
		x = 0;
		for (int rows = 0; rows < 2; rows++) {
			x = 80;
			for (int columns = 0; columns < 6; columns++) {
				button[rows][columns] = new JButton("");
				button[rows][columns].setLocation(x + bWidth, y + bHeight);
				button[rows][columns].setSize(bWidth, bHeight);
				button[rows][columns].setOpaque(false);
				button[rows][columns].setBorder(emptyBorder);
				if (rows == 0) {
					//button[rows][columns].setToolTipText("" + play.stonesOnBoard[rows + 1][5 - columns]); 

				}
				if (rows == 1) {
					//button[rows][columns].setToolTipText("" + play.stonesOnBoard[rows - 1][columns]);

				}
				button[rows][columns].addActionListener(this);

				panel.add(button[rows][columns]);

				x += 210;
			}
			y += 180; 
		}

		ebWidth = 150;
		ebHeight = 320;

		/************
		 * the end button array is only really made for checking to make sure that the user
		 * isn't trying to play in an end zone, if the user does try than a popup will show 
		 * up to let them know that the move they are trying to make is not valid
		 ************/
		x = -120; 
		y = -80;
		for (int ends = 0; ends < 2; ends++) {
			endButton[ends] = new JButton("");
			endButton[ends].setLocation(x + ebWidth, y + ebHeight);
			endButton[ends].setSize(ebWidth, ebHeight);
			endButton[ends].setOpaque(false);
			endButton[ends].setBorder(emptyBorder);

			endButton[ends].addActionListener(this);
			panel.add(endButton[ends]);


			x += 1510; 

		}


		panel.setPreferredSize(new Dimension(boardWidth, boardHeight + 400));



		/************
		 * 
		 ************/
		gameAI = new JPanel() {
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
					stones = ai.getStonesOnBoard(boardRow, boardColumn);

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
				stones = ai.getStonesOnBoard(boardRow, 6);

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
					g.drawImage(stone3, 1232, 115, threeWidth - 195, threeHeight - 195, null);
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
					stones = ai.getStonesOnBoard(boardRow, boardColumn);

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

				stones = ai.getStonesOnBoard(boardRow, 6);
				//System.out.println("ai stones col 6: " + ai.getStonesOnBoard(boardRow, 6));

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

		/************
		 * 
		 ************/
		this.title = new JLabel("Play Mancala");//set font for title
		this.title.setBounds(820, 0, 100, 50); //almost centered will need to change anyways once the label has had the font and size increased
		player1AI = new JLabel();
		player2AI = new JLabel("AI");

		player1AI.setBounds(1600,535,100,50);
		player2AI.setBounds(90, 535, 100, 50);

		playerTurnAI = new JLabel("Player 1's Turn");
		playerTurnAI.setBounds(820, 100, 200, 50);
		gameAI.add(playerTurnAI);

		quit = new JButton("Quit Game");
		quit.setBounds(820, 600, 100, 50); 
		quit.addActionListener(this);
		gameAI.add(quit);      

		playAgainAI = new JButton("Play Again - Against AI");
		playAgainAI.setBounds(400, 600, 200, 50);
		playAgainAI.addActionListener(this);
		gameAI.add(playAgainAI);

		gameAI.add(this.title); 
		gameAI.add(player1AI);
		gameAI.add(player2AI);

		y = 180;
		for (int rows = 0; rows < 2; rows++) {
			x = 200;
			for (int columns = 0; columns < 6; columns++) {
				stoneNums2[rows][columns] = new JLabel("");
				stoneNums2[rows][columns].setSize(50, 50);
				stoneNums2[rows][columns].setLocation(x + 50, y + 50);
				stoneNums2[rows][columns].setOpaque(false);

				gameAI.add(stoneNums2[rows][columns]);

				x += 210;
			}
			y += 180;
		}

		y = 180;
		x = -10;
		for (int columns = 0; columns < 2; columns++) {
			endStoneNums2[columns] = new JLabel("");
			endStoneNums2[columns].setSize(50, 50);
			endStoneNums2[columns].setLocation(x + 50, y + 50);
			endStoneNums2[columns].setOpaque(false);

			gameAI.add(endStoneNums2[columns]);

			x += 1500;
		}

		bHeight = 160;
		bWidth = 170;
		y = 70; 
		x = 0;
		for(int rows = 0; rows < 2; rows++) {
			x = 80;
			for (int columns = 0; columns < 6; columns++) {
				button2[rows][columns] = new JButton("");
				button2[rows][columns].setLocation(x + bWidth, y + bHeight);
				button2[rows][columns].setSize(bWidth, bHeight);
				button2[rows][columns].setOpaque(false);
				button2[rows][columns].setBorder(emptyBorder);
				if (rows == 0) {
					button2[rows][columns].setToolTipText("" + play.stonesOnBoard[rows + 1][5 - columns]);

				}
				if (rows == 1) {
					button2[rows][columns].setToolTipText("" + play.stonesOnBoard[rows - 1][columns]);

				}
				button2[rows][columns].addActionListener(this);

				gameAI.add(button2[rows][columns]);

				x += 210;
			}
			y += 180; 
		}

		ebWidth = 150;
		ebHeight = 320;

		x = -120; 
		y = -80;
		for (int ends = 0; ends < 2; ends++) {
			endButton[ends] = new JButton("");
			endButton[ends].setLocation(x + ebWidth, y + ebHeight);
			endButton[ends].setSize(ebWidth, ebHeight);
			endButton[ends].setOpaque(false);
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
	
	public void actionPerformed(ActionEvent e) {  
		if (e.getActionCommand().equals("Play Mancala")) {
			changeScreen(); 
			play.restartGame();
			changeStoneText();
			player1.setText((String) JOptionPane.showInputDialog(null, "Please enter your name,", "Mancala - Player 1", JOptionPane.INFORMATION_MESSAGE, null, null, null));
			player2.setText((String) JOptionPane.showInputDialog(null, "Please enter your name,", "Mancala - Player 2", JOptionPane.INFORMATION_MESSAGE, null, null, null));


		}
		if (e.getActionCommand().equals("Quit Program") ) {
			System.exit(0); 
		}
		if (e.getActionCommand().equals("Quit Game") ) {
			showMenu(); 
		}
		if (e.getActionCommand().equals("Play Again") ) {
			play.restartGame(); 
			changePlayerText();
			changeStoneText();
			revalidate();
			repaint();
		}
		
		if (e.getActionCommand().equals("Play Again - Against AI")) {
			ai.restartGame();
			changePlayerTextAI();
			changeStoneTextAI();
			revalidate();
			repaint();
		}

		if (e.getActionCommand().equals("Play Mancala - Against AI")) {
			playAI();
			player1AI.setText((String) JOptionPane.showInputDialog(null, "Please enter your name,", "Mancala - Player 1", JOptionPane.INFORMATION_MESSAGE, null, null, null));
			ai.restartGame();
			changeStoneTextAI();
		}

		for (int end = 0; end < 2; end++) {
			if (endButton[end] == e.getSource()) {
				showPopup();
			}
		}

		for (int rows = 0; rows < 2; rows++) {
			for (int columns = 0; columns < 6; columns++) {
				if (button[rows][columns] == e.getSource()) {
					//System.out.println("FOUND IT");
					int row = rows; //is there a reason I can't just use rows and column
					int column = columns; 

					winnerStatusNum = play.twoPlayerTurn(row, column);
					checkForWin();
					//System.out.println("winner status" + winnerStatusNum);
					play.printBoard();
					changePlayerText();
					changeStoneText();
					revalidate();
					repaint();

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
						//System.out.println("FOUND IT");
						int row = rows;
						int column = columns; 
						
						System.out.println("player: " + ai.isPlayer1());
						if (!ai.player1RanOutOfStones() && !ai.aiRanOutOfStones()) {
							ai.twoPlayerTurn(row, column); //changes player within the player turn
							//ai.setPlayer(false);
						} 

						revalidate();
						repaint();
						changePlayerTextAI();
						changeStoneTextAI();
						
						System.out.println("player time 1: " + ai.isPlayer1());
						if (!ai.isPlayer1()) {
							aiTurnMain();
							ai.setPlayer(true);
							changePlayerTextAI();
						}
						
						System.out.println("player2: " + ai.isPlayer1());
					}

				}
			}
		}
	}
	
	/**************
	 * aiTurnMain()
	 * Originally when trying to get the ai turn to work with a little bit of delay, 
	 * both delays would go at the same time if the ai had a bonus turn because there
	 * was a problem with the logic that interacted with the timer
	 * 
	 * making the aiTurn mecanics into a separate method and using recursion makes the
	 * program a lot easier to read and works way better
	 * 
	 * when ai.aiHasBonusTurn is true (its value is set in aiTurn), aiTurnMain() will call
	 * itself which resets the timer and continues to loop through the ai playing
	 * until it is no longer the ai's turn, the in the actioned performed method right
	 * above, player will be changed, and the turn surrounding who's turn it is
	 **************/
	private void aiTurnMain() { 
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ai.aiTurn();
				ai.aiPlayMove();
				ai.printBoard();
				changeStoneTextAI();
				revalidate();
				repaint();

				if (ai.aiHasBonusTurn) {
					aiTurnMain(); //calling itself here
				}
			}
		});
		timer.setRepeats(false);
		timer.start();
	}

	private void changeScreen() {
		cardLayout.show(mainPanel, "game");
	}

	private void changePlayerText() {
		if (play.isPlayer1()) {
			playerTurn.setText("Player 1's Turn");
		}
		else {
			playerTurn.setText("Player 2's Turn");
		}
	}
	
	private void changePlayerTextAI() {
		if (ai.isPlayer1()) {
			playerTurnAI.setText("Player 1's Turn");
		}
		else {
			playerTurnAI.setText("AI's Turn");
		}
	}


	private void changeStoneText() {
		for (int rows = 0; rows < 2; rows++) {
			for (int columns = 0; columns < 6; columns++) {
				if (rows == 0) {
					stoneNums[rows][columns].setText("" + play.stonesOnBoard[rows + 1][5 - columns]);
				}		
				else {
					stoneNums[rows][columns].setText("" + play.stonesOnBoard[rows - 1][columns]);
				}
			}
		}
		endStoneNums[0].setText("" + play.stonesOnBoard[1][6]);
		endStoneNums[1].setText("" + play.stonesOnBoard[0][6]);
	}

	private void changeStoneTextAI() {
		for (int rows = 0; rows < 2; rows++) {
			for (int columns = 0; columns < 6; columns++) {
				if (rows == 0) {
					stoneNums2[rows][columns].setText("" + ai.stonesOnBoard[rows + 1][5 - columns]);
				}		
				else {
					stoneNums2[rows][columns].setText("" + ai.stonesOnBoard[rows - 1][columns]);
				}
			}
		}
		endStoneNums2[0].setText("" + ai.stonesOnBoard[1][6]);
		endStoneNums2[1].setText("" + ai.stonesOnBoard[0][6]);
	}

	private void checkForWin() {
		if (winnerStatusNum != 3) {
			if (winnerStatusNum == 1) {
				winnerHolder = player1.getText();
				showWinnerPopup();
			}
			else if (winnerStatusNum == 2) {
				winnerHolder = player2.getText();
				showWinnerPopup();
			}
			else {
				showTiePopup();
			}
		}
	}

	private void showMenu() {
		cardLayout.show(mainPanel, "menu");
	}

	private void playAI() {
		cardLayout.show(mainPanel, "ai");
	}

	private void showWinnerPopup() {
		JOptionPane.showMessageDialog(panel, winnerHolder + " won the game! "
				+ "\nThe score was " + player1.getText() + " had " + play.stonesOnBoard[0][6] + " stones vs. " + player2.getText() + " had " + play.stonesOnBoard[1][6]);
	}

	private void showTiePopup() {
		JOptionPane.showMessageDialog(panel," The game ended in a tie! "
				+ "\nThe score was " + player1.getText() + " had " + play.stonesOnBoard[0][6] + " stones vs. " + player2.getText() + " had " + play.stonesOnBoard[1][6]);
	}

	private void showPopup() {
		JOptionPane.showMessageDialog(panel, "You cannot play a turn from either end zone");
	}  

	private void showAIPopup() {
		JOptionPane.showMessageDialog(panel, "You cannot play a turn from the ai's side");
	} 
	
	public static void main(String[] args) { 
		Frame test = new Frame("Mancala window test");
		test.setLocation(0, 0);
		test.setSize(new Dimension(boardWidth, boardHeight + 400)); 
		test.setDefaultCloseOperation(EXIT_ON_CLOSE);
		test.setVisible(true);
	}
}
