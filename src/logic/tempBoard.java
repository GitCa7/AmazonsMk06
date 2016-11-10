package logic;

public class tempBoard {
	
	int[][] momentaryBoard;
	
	public tempBoard(int[][] momentaryBoard)	{
		this.momentaryBoard = momentaryBoard;
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

	
	
	
}
