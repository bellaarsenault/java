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
	private int row, column;
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
	}
	
	public void isEmpty() { //can probably delete this
		
	}
	
	public void setPlayer(boolean turn) {
		player = turn;	
	}
	
	public void swapPlayer(boolean turn) {
		System.out.println("1: " + player);
		player = !turn;
		System.out.println("2: " + player);
	}
	
	public int swapRow(int row) {
		int rowHolder;
		if (row == 0) {
			rowHolder = 1;
			return rowHolder;
		}
		else {
			rowHolder = 0;
			return rowHolder;
		}
	}
	
	public boolean isPlayer1() {
		return player;
	}
	
	private void changeRowAndColumn(int rowU, int columnU) {
		if (rowU == 1) {
			row = rowU - 1;
			column = columnU;
		}
		else {
			row = rowU + 1;
			column = buttonToStone - columnU;
		}
	}
	
	/*public int getStonesOnBoard(int row, int column) {
		return stonesOnBoard[row][column];
	} */
	
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
	
	public void twoPlayerTurn(int rowU, int columnU) {
		if (!player1Over() && !player2Over()) {
			if ((isPlayer1() && rowU == 1) || (!isPlayer1() && rowU == 0)) {
				changeRowAndColumn(rowU, columnU);	
				
				stones = stonesOnBoard[row][column];
				if (stonesOnBoard[row][column] != 0) {
					int lastColumnHolder = moveStones(row, column, stones);
					//check for stealing and then check for bonus turn
					if (!checkForBonusTurn(lastColumnHolder)) {
						swapPlayer(player);
					}
				}
			}
		}
	}
	
	public int moveStones(int row, int column, int stones) { //THIS METHOD COULD BE INHERITED TO THE AI CLASS
		int columnHolder = column + 1;
		for (stones = stonesOnBoard[row][column]; stones > 0; stones--) {
			System.out.println("og column: " + column);
			System.out.println("column: " + columnHolder);
			if (columnHolder == 6) {
				if ((isPlayer1() && row == 0) || (!isPlayer1() && row == 1)) {
					stonesOnBoard[row][columnHolder]++; //needs to be rows +1
					columnHolder = 0;
					
					row = swapRow(row);
				}
			}
			else {
				stonesOnBoard[row][columnHolder]++;
				columnHolder++;
			}
		}
		
		stonesOnBoard[row][column] = 0;
		
		return columnHolder;
	}
	
	public void checkForStealing(int lastColumn) {
		
	}
	
	public boolean checkForBonusTurn(int lastColumn) {
		if (lastColumn == 6) {
			return true;
		}
		else return false;
	}
	
	//create a couple of different ways for checking if game is over
	// of the 2D array
	/*
	public void twoPlayerTurn(int rowU, int columnU) { //either put another player method or put an if case in this method
		//returning boolean for exception handling - might change the way I am doing this later
		if (rowU == 1) {// && (player1Won() != true && player2Won() != true)) { //from the rows and columns that are inputed from the button, need to properly set the location up for the stonesOnBoard array
			if (isPlayer1() && (!player1Won() && !player2Won())) {
				this.row = rowU - 1;
				this.column = columnU;

				column = moveStones(row, column);
			
				if (!extraTurn(column)) {
					setPlayer(false);
					System.out.println("player : " + isPlayer1());
				} 
					
				else {
					//setPlayer(true);
					System.out.println("player : " + isPlayer1());
				} 
					
				if (canSteal() == true && column != 6 && stonesOnBoard[row + 1][buttonToStone - column] != 0) { //this is not stealing because the column and row is not changing
					//System.out.println("HELLO STEALING");
					stealHolder = stonesOnBoard[row + 1][buttonToStone - column];
					stonesOnBoard[0][6] += stealHolder + stonesOnBoard[row][column];
					stonesOnBoard[row][column] = 0;
					stonesOnBoard[row + 1][buttonToStone - column] = 0;
					setPlayer(false);
				}	
			}
			
			else {
				this.row = rowU - 1;
				this.column = 5 - columnU;
				column = moveStones(row, column);
				
				if (!extraTurn(column)) {
					setPlayer(false);
					System.out.println("player : " + isPlayer1());
				} 
					
				else {
					//setPlayer(true);
					System.out.println("player : " + isPlayer1());
				} 
					
				if (canSteal() == true && column != 6 && stonesOnBoard[row + 1][buttonToStone - column] != 0) { //this is not stealing because the column and row is not changing
					//System.out.println("HELLO STEALING");
					stealHolder = stonesOnBoard[row + 1][buttonToStone - column];
					stonesOnBoard[0][6] += stealHolder + stonesOnBoard[row][column];
					stonesOnBoard[row][column] = 0;
					stonesOnBoard[row + 1][buttonToStone - column] = 0;
					setPlayer(false);
				}	
			}
		}	
	}		*/	/*
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
	/*
	 private int getHomeRow() {
		    return isPlayer1() ? 0 : 1;
		  }

		  private int getOtherRow(int aRow) {
		    return (aRow + 1) % 2;
		  }

		  // Given the board position defined by row and column, distribute the stones in that pit for the
		  // current player.  Assumes the initial position is valid for the player and that the specified
		  // pit has a least 1 stone.  After completion, row and column are set to the last pit to have
		  // been given a stone.
		  private void distributeStones() {
		    int stones = stonesOnBoard[row][column];
		    stonesOnBoard[row][column] = 0;

		    int maxColumn = 6; // turn starts on home row

		    do {
		      // Advance to the next position on the board for the player.
		      if (column < maxColumn) {
		        column++;
		      } else {
		        // Reached the end of this row; switch rows.
		        row = getOtherRow(row);
		        column = 0;
		        maxColumn = (row == getHomeRow()) ? 6 : 5;
		      }

		      // Deposit a stone
		      stonesOnBoard[row][column]++;

		      stones--;
		    } while (stones > 0);

		    printBoard();
		  }
		  
		  private void stealIfPossible() {
			    int homeRow = getHomeRow();
			    if ((row == homeRow) && (column < 6) && (stonesOnBoard[row][column] == 1)) {
			      // Player's pit had been empty, so a steal is permitted.
			      final int otherRow = getOtherRow(homeRow);
			      final int oppositeColumn = 5 - column;
			      final int stonesToSteal = stonesOnBoard[otherRow][oppositeColumn];
			      if (stonesToSteal > 0) {
			        // There some stones to steal.
			        System.out.println("Stealing " + stonesToSteal + " stones");
			        stonesOnBoard[homeRow][6] += stonesToSteal + 1;
			        stonesOnBoard[otherRow][oppositeColumn] = 0;
			        stonesOnBoard[row][column] = 0;
			        printBoard();
			      }
			    }
			  }
		  
		  private void collectStones(int aRow) {
			    for (int i = 0; i < 6; i++) {
			      stonesOnBoard[aRow][6] += stonesOnBoard[aRow][i];
			      stonesOnBoard[aRow][i] = 0;
			    }
			  }
	
	 private void printBoard() {
		    for (int rows = 0; rows < 2; rows++) {
		      System.out.print("Row " + rows + ":");
		      for (int columns = 0; columns < 7; columns++) {
		        System.out.print(" " + stonesOnBoard[rows][columns]);
		      }
		      System.out.println();
		    }
		  }
	 
	 private boolean isRowEmpty(int aRow) {
		    for (int i = 0; i < 6; i++) {
		      if (stonesOnBoard[aRow][i] != 0) {
		        return false;
		      }
		    }
		    return true;
		  }
	 
	 private boolean handleEndOfGame() {
		    boolean isGameOver = false;
		    if (isRowEmpty(0)) {
		      isGameOver = true;
		      collectStones(1);
		    } else if (isRowEmpty(1)) {
		      isGameOver = true;
		      collectStones(0);
		    }
		    return isGameOver;
		  }

		  public static final int OK = 0;
		  public static final int WRONG_ROW = 1;
		  public static final int EMPTY_PIT = 2;
		  public static final int PLAYER_1_WINS = 3;
		  public static final int PLAYER_2_WINS = 4;
		  public static final int TIE_GAME = 5;
		  public static final int EXTRA_TURN = 6;

		  public int playerTurn(int boardRow, int boardColumn) {
		    // Is it a valid turn?
		    int homeRow = getHomeRow();
		    //System.out.println("playerTurn: boardRow=" + boardRow + " boardColumn=" + boardColumn + " homeRow=" + homeRow);

		    if (boardRow != homeRow) {
		      // Player clicked button on opposing players row.
		      return WRONG_ROW;
		    }

		    // Are there any stones in the selected bucket?
		    if (stonesOnBoard[boardRow][boardColumn] == 0) {
		      // Nope
		      return EMPTY_PIT;
		    }

		    row = boardRow;
		    column = boardColumn;

		    distributeStones();

		    // Check if the player can steal; take the stones if yes.
		    stealIfPossible();

		    // Check for game over.
		    if (handleEndOfGame()) {
		      System.out.println("Game is over");
		      printBoard();
		      if (stonesOnBoard[0][6] > stonesOnBoard[1][6]) {
		        return PLAYER_1_WINS;
		      } else if (stonesOnBoard[0][6] == stonesOnBoard[1][6]) {
		        return TIE_GAME;
		      } else {
		        return PLAYER_2_WINS;
		      }
		    }

		    // Check if the player gets an extra turn.
		    if (column == 6) {
		      // This player gets an extra turn.
		      return EXTRA_TURN;
		    }

		    // Switch players
		    setPlayer(!isPlayer1());

		    return OK;
		  } */

		  public int getStonesOnBoard(int boardRow, int boardColumn) {
		    int stones = stonesOnBoard[boardRow][boardColumn];
		    //System.out.println("getStonesOnBoard: boardRow=" + boardRow + " boardColumn=" + boardColumn + " stones=" + stones);
		    return stones;
		  }

		  //create a couple of different ways for checking if game is over
		  // of the 2D array
