import static org.junit.Assert.*;

import org.junit.Test;

public class TicTacToeModelTest {
	private TicTacToeModel model;
	
	@Test
	public void testTicTacToeModel() {
		model = new TicTacToeModel();
		
		int[][] playGrid = model.getPlayGrid();
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				assertEquals(playGrid[i][j], 0);
			}
		}
	}

	@Test
	public void testEvaluate() {
		model = new TicTacToeModel();
		
		model.play(0, 0);
		model.play(1, 0);
		model.play(0, 1);
		model.play(1, 1);
		model.play(0, 2);
		
		assertEquals(model.isGameOver(), true);
	}

	@Test
	public void testPlay() {
		model = new TicTacToeModel();
		int[][] playGrid = model.getPlayGrid();
		
		model.play(0, 0);
		assertEquals(playGrid[0][0], 1);
		
		model.play(0, 1);
		assertEquals(playGrid[0][1], 2);
		
		model.play(0, 2);
		assertEquals(playGrid[0][2], 1);
		
		model.play(2, 1);
		assertEquals(playGrid[2][1], 2);
		
		assertEquals(playGrid[2][2], 0);
		assertEquals(playGrid[2][0], 0);
	}
}
