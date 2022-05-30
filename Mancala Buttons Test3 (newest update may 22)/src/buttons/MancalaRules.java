/* MancalaRules.java
 * name: bella arsenault
 * date:
 * 
 * purpose:
 * 
 * methods:
 * 
 */
package buttons;

public class MancalaRules { //updated mancala rules
	private int row;
	private int column;
	protected int[][] stonesOnBoard = new int[2][7]; //need to fill this with fours
	private int counter, counterHolder, counter2;
	private int stones; //value stored for stones in each pile
	private int buttonToStone = 5;
	private boolean player;
	private int stonesHolder, stealHolder; //dont actually need rowHolder
	
	public MancalaRules() {
		for (int rows = 0; rows < 2; rows++) {
			for (int columns = 0; columns < 7; columns++) {
				if (columns == 6) {
					stonesOnBoard[rows][columns] = 0;
				}
				else {
					stonesOnBoard[rows][columns] = 1;
				}
			}
		}
		counter = 0;
		counter2 = 0;
		player = true; // player 1 will be true and player two will be false
		
		/*
		for (int rows = 0; rows < 2; rows++) {
			for (int columns = 0; columns < 6; columns++) {
				if ((rows == 0 && columns == 0) || (rows == 1 && columns == 6)) {
					stonesOnBoard[rows][columns] = 0;
				}
				stonesOnBoard[rows][columns] = 4;
			}
		} */
		
		
	}
	
	public void isEmpty() {
		
	}
	
	public void setPlayer(boolean turn) {
		player = turn;	
	}
	
	public boolean getPlayer() {
		return player;
	}
	
	public int getStonesOnBoard(int row, int column) {
		return stonesOnBoard[row][column];
	}
	
	public void restartGame() { //need to reset player
		for (int rows = 0; rows < 2; rows++) {
			for (int columns = 0; columns < 7; columns++) {
				if (columns == 6) {
					stonesOnBoard[rows][columns] = 0;
				}
				else {
					stonesOnBoard[rows][columns] = 1;
				}
			}
		}
		setPlayer(true);
	}
	
	//create a couple of different ways for checking if game is over
	// of the 2D array
	
