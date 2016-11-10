package logic;

import java.util.ArrayList;

import Game.Player;
import ui.GridCoordinate;

public class Move {
	
	private boolean isRoot;
	private Player currentPlayer;
	private boolean queenMove;
	private GridCoordinate orgin, destination;

	private int ownScore, opponentScore;
	
	private ArrayList<Move> children;
	private Move parent;
	
	public Move(Move parent, Player current, GridCoordinate origin, GridCoordinate destination, boolean queenMove)	{
		if(parent == null)	{
			this.isRoot = true;
			this.parent = null;
		}
		else	{
			this.isRoot = false;
			this.parent = parent;
		}
		this.currentPlayer = current;
		this.orgin = origin;
		this.destination = destination;
		this.queenMove = queenMove;
	}
	
	
	
	public Move(Player current, GridCoordinate origin, GridCoordinate destination, boolean queenMove, int[][] Board)	{
		this.currentPlayer = current;
		this.orgin = origin;
		this.destination = destination;
		this.queenMove = queenMove;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public boolean isQueenMove() {
		return queenMove;
	}

	public void setQueenMove(boolean queenMove) {
		this.queenMove = queenMove;
	}

	public GridCoordinate getOrgin() {
		return orgin;
	}

	public void setOrgin(GridCoordinate orgin) {
		this.orgin = orgin;
	}

	public GridCoordinate getDestination() {
		return destination;
	}

	public void setDestination(GridCoordinate destination) {
		this.destination = destination;
	}

	public int getOwnScore() {
		return ownScore;
	}

	public void setOwnScore(int ownScore) {
		this.ownScore = ownScore;
	}

	public int getOpponentScore() {
		return opponentScore;
	}

	public void setOpponentScore(int opponentScore) {
		this.opponentScore = opponentScore;
	}
	
	private boolean isRoot()	{
		return isRoot;
	}
	
	

}
