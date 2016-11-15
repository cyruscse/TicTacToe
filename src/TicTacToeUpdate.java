public class TicTacToeUpdate {
	private boolean gameOver;
	private boolean turn;
	private int x;
	private int y;
	
	public TicTacToeUpdate(boolean gameOver, boolean turn, int x, int y) {
		this.gameOver = gameOver;
		this.turn = turn;
		this.x = x;
		this.y = y;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public boolean getPlayer() {
		return turn;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