	public void twoPlayerTurn(int rowU, int columnU) { //either put another player method or put an if case in this method
		//returning boolean for exception handling - might change the way I am doing this later
		if (rowU == 1) {// && (player1Won() != true && player2Won() != true)) { //from the rows and columns that are inputed from the button, need to properly set the location up for the stonesOnBoard array
			if (getPlayer() && (!player1Won() && !player2Won())) {
				this.row = rowU - 1;
				this.column = columnU;
				int columnCounter = 0;
				//columnHolder = column + 1;

				
				System.out.println("starting row: " + row + " starting column:  " + column);
				stones = stonesOnBoard[row][column]; //setting stones to be the amount in the array corresponding with the button that is pressed
				
				System.out.println("stones in pile:" + stones);
				stonesOnBoard[row][column] = 0; 
				
				int columns;
				for (int rows = row; stones > 0 && rows <= 1; rows++) {
					columns = 0;
					System.out.println("column: " + column);
					for (columns = column + 1; columns < 7; columns++) { //need to start at pos after the one that was emptied, but then columns needs to go back to zero
						if (columnCounter != 0) {
							columns--;
							System.out.println("STONES -- " + stones);
							stonesHolder = stonesOnBoard[rows][columns];
							stonesOnBoard[rows][columns]++;
							System.out.println("row : " +  rows + " column:  " + columns + " stones on Board: " + stonesOnBoard[rows][columns]);
							stones--;
							System.out.println("stones : " + stones);
							
							
							if (stones == 0) { //combine into checking for stealing as well because both will happen when stones are equal to zero

								if (!extraTurn(columns)) {
									setPlayer(false);
									System.out.println("player : " + getPlayer());
								} 
								
								else {
									//setPlayer(true);
									System.out.println("player : " + getPlayer());
								} 
								
								if (canSteal() == true && column != 6 && stonesOnBoard[rows + 1][buttonToStone - columns] != 0) {
									//System.out.println("HELLO STEALING");
									stealHolder = stonesOnBoard[rows + 1][buttonToStone - columns];
									stonesOnBoard[0][6] += stealHolder + stonesOnBoard[rows][columns];
									stonesOnBoard[rows][columns] = 0;
									stonesOnBoard[rows + 1][buttonToStone - columns] = 0;
									setPlayer(false);
								}
							}	
							columnCounter++;
							columns++; 
							break;
						}
						//took out columns + 1
						if (rows == 2) {
							break;
						}
						
						stonesHolder = stonesOnBoard[rows][columns];
						stonesOnBoard[rows][columns]++;
						System.out.println("row : " +  rows + " column:  " + columns + " stones on Board: " + stonesOnBoard[rows][columns]);
						stones--;
						System.out.println("stones : " + stones);
						
						int rowsSee = rows + 1;
						System.out.println("ROWS HErE" + rowsSee);
						if (stones == 0) { //combine into checking for stealing as well because both will happen when stones are equal to zero

							if (!extraTurn(columns)) {
								setPlayer(false);
								System.out.println("player : " + getPlayer());
							} 
							
							else {
								//setPlayer(true);
								System.out.println("player : " + getPlayer());
							}
							
							
							if (canSteal() == true && column != 6 && stonesOnBoard[rows + 1][buttonToStone - columns] != 0) {
								//System.out.println("HELLO STEALING");
								stealHolder = stonesOnBoard[rows + 1][buttonToStone - columns];
								stonesOnBoard[0][6] += stealHolder + stonesOnBoard[rows][columns];
								stonesOnBoard[rows][columns] = 0;
								stonesOnBoard[rows + 1][buttonToStone - columns] = 0;
								setPlayer(false);
							}
							
							break;
						}
					}
					//columns = 0;
					if (rows == 2) {
						break;
					}
					column = 0;
					columnCounter++;
				}
				
				//return true;
			}
			//else {
				//return false;
			//}
		}
	}
	
/*
	public void onePlayerTurn(int uColumn) {
		if (rowU == 1) {// && (player1Won() != true && player2Won() != true)) { //from the rows and columns that are inputed from the button, need to properly set the location up for the stonesOnBoard array
			if (getPlayer() && (!player1Won() && !player2Won())) {
				this.row = rowU - 1;
				this.column = columnU;
				int columnCounter = 0;
				//columnHolder = column + 1;

				
				System.out.println("starting row: " + row + " starting column:  " + column);
				stones = stonesOnBoard[row][column]; //setting stones to be the amount in the array corresponding with the button that is pressed
				
				System.out.println("stones in pile:" + stones);
				stonesOnBoard[row][column] = 0; 
				
				int columns;
				for (int rows = row; stones > 0 && rows <= 1; rows++) {
					columns = 0;
					System.out.println("column: " + column);
					for (columns = column + 1; columns < 7; columns++) { //need to start at pos after the one that was emptied, but then columns needs to go back to zero
						if (columnCounter != 0) {
							columns--;
							System.out.println("STONES -- " + stones);
							stonesHolder = stonesOnBoard[rows][columns];
							stonesOnBoard[rows][columns]++;
							System.out.println("row : " +  rows + " column:  " + columns + " stones on Board: " + stonesOnBoard[rows][columns]);
							stones--;
							System.out.println("stones : " + stones);
							
							
							if (stones == 0) { //combine into checking for stealing as well because both will happen when stones are equal to zero

								if (!extraTurn(columns)) {
									setPlayer(false);
									System.out.println("player : " + getPlayer());
								} 
								
								else {
									//setPlayer(true);
									System.out.println("player : " + getPlayer());
								} 
								
								if (canSteal() == true && column != 6 && stonesOnBoard[rows + 1][buttonToStone - columns] != 0) {
									//System.out.println("HELLO STEALING");
									stealHolder = stonesOnBoard[rows + 1][buttonToStone - columns];
									stonesOnBoard[0][6] += stealHolder + stonesOnBoard[rows][columns];
									stonesOnBoard[rows][columns] = 0;
									stonesOnBoard[rows + 1][buttonToStone - columns] = 0;
									setPlayer(false);
								}
							}	
							columnCounter++;
							columns++; 
							break;
						}
						//took out columns + 1
						if (rows == 2) {
							break;
						}
						
						stonesHolder = stonesOnBoard[rows][columns];
						stonesOnBoard[rows][columns]++;
						System.out.println("row : " +  rows + " column:  " + columns + " stones on Board: " + stonesOnBoard[rows][columns]);
						stones--;
						System.out.println("stones : " + stones);
						
						int rowsSee = rows + 1;
						System.out.println("ROWS HErE" + rowsSee);
						if (stones == 0) { //combine into checking for stealing as well because both will happen when stones are equal to zero

							if (!extraTurn(columns)) {
								setPlayer(false);
								System.out.println("player : " + getPlayer());
							} 
							
							else {
								//setPlayer(true);
								System.out.println("player : " + getPlayer());
							}
							
							
							if (canSteal() == true && column != 6 && stonesOnBoard[rows + 1][buttonToStone - columns] != 0) {
								//System.out.println("HELLO STEALING");
								stealHolder = stonesOnBoard[rows + 1][buttonToStone - columns];
								stonesOnBoard[0][6] += stealHolder + stonesOnBoard[rows][columns];
								stonesOnBoard[rows][columns] = 0;
								stonesOnBoard[rows + 1][buttonToStone - columns] = 0;
								setPlayer(false);
							}
							
							break;
						}
					}
					//columns = 0;
					if (rows == 2) {
						break;
					}
					column = 0;
					columnCounter++;
				}
				
				return true;
			}
			else {
				return false;
			}
		}
	}
	 */
	
