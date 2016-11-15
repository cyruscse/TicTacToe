import java.util.Observable;

public class TicTacToeModel extends Observable {
	private int [][] playGrid;
	private int currentPlayer;
	private boolean turn;
	private boolean gameOver;
	
	public TicTacToeModel() {
		playGrid = new int[3][3];
		turn = false;
		gameOver = false;
		currentPlayer = 1;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public int[][] getPlayGrid() {
		return playGrid;
	}
	
	public void evaluate(int x, int y) {
		if (playGrid[x][0] == currentPlayer && playGrid[x][1] == currentPlayer
			&& playGrid[x][2] == currentPlayer) { 
			gameOver = true;
		}
		else if (playGrid[0][y] == currentPlayer && playGrid[1][y] == currentPlayer
				 && playGrid[2][y] == currentPlayer) {
			gameOver = true;
		}
		else if (playGrid[0][0] == currentPlayer && playGrid[1][1] == currentPlayer
				 && playGrid[2][2] == currentPlayer) {
			gameOver = true;
		}
		else if (playGrid[0][2] == currentPlayer && playGrid[1][1] == currentPlayer
				 && playGrid[2][0] == currentPlayer) {
			gameOver = true;
		}
	}
	
	public void play(int x, int y) {
		if ((!gameOver) && (playGrid[x][y] == 0)) {
			turn = !turn;
			playGrid[x][y] = currentPlayer;		
			evaluate(x, y);
						
			if (turn) {
				currentPlayer++;
			} else {
				currentPlayer--;
			}

			setChanged();
			notifyObservers(new TicTacToeUpdate(gameOver, turn, x, y));
		}
	}
}
