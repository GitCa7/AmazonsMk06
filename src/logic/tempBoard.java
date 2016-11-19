package logic;

import ui.GridCoordinate;

public class tempBoard {
	
	int[][] momentaryBoard;
	private GridCoordinate origin;

	public tempBoard(int[][] momentaryBoard)	{
		this.momentaryBoard = momentaryBoard;
		origin = null;
	}

	public tempBoard(int[][] momentaryBoard, GridCoordinate origin)	{
		this.momentaryBoard = momentaryBoard;
		this.origin = origin;
	}

	public GridCoordinate getOrigin()	{
		return origin;
	}


	public int[][] getMomentaryBoard() {
		return momentaryBoard;
	}
	
	/*
	public int[][] setMomentaryBoard(int[][] ) {
		return momentaryBoard;
	}*/

	public void print()	{
		String s;
		for (int i = 0; i < momentaryBoard.length; i++) {
			for (int j = 0; j < momentaryBoard[0].length; j++) {
				if (momentaryBoard[i][j] < 10) {
					s = "";
				} else {
					s = "0";
				}
				System.out.print(s + momentaryBoard[i][j] + " ");
			}
			System.out.println("");
		}
	}


	public boolean isSame(tempBoard comp)	{
		int[][] mine = getMomentaryBoard();
		int[][] toCompareTo = comp.getMomentaryBoard();

		for(int i = 0; i < toCompareTo.length; i++)	{
			for(int j = 0; j < toCompareTo[0].length; j++)	{
				if(mine[i][j] != toCompareTo[i][j])	{
					return false;
				}
			}
		}
		return true;
 	}



	
	
	
}
