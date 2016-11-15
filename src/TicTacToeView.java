import java.util.Observer;
import java.util.Observable;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TicTacToeView implements Observer {
	private JFrame mainFrame;
	private JPanel gamePanel;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem gameCreate;
	private JMenuItem exitGame;
	private GridLayout gameLayout;
	private JButton[][] buttonGrid;
	
	private TicTacToeModel model;
	private final int GRID_SIZE = 3;
	
	public TicTacToeView() {
		mainFrame = new JFrame();
		gamePanel = new JPanel();
		buttonGrid = new JButton[GRID_SIZE][GRID_SIZE];
		
		gameLayout = new GridLayout(GRID_SIZE, GRID_SIZE);
		gamePanel.setLayout(gameLayout);
		
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		
		gameCreate = new JMenuItem("New Game");
		gameCreate.addActionListener(e -> reset());
		
		exitGame = new JMenuItem("Exit");
		exitGame.addActionListener(e -> System.exit(1));
		
		menu.add(gameCreate);
		menu.add(exitGame);
		menuBar.add(menu);
		
		JButton buttonToAdd;
		
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				buttonToAdd = new JButton("-");
				buttonToAdd.setActionCommand(i + " " + j);
				buttonToAdd.addActionListener(e -> {
					String[] buttonPosition = e.getActionCommand().split("\\s+");
					
					model.play(Integer.parseInt(buttonPosition[0]), Integer.parseInt(buttonPosition[1]));
				});
				buttonGrid[i][j] = buttonToAdd;
				gamePanel.add(buttonToAdd);
			}
		}
		
		reset();
		
		mainFrame.add(gamePanel);
		mainFrame.setJMenuBar(menuBar);
		
		mainFrame.setSize(300, 300);
		mainFrame.setTitle("Tic Tac Toe");
		mainFrame.setVisible(true);
	}
	
	private void reset() {
		model = new TicTacToeModel();
		model.addObserver(this);
		
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				buttonGrid[i][j].setText("-");
			}
		}
	}
	
	public void update (Observable o, Object arg) {
		if (arg instanceof TicTacToeUpdate) {
			TicTacToeUpdate upd = (TicTacToeUpdate) arg;
			String currentPlayer;
			
			if (upd.getPlayer()) {
				currentPlayer = "X";
			}
			else {
				currentPlayer = "O";
			}
			
			buttonGrid[upd.getX()][upd.getY()].setText(currentPlayer);
			
			if (upd.isGameOver()) {
				JOptionPane.showMessageDialog(mainFrame, "The game is over! The winner is " + currentPlayer);
			}
		}
	}
	
	public static void main(String[] args) {
		new TicTacToeView();
	}
}