	public boolean extraTurn(int columns) { //will only check the column and in this case, if returns false then would not change player, else would change
		if (columns == 6) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean canSteal() {
		if (stonesHolder == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	
	public boolean player1Won() { //this could be put into player 1/2 has won? - add this but as opposite to player1 for player2 - PUT THIS INTO GRAPHICS TO TEST
		counter = 0;
		for (int rows = 0; rows < 1; rows++) {
			for (int columns = 0; columns < 6; columns++) {
				
				if (rows == 1 && columns == 6) {
					//end zone dont want it to do anything
					break; //I just want this to be skipped
				}
				
				if ((rows != 1 && columns != 6 && stones <= 6) && rows == 0) {
					if (stonesOnBoard[rows][columns] == 0) {
						counter++;
						System.out.println("counter: " + counter);
						/*if (counter == 6) {
							break;
						} */
					}
				}
				
				/*if (rows == 1) {
					if (stonesOnBoard[rows][columns] == 0) {
						counter++;
					}
				} */
				
				if (counter == 6) {
					System.out.println("Game Over!");
					for (int columns2 = 0; columns2 < 6; columns2++) { //adding stones on other side to other end zone
						counterHolder = stonesOnBoard[1][columns2];
					}
					stonesOnBoard[1][6] += counterHolder;
					return true;
				}
					/*if (stonesOnBoard[rows][columns] == 0) {
					counter++;
				} */
					
			}
		}		
		
		/*if (counter != 6) { //if a counter on either side
			for (int columns = 0; columns < 6; columns++) { //adding stones on other side to other end zone
				counterHolder = stonesOnBoard[1][columns];
			}
			stonesOnBoard[1][6] += counterHolder;
			return false;
		} */
		
		return false; //does this actually work?
			
	}
	public boolean player2Won() { //this could be put into player 1/2 has won? - add this but as opposite to player1 for player2 - PUT THIS INTO GRAPHICS TO TEST
		counter2 = 0;
		for (int rows = 1; rows < 2; rows++) {
			for (int columns = 0; columns < 6; columns++) {
				
				if (rows == 1 && columns == 6) {
					//end zone dont want it to do anything
					break; //I just want this to be skipped
				}
				
				if ((rows != 0 && columns != 6 && stones <= 6) && rows == 1) {
					if (stonesOnBoard[rows][columns] == 0) {
						counter2++;
						System.out.println("counter1: " + counter);

						/*if (counter == 6) {
							break;
						} */
					}
				}
				
				/*if (rows == 1) {
					if (stonesOnBoard[rows][columns] == 0) {
						counter++;
					}
				} */
				
				if (counter2 == 6) {
					for (int columns2 = 0; columns2 < 6; columns2++) { //adding stones on other side to other end zone
						counterHolder = stonesOnBoard[1][columns2];
					}
					stonesOnBoard[1][6] += counterHolder;
					//break;
					return true;
				}
					/*if (stonesOnBoard[rows][columns] == 0) {
					counter++;
				} */
					
			}
		}
		
		/*
		if (counter2 != 0) { //if a counter on either side
			for (int columns = 0; columns < 6; columns++) { //adding stones on other side to other end zone
				counterHolder = stonesOnBoard[1][columns];
			}
			stonesOnBoard[1][6] += counterHolder;
			return false;
		} */
		
		return false; //does this actually work?
			
	}
	
	
}
