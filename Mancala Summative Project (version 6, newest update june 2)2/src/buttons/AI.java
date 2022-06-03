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

//this isn't the biggest deal but for some reason it's not actually seeing if it can steal before making another turn
//turn switches most of the time but not always
public class AI extends MancalaRules{ 
	int aiRow, opponentRow;
	int counter, stealCounter;
	int[] columnsHolder = new int[8]; 
	boolean canSteal, aiHasBonusTurn, isGoingToSteal;
	int[] stonesCanGain = new int[6];
	int[] stonesCanBeStolen = new int[6];
	int columnBestSteal, bestStealNum, stonesStolen, columnBestStolen, holderForAllOriginalColumns;
	int aiStones, rows, columns, stonesCounter;

	
	public AI() {
		bestStealNum = 0;
		columnBestSteal = 0;
		aiRow = 1;
		opponentRow = 0;
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
		aiHasBonusTurn = false;
		canSteal = false;
		isGoingToSteal = false;
		
	}
	
	 public int getStonesOnBoard(int boardRow, int boardColumn) {
		    int stones = stonesOnBoard[boardRow][boardColumn];
		    //System.out.println("getStonesOnBoard: boardRow=" + boardRow + " boardColumn=" + boardColumn + " stones=" + stones);
		    return stones;
		  }
	
	//return true if ai's turn again, return false if it is the player's turn
	public boolean aiTurn() { //this is checking if it can steal, wont need to worry about
		isGoingToSteal = false;
		//System.out.println("can ai steal: " + canAISteal());
		//System.out.println("can player steal " + canOpponentSteal());
		if (!player1RanOutOfStones() && !aiRanOutOfStones()) {
			if (canAISteal() == false && canOpponentSteal() == false) {
				//check for bonus turn, if no bonus turn then check for piles with one stone, if none than play the pile with the biggest number of stones in it
				if (haveBonusTurn() != 7) { //IF USER CAN PLAY A BONUS TURN
		//			System.out.println("have bonus turn: " + haveBonusTurn());
					rows = aiRow; //setting initially, if done in the loop, will be reset everytime and I don't want that to happen
					columns = haveBonusTurn() + 1;
					holderForAllOriginalColumns = haveBonusTurn();
					//moveStones(columns, haveBonusTurn());
		//			System.out.println("I AM REACHED");
					aiHasBonusTurn = true;
					return true;
				}
				else if (checkOneStone() != 6){ //if user can't play a bonus turn than check to see if any of the piles have one stone in the pile
		//			System.out.println("check one stone: " + checkOneStone());
					columns = checkOneStone() + 1;
					holderForAllOriginalColumns = checkOneStone();
					//stonesOnBoard[aiRow][columns]++;
					//stonesOnBoard[aiRow][checkOneStone()] = 0;
		//			System.out.println("I AM REACHED");
					aiHasBonusTurn = false;
					return false;
				}
				else {
		//			System.out.println("greatestPile: " + getGreatestPile());
					rows = aiRow;
					columns = getGreatestPile() + 1;
					holderForAllOriginalColumns = getGreatestPile();
					//moveStones(columns, getGreatestPile());
		//			System.out.println("I AM REACHED");
					aiHasBonusTurn = false;
					return false;
				}
			}
			else if (canAISteal() && !canOpponentSteal()) { //if ai can steal but opponent can't
		//		System.out.println("Can steal ai and can't steal player");
		//		System.out.println("columns: " + columnBestSteal);
				columns = columnBestSteal + 1;
				holderForAllOriginalColumns = columnBestSteal;
				//moveStones(columns, columnBestSteal);
		//		System.out.println("I AM REACHED");
				isGoingToSteal = true;
				aiHasBonusTurn = false;
				return false;
			}
			else if (!canAISteal() && canOpponentSteal()) { //if opponent can steal but ai can't
		//		System.out.println("ai can't steal but player can");
				columns = columnBestStolen + 1;
				holderForAllOriginalColumns = columnBestStolen;
				//moveStones(columns, columnBestStolen);
		//		System.out.println("I AM REACHED");
				aiHasBonusTurn = false;
				return false;
			}
			else {
		//		System.out.println("both player and ai can steal");
				if (bestStealNum >= stonesStolen) {
		//			System.out.println("ai going to steal");
					columns = columnBestSteal + 1;
					holderForAllOriginalColumns = columnBestSteal;
					//moveStones(columns, columnBestSteal);
		//			System.out.println("I AM REACHED");
					isGoingToSteal = true;
					aiHasBonusTurn = false;
					return false;
				}	
				else {
		//			System.out.println("ai going defensive");
					columns = columnBestStolen + 1;
					holderForAllOriginalColumns = columnBestStolen;
					//moveStones(columns, columnBestStolen);
		//			System.out.println("I AM REACHED");
					aiHasBonusTurn = false;
					return false;
				}
			}
						
		} 
		else return false; 
	}
	
