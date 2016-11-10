package Board;

import Game.Player;
import ui.GridCoordinate;

public class GameBoard2 {

	private final int length = 10;
	private final int height = 10;
	
	private final int arrowValue = 66;

	private Player player1, player2, current;
	private int[][] Grid = new int[height][length];

	public GameBoard2(Player player1, Player player2) {
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
	}

	public void iniGame() {

		// For player1
		GridCoordinate[] temp = player1.getAmazonsPosistions();

		for (int i = 0; i < temp.length; i++) {
			Grid[temp[i].y][temp[i].x] = 10 /* + i */;
		}

		// For player2
		temp = player2.getAmazonsPosistions();

		for (int i = 0; i < temp.length; i++) {
			Grid[temp[i].y][temp[i].x] = 20 /* + i */;
		}
	}

	public boolean amazonOfCurrentPlayer(GridCoordinate position) {
		if (Grid[position.y][position.x] >= current.getVal()
				&& Grid[position.y][position.x] < (current.getVal() + 10)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean amazonAt(GridCoordinate position) {
		if (Grid[position.y][position.x] >= 10 && Grid[position.y][position.x] < 30)
			return true;
		else
			return false;
	}

	// Just removes the amazon at a certain position
	// could be used for moving animation
	public void removeAmazAt(GridCoordinate position) {
		int posX = position.x;
		int posY = position.y;

		if(Grid[posY][posX] == current.getVal())	{
			Grid[posY][posX] = 0;
		}
		else	  {
			System.out.println("There is no Amazon at this position");
		}
	}

	// Just removes the amazon at a certain position
	// could be used for moving animation
	public void setAmazonOfCurrentAt(GridCoordinate position) {
		
		int posX = position.x;
		int posY = position.y;
		if(Grid[posY][posX] == 50)	{
			disposePosMoves();
			Grid[posY][posX] = current.getVal();
		}
		else	{
			System.out.println("This is not a vlaid positon");
		}
	}
	
	public void setArrowAt(GridCoordinate position) {
		
		int posX = position.x;
		int posY = position.y;
		if(Grid[posY][posX] == 50)	{
			disposePosMoves();
			Grid[posY][posX] = arrowValue;
		}
		else	{
			System.out.println("This is not a valid Arrow positon");
		}
	}

	public void toggleTurn() {
		System.out.print("Turn ended: ");
		if (current == player1) {
			System.out.print("player 2 turn now\n");
			current = player2;
		} else {
			System.out.print("player 1 turn now\n");
			current = player1;
		}
	}

	public void showPosMoves(GridCoordinate position) {
		int posY = position.y;
		int posX = position.x;

		int tempX, tempY;

		// up
		for (tempY = posY - 1; tempY >= 0 && Grid[tempY][posX] == 0; tempY--) {
			Grid[tempY][posX] = 50;
		}

		// down
		for (tempY = posY + 1; tempY < Grid.length && Grid[tempY][posX] == 0; tempY++) {
			Grid[tempY][posX] = 50;
		}

		// right
		for (tempX = posX + 1; tempX < Grid[0].length && Grid[posY][tempX] == 0; tempX++) {
			Grid[posY][tempX] = 50;
		}

		// left
		for (tempX = posX - 1; tempX >= 0 && Grid[posY][tempX] == 0; tempX--) {
			Grid[posY][tempX] = 50;
		}

		// Diag up left
		tempY = posY - 1;
		tempX = posX + 1;

		while (checkBound(tempY, tempX) && Grid[tempY][tempX] == 0) {
			Grid[tempY][tempX] = 50;
			tempY--;
			tempX++;

		}

		// Diag low right
		tempY = posY + 1;
		tempX = posX + 1;

		while (checkBound(tempY, tempX) && Grid[tempY][tempX] == 0) {
			// System.out.println("Position:\tY = " + tempY + "\tX = " + tempX +
			// "\nStill in Bounds? " + checkBound(tempY, tempX));

			Grid[tempY][tempX] = 50;
			tempY++;
			tempX++;

		}

		// Diag low left
		tempY = posY + 1;
		tempX = posX - 1;

		while (checkBound(tempY, tempX) && Grid[tempY][tempX] == 0) {
			Grid[tempY][tempX] = 50;
			tempY++;
			tempX--;

		}

		// Diag up left
		tempY = posY - 1;
		tempX = posX - 1;

		while (checkBound(tempY, tempX) && Grid[tempY][tempX] == 0) {

			Grid[tempY][tempX] = 50;
			tempY--;
			tempX--;

		}
	}

	private void disposePosMoves() {
		for (int j = 0; j < Grid[0].length; j++) {
			for (int i = 0; i < Grid.length; i++) {
				if (Grid[i][j] == 50) {
					Grid[i][j] = 0;
				}
			}
		}
	}

	// Method for checing if a clicked position is among all the possible
	// positions
	public boolean checkDest(GridCoordinate position) {
		int posX = position.x;
		int posY = position.y;

		if (Grid[posY][posX] == 50) {
			return true;
		} else {
			return false;
		}

	}

	// method overload, just use whatever is more convinient
	public boolean checkDest(int posX, int posY) {
		if (Grid[posY][posX] == 50) {
			return true;
		} else {
			return false;
		}
	}

	// Executed When Amazon of Current player is true, ...
	// - showPosMoves is executed
	// - and CheckDest returns true
	public void moveAmazon(GridCoordinate origin, GridCoordinate destination) {
		int orX, orY, destX, destY;

		orX = origin.x;
		orY = origin.y;

		destX = destination.x;
		destY = destination.y;

		if (amazonOfCurrentPlayer(origin)) {
			System.out.println("The Starting position belongs to an amazon of the current Player");

			printGrid();

			// Takong the Queen of
			Grid[orY][orX] = 0;

			try {
				Thread.sleep(1000); // 1000 milliseconds is one second.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

		}

	}

	private boolean checkBound(int y, int x) {
		if ((y >= 0 && y < Grid.length) && (x >= 0 && x < Grid[0].length)) {
			return true;
		} else {
			return false;
		}
	}

	public int[][] getGrid() {
		return Grid;
	}

	public void printGrid() {
		for (int i = 0; i < Grid.length; i++) {
			System.out.println(
					"_________________________________________________________________________________________________________________________________________________________________");
			System.out.print("|");
			for (int j = 0; j < Grid[0].length; j++) {
				System.out.print("\t" + Grid[i][j] + "\t|");
			}
			System.out.print("\n");
		}
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	

}
