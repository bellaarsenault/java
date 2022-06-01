/* AI.java
 * name: bella arsenault
 * date:
 * 
 * purpose:
 * 
 * methods:
 * 
 */
package buttons;

//import java.util.ArrayList;

public class AI extends MancalaRules{ //would pass in stone array from mancala rules
// need method for checking if anywhere on their own side and then if any of the
//need the stones on board passed in in each method that the ai is going through
	//FIX ARRANGEMENT OF THIS
	int aiRow; //change var names
	int opponentRow = 0;
	int counter, stealCounter;
	int[] columnsHolder = new int[8]; //change later AND LEARN HOW TO USE ARRAYS
	boolean canSteal = false;
	int[] stonesCanGain = new int[6];
	int[] stonesCanBeStolen = new int[6];
	//ArrayList<Integer> stonesCanGain = new ArrayList<Integer>(); 
	//ArrayList<Integer> stonesCanBeStolen
	//= new int[], stonesCanBeStolen;// chanfe to array list because don;t know how many will be added
	int columnBestSteal, bestStealNum, stonesStolen, columnBestStolen;
	int aiStones, rows, columns, stonesCounter;
	
	
	
	public AI() {
		bestStealNum = 0;
		columnBestSteal = 0;
		aiRow = 1;
		counter = 0;
		stealCounter = 0;
		columnBestSteal = 0;
		bestStealNum = 0;
		stonesStolen = 0;
		columnBestStolen = 0;
		aiStones = 0;
		rows = 0;
		columns = 0;
		stonesCounter = 0;
		
	}
	
	//return true if ai's turn again, return false if it is the player's turn
	public boolean aiTurn() { //this is checking if it can steal, wont need to worry about
		System.out.println("can ai steal: " + canAISteal());
		System.out.println("can player steal " + canOpponentSteal());
		if (!player1RanOutOfStones() && !aiRanOutOfStones()) {
			if (canAISteal() == false && canOpponentSteal() == false) {
				//check for bonus turn, if no bonus turn then check for piles with one stone, if none than play the pile with the biggest number of stones in it
				if (haveBonusTurn() != 7) { //IF USER CAN PLAY A BONUS TURN
					System.out.println("have bonus turn: " + haveBonusTurn());
					rows = aiRow; //setting initially, if done in the loop, will be reset everytime and I don't want that to happen
					columns = haveBonusTurn() + 1;
					moveStones(columns, haveBonusTurn());
					return false;
				}
				else if (checkOneStone() != 6){ //if user can't play a bonus turn than check to see if any of the piles have one stone in the pile
					System.out.println("check one stone: " + checkOneStone());
					columns = checkOneStone() + 1;
					stonesOnBoard[aiRow][columns]++;
					stonesOnBoard[aiRow][checkOneStone()] = 0;
					return true;
				}
				else {
					System.out.println("greatestPile: " + getGreatestPile());
					rows = aiRow;
					columns = getGreatestPile() + 1;
					moveStones(columns, getGreatestPile());
					return true;
				}
			}
			else if (canAISteal() && !canOpponentSteal()) { //if ai can steal but opponent can't
				System.out.println("Can steal ai and can't steal player");
				columns = columnBestSteal + 1;
				moveStones(columns, columnBestSteal);
				return true;
			}
			else if (!canAISteal() && canOpponentSteal()) { //if opponent can steal but ai can't
				System.out.println("ai can't steal but player can");
				columns = columnBestStolen + 1;
				moveStones(columns, columnBestStolen);
				return true;
			}
			else {
				System.out.println("both player and ai can steal");
				if (bestStealNum >= stonesStolen) {
					System.out.println("ai going to steal");
					columns = columnBestSteal + 1;
					moveStones(columns, columnBestSteal);
					return true;
				}	
				else {
					System.out.println("ai going defensive");
					columns = columnBestStolen + 1;
					moveStones(columns, columnBestStolen);
					return true;
				}
			}
						
		} 
		else return false; 
	}
	
