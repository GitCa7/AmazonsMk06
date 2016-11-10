package Game;

public class Amazon {

	int posX, posY;

	public Amazon(int y, int x) {
		posY = y;
		posX = x;
	}

	public Amazon() {

	}

	public void setPosition(int newY, int newX) {
		posY = newY;
		posX = newX;
	}

	public int[] getPosition() {
		int[] pos = new int[2];
		pos[0] = posY;
		pos[1] = posX;
		return pos;
	}

	public boolean isAtPosition(int y, int x) {
		if (y == posY && x == posX)
			return true;
		else
			return false;
	}

}
