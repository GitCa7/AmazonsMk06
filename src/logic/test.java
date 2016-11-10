package logic;

import java.util.ArrayList;

import Game.Player;
import ui.GridCoordinate;

public class test {
	
	LogicBoard Board;
	
	public test()	{
		
		Player player1 = new Player(true);
		Player player2 = new Player(false);

		Board = new LogicBoard(player1, player2);
		
		Board.printBoard();
		check();
	}
	
	private void check()	{
		
		ArrayList<GridCoordinate> queens = Board.listQueensOfCurrent();
		for(GridCoordinate temp : queens)	{
			System.out.println(temp);
		}
		System.out.println();
		Board.calcPosMoves(queens.get(0), true);
		
		Board.printBoard();
		System.out.println();
		

		
	}
	
	public static void main(String[] args0)	{
		test Test = new test();
	}

}