	public void moveStones(int columns, int columnHolder) {
		rows = 1;
		System.out.println("columnHolder: " + columnHolder);
		for (aiStones = stonesOnBoard[aiRow][haveBonusTurn()]; aiStones > 0; aiStones--) {
			System.out.println("row1: " + rows);
			System.out.println("ai stones:" + aiStones);
			System.out.println("columns:" + columns);
			if (columns > 6) {
				System.out.println("AHHHH");
			}
			else if (columns == 6) {
				System.out.println("row2: " + rows);
				//row will need to change and column needs to be adjusted to go back to zero
				stonesOnBoard[rows][columns]++;
				System.out.println("stones" + stonesOnBoard[rows][columns]);
				columns = 0;
				
				if (rows == 1) {
					rows = 0;
				}
				else {
					rows = 1;
				}
				System.out.println("row3: " + rows);
			}
			else {
				System.out.println("row4: " + rows);
				stonesOnBoard[rows][columns]++;
				columns++;
			}
		}
		stonesOnBoard[aiRow][columnHolder] = 0;
	}
	
	public int getStealCounter() {
		return bestStealNum;
	}
	
	public int getStealPos() {
		return columnBestSteal;
	}
	
	public int getDenfenceCounter() {
		return 1;
	}
	
	public int getDefencePos() {
		return 1;
	}
	
	public int getBonusTurnPos() {
		return 1;
	}
	
	public int checkOneStone() {
		for (int columns = 0; columns < 6; columns++) {
			if (stonesOnBoard[aiRow][columns] == 1) {
				return columns;
			}
		}
		return 6;

	}
	
	public int getGreatestPile() {
		int column2;
		int greatestColumn = 0;
		for (int columns = 0; columns < 6; columns++) {
			column2 = columns++;
			if (stonesOnBoard[aiRow][columns] >= stonesOnBoard[aiRow][column2]) {
				greatestColumn = columns;
			}
		}
		return greatestColumn;
	}
	
	public boolean player1RanOutOfStones() {
		stonesCounter = 0;
		for (int columns = 0; columns < 6; columns++) {
			if (stonesOnBoard[opponentRow][columns] == 0) {
				stonesCounter++;
			}
		}
		
		if (stonesCounter == 6) {
			return true;
		}
		else return false;
	}
	
	public boolean aiRanOutOfStones() {
		stonesCounter = 0;
		for (int columns = 0; columns < 6; columns++) {
			if (stonesOnBoard[aiRow][columns] == 0) {
				stonesCounter++;
			}
		}
		
		if (stonesCounter == 6) {
			return true;
		}
		else return false;
	}
	
	public boolean canAISteal() {
		for (int columns = 0; columns < 6; columns++) { //fix public var in gameRules
			if(stonesOnBoard[aiRow][columns] == 0 && stonesOnBoard[opponentRow][columns] != 0) { //192, why is column holder null
				counter++; //need to switch back for opponent row because they are in different places
				columnsHolder[columns] = columns;
				System.out.println("Columns Holder: " + columnsHolder[columns]);
				stonesCanGain[columns] = stonesOnBoard[0][columns];
			}
		}
		//column holder gives
		if (counter != 0) { //checking for stealing
			int pos2; 
			for (int pos = 0; pos <= counter; pos++) {
				pos2 = pos++;
				if (stonesCanGain[pos] >= stonesCanGain[pos2]) {
					columnBestSteal = pos;
					bestStealNum = stonesCanGain[pos];
				}
			}
			return true;
		}	
		
		else return false;
	
	}
	
	public boolean canOpponentSteal() { //in opponent steal, ai is trying to look for the option where the user can steal the least amount of stones if there are more than one options, if only one option, then will take into account how many stones it is possible to lose
		for (int columns = 0; columns < 6; columns++) { //fix public var in gameRules
			if(stonesOnBoard[opponentRow][columns] == 0 && stonesOnBoard[aiRow][columns] != 0) {
				counter++;
				columnsHolder[columns] = columns;
				stonesCanBeStolen[columns] = stonesOnBoard[0][columns];
			}
		}
		//column holder gives
		if (counter != 0) { //checking for stealing
			
			int pos2;
			for(int pos = 0; pos <= counter; pos++) {
				pos2 = pos++;
				if (stonesCanBeStolen[pos] >= stonesCanBeStolen[pos2]) {
					columnBestStolen = pos;
					stonesStolen = stonesCanBeStolen[pos];
				}
			}
			
			return true;
		}
		else return false;
	}
	
	public int haveBonusTurn() {
		//int count = 0;
		for (int x = 0; x < 6; x++) {
			int holder = 6 - x;
			if (stonesOnBoard[aiRow][x] == holder) {
				return x;
			}
		}
		return 7;//because column 0 is a valid column to return pos for the bonus turn i need to return a pos that is impossible
	}
}