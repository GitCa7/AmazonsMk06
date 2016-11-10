package Test;

import java.util.ArrayList;

import Game.Player;
import ui.GridCoordinate;

public class floodFill {

	int[][] Grid = {
			{0,0,0,0,0,0,0,0,0,0},
			{3,3,3,0,0,0,0,0,0,0},
			{0,0,3,0,0,0,0,0,0,0},
			{0,0,3,0,0,0,0,0,0,0},
			{3,3,3,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0}
	};
	Player current;
	Player player1, player2;

	private final int markedSpot = 7;
	private final int inaccesible = 6;

	public floodFill()	{
		this.player1 = new Player(true);
		this.player2 = new Player(false);

		player1.setUpQueens();
		player2.setUpQueens();
	}

	public static void main(String[] args0)	{
		floodFill test = new floodFill();
		test.initializeBoard();
		test.printBoard();
		test.checkPlayers();
	}

	public void checkPlayers()	{
		// for PLayer1	
		//get all QueenPositions
		ArrayList<GridCoordinate> queens = listQueensOfCurrent(player1);
		for(GridCoordinate queen: queens)	{
			if(queenEnclosed(queen))	{
				System.out.println("enclosed for " + player1);
			}
			printBoard();
			removeMarked();
		}

		ArrayList<GridCoordinate> queensB = listQueensOfCurrent(player2);
		for(GridCoordinate queen: queensB)	{
			if(queenEnclosed(queen))	{
				System.out.println("enclosed for " + player2);
			}
			printBoard();
			removeMarked();
		}


	}

	public ArrayList<GridCoordinate> listQueensOfCurrent(Player player)	{
		ArrayList<GridCoordinate> queens = new ArrayList<GridCoordinate>();
		int pos=0;
		GridCoordinate temp;
		for(int x = 0; x < 10; x++)	{
			for(int y = 0; y < 10; y++)	{
				temp = new GridCoordinate(x +1,y+ 1);
				if(Grid[y][x] == player.getVal())	{
					temp = new GridCoordinate(x +1,y+ 1);
					queens.add(temp);
				}
				if(queens.size() == 4)
					break;
			}
		}
		return queens;
	}

	public void initializeBoard()	{
		GridCoordinate[] temp = player1.getAmazonsPosistions();



		for (int i = 0; i < temp.length; i++) {
			Grid[temp[i].y][temp[i].x] = 1 /* + i */;
		}

		// For player2
		temp = player2.getAmazonsPosistions();

		for (int i = 0; i < temp.length; i++) {
			Grid[temp[i].y][temp[i].x] = 2 /* + i */;
		}
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

	public boolean queenEnclosed(GridCoordinate position)	{

		int x = position.x;
		int y = position.y;


		roundHouse(x,y);
		for(int i = 0; i < Grid.length; i++)	{
			for(int j = 0; j < Grid[0].length; j++)	{
				if(Grid[i][j] == markedSpot && proximityCheck(j,i))	{
					roundHouse(j,i);
					i = 0;
					j = 0;
				}
			}
		}

		if(countMarked() != countFreeSpots())	{
			markInaccesible();
			return true;
		}
		else
			return false;

	}

	private  void markInaccesible()	{
		for(int i = 0; i < Grid.length; i++)	{
			for(int j = 0; j < Grid[0].length; j++)	{
				if(Grid[i][j] == markedSpot)	{
					Grid[i][j] = inaccesible;
				}
			}
		}
	}

	private boolean proximityCheck(int x, int y)	{
		int xStart = 0;
		int yStart = 0;


		if(x == 0)	{
			xStart = x;
		}
		else 	{
			xStart = x - 1;
		}


		if(y == 0)	{
			yStart = y;
		}
		else 	{
			yStart = y - 1;
		}

		for(int yPos = yStart; yPos <= y + 1 &&  yPos < 10; yPos++)	{
			for(int xPos = xStart; xPos <= x + 1 && xPos < 10; xPos++)	{
				if(Grid[yPos][xPos] == 0)	{
					return true;
				}
			}
		}
		return false;
	}

	public void roundHouse(int x, int y)	{
		int xStart = 0;
		int yStart = 0;


		if(x == 0)	{
			xStart = x;
		}
		else 	{
			xStart = x - 1;
		}


		if(y == 0)	{
			yStart = y;
		}
		else 	{
			yStart = y - 1;
		}

		for(int yPos = yStart; yPos <= y + 1 &&  yPos < 10; yPos++)	{
			for(int xPos = xStart; xPos <= x + 1 && xPos < 10; xPos++)	{
				//System.out.println("X = " + xPos + "\tY = " + yPos);
				if(Grid[yPos][xPos] == 0)	{
					Grid[yPos][xPos] = markedSpot;
				}
			}
		}
	}

	public void removeMarked()	{
		for(int i = 0; i < Grid.length; i++)	{
			for(int j = 0; j < Grid[0].length; j++)	{
				if(Grid[i][j] == markedSpot)
					Grid[i][j] = 0;
			}
		}
	}


	private int countFreeSpots()	{
		int count = 0;
		for(int i = 0; i < Grid.length; i++)	{
			for(int j = 0; j < Grid[0].length; j++)	{
				if(Grid[i][j] == 3)	{
					//count spots of arrows
					count++;
				}
			}
		}
		return (92 - count);
	}

	private int countMarked()	{
		int count = 0;
		for(int i = 0; i < Grid.length; i++)	{
			for(int j = 0; j < Grid[0].length; j++)	{
				if(Grid[i][j] == markedSpot)
					count++;
			}
		}
		return count;
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

}
