package game;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

import engine.GameObject;

public class Tile extends GameObject {
	
	private Image sprite;
	private boolean solid; //Determines if tile blocks movement
	
	public Tile (int x, int y, int width, int height, String spritePath, boolean solid) {
		super(x, y, width, height);
		
		this.sprite = new ImageIcon(spritePath).getImage();
		this.solid = solid;
		
	}

	
	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
		g.drawImage(sprite, x, y, width, height, null);
		
	}
	
	public boolean isSolid() {
		return solid;
		
	}

}
