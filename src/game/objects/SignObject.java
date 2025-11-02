package game.objects;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class SignObject extends StaticSolidObject {
	
	
	public SignObject(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setImage("image/objectAssets/Beach Plates.png");
		
	}

	
	
	
	
	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
		g.drawImage(objectImage, x, y, width, height, null);
	}

}
