package engine.core;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject implements Updatable {

	protected int x, y, width, height;
	protected boolean solid = false;
	
	
	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public abstract void draw(Graphics2D g);
	
	
	//For collision detection
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);

	}
	
	public boolean isSolid() {
		return solid;
		
	}
	
	public void setSolid(boolean solid) {
		this.solid = solid;
		
	}
	

	
	//Getters
	public int getX() {
		return x;
		
	}
	
	public int getY() {
		return y;
		
	}
	
	public int getWidth() {
		return width;
		
	}
	
	public int getHeight() {
		return height;
		
	}
	
	
	//Setter
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	
	
}
