package game.objects;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import engine.core.GameObject;

public class StaticSolidObject extends GameObject {
	
	protected Image objectImage;
	
	public StaticSolidObject(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.solid = true;
		
	}
	
	public void setImage(String imagePath) {
		this.objectImage = new ImageIcon(imagePath).getImage();
		
	}

	@Override
	public void update(double deltaTime) { 
		//Static objects don't need logic updates
	}	

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
		if(objectImage == null) {
			g.drawRect(x, y, width, height);
			
		}
		
		g.drawImage(objectImage, x, y, width, height, null);
		
	}
	
	

}