	public void aiPlayMove() {
		moveStones(columns, holderForAllOriginalColumns);
		//setRowAndColumn(rows, columns);
		System.out.println("check for stealing above" + isGoingToSteal);
		if (isGoingToSteal) {
			checkForStealing(); //check for stealing is going wrong because columns are being used, I should be switching everything to be protected

		}
	}
	
	public void moveStones(int columns, int columnHolder) {
		rows = 1;
	//	System.out.println("columnHolder: " + columnHolder);
		for (aiStones = stonesOnBoard[aiRow][columnHolder]; aiStones > 0; aiStones--) {
	//		System.out.println("row1: " + rows);
	//		System.out.println("ai stones:" + aiStones);
	//		System.out.println("columns:" + columns);
			/*if (columns > 6) {
				System.out.println("AHHHH");
			} *///extra turn issue is right down below in the move stones code
			stonesOnBoard[rows][columns]++;
			if (columns == 6) {
	//			System.out.println("EXTRA TURN: " + rows);
				//row will need to change and column needs to be adjusted to go back to zero
				//stonesOnBoard[rows][columns]++;
	//			System.out.println("stones" + stonesOnBoard[rows][columns]);
				columns = 0;
				rows = 0;
			}	
			else if (columns == 5){
				if (rows == 1) {
					columns++;
				}
				else {
					columns = 0;
					rows = 1;
				}	
			}	
			else {
	//			System.out.println("row4: " + rows);
				//stonesOnBoard[rows][columns]++;
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
		int greatestColumn = 0;
		for (int columns = 0; columns < 6; columns++) {
			if (stonesOnBoard[aiRow][columns] > stonesOnBoard[aiRow][greatestColumn]) {
				greatestColumn = columns;
			}
		}
		return greatestColumn;
	}
	
	public boolean player1RanOutOfStones() {		
		for (int columns = 0; columns < 6; columns++) {
			if (stonesOnBoard[opponentRow][columns] != 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean aiRanOutOfStones() {
		for (int columns = 0; columns < 6; columns++) {
			if (stonesOnBoard[aiRow][columns] != 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean canAISteal() {
		for (int columns = 0; columns < 6; columns++) { //fix public var in gameRules
			if(stonesOnBoard[aiRow][columns] == 0 && stonesOnBoard[opponentRow][5 - columns] != 0 && haveATurn(aiRow, columns) != -1) { //192, why is column holder null
				columnsHolder[counter] = columns;
			//	System.out.println("Columns Holder: " + columnsHolder[columns]);
				stonesCanGain[counter] = stonesOnBoard[opponentRow][5 - columns];
				counter++;
			}
		}
		//column holder gives
		if (counter != 0) { //checking for stealing
			columnBestSteal = columnsHolder[0];
			bestStealNum = stonesCanGain[0];
			for (int pos = 0; pos < counter; pos++) {
				if (stonesCanGain[pos] > bestStealNum) {
					columnBestSteal = columnsHolder[pos];
					bestStealNum = stonesCanGain[pos];
				}
			}
			return true;
		}	
		
		else return false;
	
	}
	
	public boolean canOpponentSteal() { //in opponent steal, ai is trying to look for the option where the user can steal the least amount of stones if there are more than one options, if only one option, then will take into account how many stones it is possible to lose
		counter = 0;
		for (int columns = 0; columns < 6; columns++) { //fix public var in gameRules
			if(stonesOnBoard[opponentRow][5 - columns] == 0 && stonesOnBoard[aiRow][columns] != 0 && haveATurn(opponentRow, 5 - columns) != -1) {
				columnsHolder[counter] = columns;
				stonesCanBeStolen[counter] = stonesOnBoard[aiRow][columns];
				counter++;
			//	System.out.println("can opponent steal columns: " + columns + "\ncan opponent steal counter " + counter);
			}
		}
		//column holder gives
		if (counter != 0) { //checking for stealing
			columnBestStolen = columnsHolder[0];
			stonesStolen = stonesCanBeStolen[0];
			for(int pos = 0; pos < counter; pos++) {
				if (stonesCanBeStolen[pos] > stonesStolen) {
					columnBestStolen = columnsHolder[pos];
					stonesStolen = stonesCanBeStolen[pos];
				}
			}
			
			return true;
		}
		else return false; 
	}
	
	public int haveATurn(int row, int targetColumn) {
        for (int columns = 0; columns < 6; columns++) {
            int holder = -1;
            if (columns < targetColumn) {
                holder = targetColumn - columns;
            } else if (columns > targetColumn) {
                holder = 13 - (columns - targetColumn);
            }
            if (stonesOnBoard[row][columns] == holder) {
                return columns;
            }
        }
        return -1;//because column 0 is a valid column to return pos for the bonus turn i need to return a pos that is impossible

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