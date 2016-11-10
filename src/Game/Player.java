package Game;

import ui.GridCoordinate;

public class Player {

	GridCoordinate[] amazonPositions = new GridCoordinate[4];
	private boolean isFirst;
	private boolean isBot;

	public Player(boolean isFirst) {
		this.isFirst = isFirst;
		this.isBot = false;
	}
	
	
	public Player(boolean isFirst, boolean isBot) {
		this.isFirst = isFirst;
		this.isBot = isBot;
	}
	

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst() {
		isFirst = true;
	}

	public void setSecond() {
		isFirst = false;
	}

	public int getVal() {
		if (isFirst) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public String toString()	{
		if(isFirst == true)	{
			return new String("White");
		}
		else	{
			return new String("Black");
		}
	}

	public void setUpQueens() {
		int startY1, startY2;
		if (isFirst) {
			startY1 = 6;
			startY2 = 9;
		} else {
			startY1 = 3;
			startY2 = 0;
		}
		int i = 0;

		amazonPositions[i++] = new GridCoordinate(0, startY1);
		amazonPositions[i++] = new GridCoordinate(3, startY2);
		amazonPositions[i++] = new GridCoordinate(6, startY2);
		amazonPositions[i++] = new GridCoordinate(9, startY1);
	}

	public GridCoordinate[] getAmazonsPosistions() {
		return amazonPositions;
	}

}
