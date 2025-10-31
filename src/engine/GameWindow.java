package engine;

import javax.swing.JFrame;
import game.GamePanel;

public class GameWindow extends JFrame {

	
	public GameWindow() {
		this.setTitle("2D Jave Game Framework");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		GamePanel panel = new GamePanel();
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		panel.startGame();
		
	}
	
	public static void main(String[] args) {
		new GameWindow();
		
	}
	
}