/*
		  public boolean player1Turn(int rowU, int columnU) { //either put another player method or put an if case in this method
		    //returning boolean for exception handling - might change the way I am doing this
		    //later

		    if (rowU == 1) {// && (player1Won() != true && player2Won() != true)) { //from the rows and columns that are inputed from the button, need to properly set the location up for the stonesOnBoard array
		      if (isPlayer1() && (!player1Won() && !player2Won())) {
		        // Button on player1's row of buttons (bottom row) was clicked.
		        this.row = rowU - 1;    // Board row 0 is for player 1
		        this.column = columnU;  // Button column matches board column
		        int columnHolder;
		        columnHolder = column + 1;


		        System.out.println("starting row: " + row + " starting column:  " + column);
		        stones = stonesOnBoard[row][column]; //setting stones to be the amount in the array corresponding with the button that is pressed

		        System.out.println("stones in pile:" + stones);
		        stonesOnBoard[row][column] = 0;

		        for (int rows = row; stones > 0 && rows <= 1; rows++) {
		          System.out.println("column: " + column);
		          for (int columns = columnHolder; columns < 7; columns++) {
		            //took out columns + 1
		            if (rows == 2) {
		              break;
		            }

		            stonesHolder = stonesOnBoard[rows][columns];
		            stonesOnBoard[rows][columns]++;
		            System.out.println("row : " +  rows + " column:  " + columns + " stones on Board: " + stonesOnBoard[rows][columns]);
		            stones--;
		            System.out.println("stones : " + stones);

		            if (stones == 0) { //combine into checking for stealing as well because both will happen when stones are equal to zero

		              if (!extraTurn(columns)) {
		                setPlayer(false);
		                System.out.println("player : " + isPlayer1());
		              }

		              else {
		                //setPlayer(true);
		                System.out.println("player : " + isPlayer1());
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
		          if (rows == 2) {
		            break;
		          }
		          column = 0;
		        }
		        return true;
		      }
		      else {
		        return false;
		      }
		    }
		    else {
		      if (!isPlayer1() && (!player1Won() && !player2Won())) {
		        this.row = rowU + 1;
		        this.column = buttonToStone - columnU;
		        int columnHolder;
		        columnHolder = column;

		        System.out.println("starting row: " + row + " starting column:  " + column);
		        stones = stonesOnBoard[row][column]; //setting stones to be the amount in the array corresponding with the button that is pressed

		        System.out.println("stones in pile:" + stones);
		        stonesOnBoard[row][column] = 0;

		        for (int rows = row; stones > 0 && row > 0 && rows != -1; rows--) {
		          for (int columns = column + 1; columns < 7; columns++) {
		            if (row == -1) {
		              break;
		            }

		            stonesHolder = stonesOnBoard[rows][columns];
		            stonesOnBoard[rows][columns]++;
		            System.out.println("row : "+  rows + " column:  "+ columns + " stones on Board: " + stonesOnBoard[rows][columns]);
		            stones--;
		            System.out.println("stones : " + stones);

		            if (stones == 0) { //this can also be used to check if extra turn, right before break, the player will not be reset, it will go to the other
		              if (!extraTurn(columns)) { //can steal code should be in
		                setPlayer(true);
		                System.out.println("player : " + isPlayer1());

		              }
		              else {
		                //setPlayer(false);
		                System.out.println("player : " + isPlayer1());
		              }

		              if (canSteal() == true && columns != 6 && stonesOnBoard[rows - 1][columns] != 0) {
		                stealHolder = stonesOnBoard[rows - 1][columns];
		                stonesOnBoard[1][6] += stealHolder + stonesOnBoard[rows][columns];
		                stonesOnBoard[rows][columns] = 0;
		                stonesOnBoard[rows - 1][buttonToStone - columns] = 0;
		                setPlayer(true);
		                break;

		              }

		              break;

		            }
		          }
		          if (rows == -1) {
		            break;
		          }
		          column = 0;
		        }
		        return true;
		      }
		      else {
		        return false;
		      }
		    }
		  } */
	 
	
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

	
	public boolean player1Over() { //this could be put into player 1/2 has won? - add this but as opposite to player1 for player2 - PUT THIS INTO GRAPHICS TO TEST
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
	public boolean player2Over() { //this could be put into player 1/2 has won? - add this but as opposite to player1 for player2 - PUT THIS INTO GRAPHICS TO TEST
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
					System.out.println("adding stones");
					for (int columns2 = 0; columns2 < 6; columns2++) { //adding stones on other side to other end zone
						counterHolder = stonesOnBoard[1][columns2];
					}
					stonesOnBoard[1][6] += counterHolder;
					
					for (int rows1 = 0; rows < 2; rows++) { //making sure that all rows and olumns are done
						for (int columns1 = 0; columns < 6; columns++) {
							stonesOnBoard[rows1][columns1] = 0;
						}
					}
					
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
