package game.objects;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import engine.core.GameObject;

public abstract class InteractiveObject extends GameObject {
	
	protected boolean collected = false;
	protected Image objectImage;
	
	public InteractiveObject(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.solid = false; //Interactive objects typically not solid
		
	}
	
	public void setImage(String imagePath) {
		this.objectImage = new ImageIcon(imagePath).getImage();
		
	}

	public boolean isCollected() {
		return collected;
		
	}
	
	public void collect() {
		this.collected = true;
		
	}
	
	//To be implemented by sub-classes (XP Orb and Sign for now)
	public abstract void onInteract();
	
	
	
	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
		if(!collected && objectImage != null) {
			g.drawImage(objectImage, x, y, width, height, null);
			
		}
	}
	
	

}
