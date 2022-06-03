/* MancalaRules.java
 * name: bella arsenault
 * date: june 3, 2022
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
	
	public void setRowAndColumn(int rowAI, int columnAI) { //?
		System.out.println("setRow: " + row + " setColumn" + column);
		row = rowAI;
		column = columnAI;
		System.out.println("setRow: " + row + " setColumn" + column);
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
	
	private int whichPlayerWon() {
		if (stonesOnBoard[0][6] == stonesOnBoard[1][6]) {
			return 0;
		}
		
		if (stonesOnBoard[0][6] > stonesOnBoard[1][6]) {
			return 1;
		}
		else {
			return 2;
		}
	}
	
	public void restartGame() { //need to reset player
		setPlayer(true);
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
	}
	
	public int twoPlayerTurn(int rowU, int columnU) {
		if (!playerOneOver() && !playerTwoOver()) { 
			if ((isPlayer1() && rowU == 1) || (!isPlayer1() && rowU == 0)) {
				changeRowAndColumn(rowU, columnU);	
				
				stones = stonesOnBoard[row][column];
				
				if (stones != 0) {
					moveStones(stones);
					if (column != 6) {
						checkForStealing();
					}
					
					if (!checkForBonusTurn()) {
						swapPlayer(player);
					}
				}
			}
		}
		
		if (playerOneOver()) {
			endGame(1);
			return whichPlayerWon();
		}
		
		if (playerTwoOver()) {
			endGame(2);
			return whichPlayerWon();
		}
		
		return 3; //make this into an actual var that can't be changed
	}
	
	public void moveStones(int stones) { //THIS METHOD COULD BE INHERITED TO THE AI CLASS
		System.out.println("MOVING STONSE FROM MANCALA");
		stonesOnBoard[row][column] = 0;
		column++;
		while (stones > 0) {
			//System.out.println("og column: " + column);
			System.out.println("column: " + column + " row: "+ row);
			if (column == 6) {
				if ((isPlayer1() && row == 0) || (!isPlayer1() && row == 1)) {
					stonesOnBoard[row][column]++; //needs to be rows +1
				}
				
				if (stones > 1) {
					column = 0;
					row = swapRow(row);
				}
			}
			else {
				stonesOnBoard[row][column]++;
				if (stones > 1) {
					column++;
				}
			}
			stones--;
		}
	}
	
	public void checkForStealing() {
		int rowHolder;
		int initialRowHolder;
		int stealCounter;
		int stealColumnHolder;
		
		if (isPlayer1()) {
			rowHolder = 1;
			initialRowHolder = 0;
			stealColumnHolder = buttonToStone - column; //maybe put this outside
		}
		else {
			rowHolder = 0;
			initialRowHolder = 1;
			stealColumnHolder = buttonToStone - column;
		}
		System.out.println("initialRowHolder: " + initialRowHolder);
		System.out.println("rowHolder: " + rowHolder);
		System.out.println("column for stealing: " + column);
		if (stonesOnBoard[initialRowHolder][column] == 1 && stonesOnBoard[rowHolder][stealColumnHolder] != 0) {
			stealCounter = stonesOnBoard[rowHolder][stealColumnHolder];
			stonesOnBoard[rowHolder][stealColumnHolder] = 0;
			stonesOnBoard[initialRowHolder][column] = 0;
			
			stonesOnBoard[initialRowHolder][6] += stealCounter + 1;

		} 
	}
	
	public boolean checkForBonusTurn() {
		System.out.println("columninbonusturn: " + column);
		if (column == 6) {
			return true;
		}
		else return false;
	}
	
	
	public void endGame(int player) {
		int endZoneCounter = 0;
		if (player == 1) {
			for (int columns = 0; columns < 6; columns++) {
				if (stonesOnBoard[1][columns] != 0) {
					endZoneCounter += stonesOnBoard[1][columns];
					stonesOnBoard[1][columns] = 0;
				}
			}
			stonesOnBoard[1][6] += endZoneCounter;
		}
		else {
			for (int columns = 0; columns < 6; columns++) {
				if (stonesOnBoard[0][columns] != 0) {
					endZoneCounter += stonesOnBoard[0][columns];
					stonesOnBoard[0][columns] = 0;
				}
			}
			stonesOnBoard[0][6] += endZoneCounter;
		}
	}
	
	public boolean playerOneOver() {
		for (int columns = 0; columns < 6; columns++) {
			if (stonesOnBoard[0][columns] != 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean playerTwoOver() {
		for (int columns = 0; columns < 6; columns++) {
			if (stonesOnBoard[1][columns] != 0) {
				return false;
			}
		}
		return true;
	}
	
	 public void printBoard() {
		    for (int rows = 0; rows < 2; rows++) {
		      System.out.print("Row " + rows + ":");
		      for (int columns = 0; columns < 7; columns++) {
		        System.out.print(" " + stonesOnBoard[rows][columns]);
		      }
		      System.out.println();
		    }
		  }
	
	 public int getStonesOnBoard(int boardRow, int boardColumn) {
		 int stones = stonesOnBoard[boardRow][boardColumn];
		    //System.out.println("getStonesOnBoard: boardRow=" + boardRow + " boardColumn=" + boardColumn + " stones=" + stones);
		 return stones;
	 }

		 
	
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
	
}	