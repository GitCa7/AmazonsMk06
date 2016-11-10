package logic;

import java.util.ArrayList;

import Game.Player;
import ui.GridCoordinate;

public class LogicBoard {

	private final int width = 10;
	private final int height = 10;

	public final int wQueenVal = 1;
	public final int bQueenVal = 2;
	public final int arrowVal = 3;

	public final int amazonPosVal = 4;
	public final int arrowPosVal = 5;

	private Player player1, player2, current;
	private int[][] Grid = new int[height][width];


	public boolean queenSelect;
	public boolean arrowSpotSelect;
	
	//private ArrayList<Move> Moves;
	private ArrayList<tempBoard> Moves;
	
	// According to sprite
	// {0 = empty, 1 = white queen, 2 = black queen, 3 = arrow, 4 = possible
	// queen spot, 5 = possible arrow spot}
	public LogicBoard(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;

		if (player1.isFirst() == player2.isFirst()) {
			player1.setFirst();
			player2.setSecond();
		}

		if (player1.isFirst() == true) {
			current = player1;
		} else {
			current = player2;
		}

		player1.setUpQueens();
		player2.setUpQueens();
		initializeBoard();
	}

	
	
	public void printBoard() {
		System.out.println("");
		String s;
		for (int i = 0; i < Grid.length; i++) {
			for (int j = 0; j < Grid[0].length; j++) {
				if (Grid[i][j] < 10) {
					s = "";
				} else {
					s = "0";
				}
				System.out.print(s + Grid[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	// Four white queens (player 1) and four black queens (player 2) to start
	// with
	private void initializeBoard() {
		queenSelect = false;
		arrowSpotSelect = false;
		Moves = new ArrayList<>();
		/*
		queenSelect = true;
		arrowSpotSelect = false;
		*/
		// For player1
		GridCoordinate[] temp = player1.getAmazonsPosistions();

		for (int i = 0; i < temp.length; i++) {
			Grid[temp[i].y][temp[i].x] = wQueenVal /* + i */;
		}

		// For player2
		temp = player2.getAmazonsPosistions();

		for (int i = 0; i < temp.length; i++) {
			Grid[temp[i].y][temp[i].x] = bQueenVal /* + i */;
		}
		
		//addMove();
	}

	public int[][] getBoard() {
		return Grid;
	}

	public void printAllMoves()	{
		int i = 0;
		while(i < Moves.size())	{
			System.out.println("Move: " + i);
			Moves.get(i).print();
			System.out.println("\n");
			i++;
		}
	}

	// Removes possible moves
	public void removePossibleMoves() {
		for (int i = 0; i < Grid.length; i++) {
			for (int j = 0; j < Grid[0].length; j++) {
				if (Grid[i][j] == amazonPosVal || Grid[i][j] == arrowPosVal) {
					Grid[i][j] = 0;
				}
			}
		}
	}
	
	public void setQueenOfCurrentOn(GridCoordinate point)	{
		int x = point.x - 1;
		int y = point.y - 1;
		
		Grid[y][x] = getCurrent().getVal();
	}
	
	public void setQueenOfCurrentOn(int x, int y)	{
		Grid[y][x] = getCurrent().getVal();
	}
	
	public void setQueenOfOpposing(GridCoordinate point)	{
		int x = point.x - 1;
		int y = point.y - 1;
		
		if(current == player1)	{
			Grid[y][x] = player2.getVal();
		}
		else 	{
			Grid[y][x] = player1.getVal();
		}
	}
	
	public void setArrowOn(int x, int y)	{
		Grid[y][x] = arrowVal;
	}
	
	public void setArrowOn(GridCoordinate position)	{
		int x = position.x - 1;
		int y = position.y - 1;
		
		Grid[y][x] = arrowVal;
	}

	public void setEmpty(GridCoordinate point)	{
		int x = point.x - 1;
		int y = point.y - 1;
		
		Grid[y][x] = 0;
	}
	
	
	public void addMove()	{
		removePossibleMoves();
		int[][] t = copyBoard();
		tempBoard tBoard = new tempBoard(t);
		Moves.add(tBoard);
		System.out.println("Last State");
		printBoard(Moves.get(Moves.size() - 1).getMomentaryBoard());
	}
	
	public int[][] copyBoard()	{
		
		int length = Grid.length;
	    int[][] target = new int[length][Grid[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(Grid[i], 0, target[i], 0, Grid[i].length);
	    }
	    return target;
		
	}
	
	private void setBoard(int[][] newBoard)	{
		for (int i = 0; i < Grid.length; i++) {
			System.arraycopy(newBoard[i], 0, Grid[i], 0, newBoard[i].length);
		}

	}
	
	public void undoMove()	{
		
		//possible cases
		//queen has been choose
		System.out.println("queenSelect: " + queenSelect + "\narrowSpotSelect: " + arrowSpotSelect);
		
		if(!arrowSpotSelect && queenSelect)	{
			removePossibleMoves();
			queenSelect = false;
		}
		else if(arrowSpotSelect && !queenSelect)	{
		//You are about to place an Arrow but are unhappy with the Position of the Queen so you choose the previous situation
			if(Moves.size() > 0)	{
				System.out.println("entered");
				int lastPos = Moves.size() - 1;
				tempBoard temp = Moves.remove(lastPos);
				int[][] temp2 = temp.getMomentaryBoard();
				System.out.println("Supposingly new state");
				printBoard(temp2);
				Grid = temp2;
				queenSelect = false;
				arrowSpotSelect = false;
			}
			
		}
		else	{
			//When you just want to get the previous state
			System.out.println("Size: " + Moves.size());
			if(Moves.size() > 0)	{
				int lastPos = Moves.size() - 1;
				tempBoard temp = Moves.remove(lastPos);
				int[][] temp2 = temp.getMomentaryBoard();
				System.out.println("Supposingly new state");
				printBoard(temp2);
				Grid = temp2;
				toggleTurn();
			}
		}
		System.out.println("Actual Grid");
		printBoard();
		
	}
	
	public void setEmpty(int x, int y)	{
		Grid[y][x] = 0;
	}
	
	// Checks whether the game is over (i.e. is every queen from a certain
	// player locked in? if yes, then game over)
	public boolean isGameOver() {
		int currentPlayerVal = getCurrent().getVal();
		for(int i = 0; i < Grid.length; i++)	{
			for(int j = 0; j < Grid[0].length; j++)	{
				if(Grid[i][j] == currentPlayerVal)	{
					calcPosMoves(new GridCoordinate(j + 1, i + 1), true);
					if(countPosMoves() > 0)	{
						return true;
					}
				}
			}
		}
		
		return false;
	}

	
	
	public void printBoard(int[][] array) {
        String s;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] < 10) {
                    s = "";
                } else {
                    s = "0";
                }
                System.out.print(s + array[i][j] + " ");
            }
            System.out.println("");
        }
    }
	
	

	// Checks if a queens move is possible and takes board boundaries into
	// account to prevent out of boundary issues
	public void calcPosMoves(GridCoordinate position, boolean amazonMove) {
		removePossibleMoves();
		
		//Since GridCordinates start at 1,1 instead of 0,0
		int x = position.x - 1;
		int y = position.y - 1;

		
		System.out.println("Position: X = " + x + " Y= " + y);
		
		
		int val;
		
		if(amazonMove)	{
			val = amazonPosVal;
		}
		else	{
			val = arrowPosVal;
		}
		
		int i, j;
		int tempX, tempY;
		int length = Grid.length;
		i = y - 1;
		j = x - 1;

		// up
		for (tempY = y - 1; tempY >= 0 && Grid[tempY][x] == 0; tempY--) {
			Grid[tempY][x] = val;
		}

		// Direction: bottom vertical
		// down
		for (tempY = y + 1; tempY < Grid.length && Grid[tempY][x] == 0; tempY++) {
			Grid[tempY][x] = val;
		}

		// Direction: left horizontal
		for (tempX = x - 1; tempX >= 0 && Grid[y][tempX] == 0; tempX--) {
			Grid[y][tempX] = val;
		}
		// Direction: right horizontal
		for (tempX = x + 1; tempX < Grid[0].length && Grid[y][tempX] == 0; tempX++) {
			Grid[y][tempX] = val;
		}
		// Direction: top right diagonal
		tempY = y - 1;
		tempX = x + 1;

		while (checkBound(tempY, tempX) && Grid[tempY][tempX] == 0) {
			//System.out.println("Position:\tY = " + tempY + "\tX = " + tempX +
			//"\nStill in Bounds? " + checkBound(tempY, tempX));

			Grid[tempY][tempX] = val;
			tempY--;
			tempX++;

		}
		// Direction: top left diagonal
		tempY = y - 1;
		tempX = x - 1;

		while (checkBound(tempY, tempX) && Grid[tempY][tempX] == 0) {

			Grid[tempY][tempX] = val;
			tempY--;
			tempX--;

		}
		// Direction: bottom right diagonal
		tempY = y + 1;
		tempX = x + 1;

		while (checkBound(tempY, tempX) && Grid[tempY][tempX] == 0) {
			// System.out.println("Position:\tY = " + tempY + "\tX = " + tempX +
			// "\nStill in Bounds? " + checkBound(tempY, tempX));

			Grid[tempY][tempX] = val;
			tempY++;
			tempX++;

		}
		// Direction: bottom left diagonal
		tempY = y + 1;
		tempX = x - 1;

		while (checkBound(tempY, tempX) && Grid[tempY][tempX] == 0) {
			Grid[tempY][tempX] = val;
			tempY++;
			tempX--;

		}

		
		
	}
	
	
	public boolean isMovePossible()	{
		int posMoves = countPosMoves();
		if (posMoves == 0) {
			System.out.println("This queen is not able to move anymore!");
			return false;
		}
		else {
			return true;
		}
	}
	
	private int countPosMoves()	{
		int count = 0;
		for(int i = 0; i < Grid.length; i++)	{
			for(int j = 0; j < Grid[0].length; j++)	{
				if(Grid[i][j] == amazonPosVal)	{
					count++;
				}
			}
		}
		return count;
	}
	
	public ArrayList<GridCoordinate> listQueensOfCurrent()	{
		ArrayList<GridCoordinate> queens = new ArrayList<GridCoordinate>();
		int pos=0;
		GridCoordinate temp;
		for(int x = 1; x < 11; x++)	{
			for(int y = 1; y < 11; y++)	{
				temp = new GridCoordinate(x,y);
				if(amazonOfCurrentPlayer(temp))	{
					queens.add(temp);
				}
				if(queens.size() == 4)	{
					break;
				}
			}
		}
		return queens;
	}
	
	public ArrayList<GridCoordinate> listPossibleMoves(GridCoordinate position)	{
		ArrayList<GridCoordinate> posMoves = new ArrayList<GridCoordinate>();
		calcPosMoves(position, false);
		GridCoordinate temp;
		for(int x = 1; x < 11; x++)	{
			for(int y = 1; y < 11; y++)	{
				temp = new GridCoordinate(x,y);
				if(posMoveAt(temp))	{
					posMoves.add(temp);
				}
			}
		}
		removePossibleMoves();
		return posMoves;
		
	}

	// Calculates possible moves and takes board boundaries into account to
	// prevent out of boundary issues.
	

	private boolean checkBound(int y, int x) {
		if ((y >= 0 && y < Grid.length) && (x >= 0 && x < Grid[0].length)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkBound(GridCoordinate position) {
		int x = position.x;
		int y = position.y;

		if ((y >= 0 && y < Grid.length) && (x >= 0 && x < Grid[0].length)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void toggleTurn() {
		queenSelect = false;
		arrowSpotSelect = false;
		//Change to true
		System.out.print("Turn ended: ");
		if (current == player1) {
			System.out.print("player 2 turn now\n");
			current = player2;
		} else {
			System.out.print("player 1 turn now\n");
			current = player1;
		}
	}
	
	public Player getCurrent()	{
		return current;
	}
	
	public boolean posMovesDispl()	{
		for(int i = 0; i < Grid.length; i++)	{
			for(int j = 0; j < Grid[0].length; j++)	{
				if(Grid[i][j] == amazonPosVal){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean amazonOfCurrentPlayer(GridCoordinate position) {
		int posX = position.x - 1;
		int posY = position.y - 1;
		
		
		if (Grid[posY][posX] == current.getVal()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean posMoveAt(GridCoordinate position){
		int posX = position.x - 1;
		int posY = position.y - 1;
		
		if (Grid[posY][posX] == amazonPosVal || Grid[posY][posX] == arrowPosVal) {
			return true;
		} else {
			return false;
		}
	}

}
