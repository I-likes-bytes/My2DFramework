package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import engine.core.GameLoop;
import engine.core.GameSettings;
import engine.core.Updatable;
import engine.input.InputManager;

import game.entities.Player;
import game.objects.Coin;
import game.objects.SignObject;
import game.world.WorldManager;

public class GamePanel extends JPanel implements Updatable {
	
	private GameLoop loop;
	private InputManager input;
	private Player player;
	private WorldManager world;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));	
		this.setFocusable(true);
		
		input = new InputManager();
		this.addKeyListener(input);
		
		world = new WorldManager();
		
		player = new Player(100, 100, input, world);
		world.addObject(player); //Adding player to world
		
		world.addObject(new SignObject(500, 100, 32, 32));
		
		world.addObject(new Coin(300, 100, 32, 32));
		
		
		
		loop = new GameLoop(this);
	
	}

	public void startGame() {
		loop.start();
		
	}
	
	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		player.update(deltaTime);
		repaint();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		world.draw(g2); //Everything is drawn in the world
		
		g2.dispose();
	}

}
