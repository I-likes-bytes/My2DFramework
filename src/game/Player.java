package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import engine.GameObject;
import engine.GameSettings;
import engine.InputManager;

import java.awt.event.KeyEvent;

public class Player extends GameObject {

	private WorldManager world;
	private InputManager input;
	private int speed = 3;
	
	private String direction = "down";
	private boolean frameToggle = true;
	private long lastFrameTime;
	private long FRAME_DELAY = 200;
	
	private Image up1, up2, down1, down2, left1, left2, right1, right2;
	
	public Player(int x, int y, InputManager input, WorldManager world) {
		super(x, y, 48, 48);
		
		this.input = input;
		this.world = world;
		
		loadSprites();
		
	}

	/**
	 * Method used to load player assets just once
	 */
	private void loadSprites() {
		up1 = new ImageIcon("image/playerAssets/sprite_5.png").getImage();
        up2 = new ImageIcon("image/playerAssets/sprite_7.png").getImage();
        down1 = new ImageIcon("image/playerAssets/sprite_1.png").getImage();
        down2 = new ImageIcon("image/playerAssets/sprite_3.png").getImage();
        right1 = new ImageIcon("image/playerAssets/sprite_9.png").getImage();
        right2 = new ImageIcon("image/playerAssets/sprite_11.png").getImage();
        left1 = new ImageIcon("image/playerAssets/sprite_13.png").getImage();
        left2 = new ImageIcon("image/playerAssets/sprite_15.png").getImage();
	}
	
	
	/**
	 * Method toggles between two provided images, by toggling back and forth
	 * between the two images the player is supposed to look as if it walking
	 * and not just a static image of a character
	 * 
	 * @return Image
	 */
    private Image getCurrentFrame() {
        switch (direction) {
            case "up": 
            	return frameToggle ? up1 : up2;
            case "down": 
            	return frameToggle ? down1 : down2;
            case "left": 
            	return frameToggle ? left1 : left2;
            case "right": 
            	return frameToggle ? right1 : right2;
            default: return down1;
        }
    }
	
    
    
	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
        int dx = 0, dy = 0; //Change in position on the x and y axis
        
        /**
         * Checking what keys are pressed, then calculating how far to move 
         * across the x-axis @variable dx and @variable y-axis(dy) in this 
         * specific frame, after the change in x and y are calculated the 
         * values are immediately applied to them at the end 
         * via x += dx; and y += dy;
         * 
         * The separation of calculation from application allows for other factors
         * to be taken into account before actually applying motion such as 
         * collision, acceleration, etc. which could have impacts on both
         * @variable dx and dy prior to statements x += dx; and y += dy;
         */

        if (input.isKeyPressed(KeyEvent.VK_W)) {
            dy -= speed;
            direction = "up";
        }
        if (input.isKeyPressed(KeyEvent.VK_S)) {
            dy += speed;
            direction = "down";
        }
        if (input.isKeyPressed(KeyEvent.VK_A)) {
            dx -= speed;
            direction = "left";
        }
        if (input.isKeyPressed(KeyEvent.VK_D)) {
            dx += speed;
            direction = "right";
        }
        
        
        //Collision detection before applying movement
        boolean canMove = true;
        
        Rectangle nextPos = new Rectangle (	
        		(int) (x + dx),
        		(int) (y + dy),
        		width,
        		height
        );
        
        for(Tile tile : world.getTileMap().getTiles()) {
        	if(tile.isSolid()) {
        		Rectangle tileBounds = new Rectangle(tile.getX(), tile.getY(), GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
        		
        		if( nextPos.intersects(tileBounds) ) {
        			canMove = false;
        			break;
        		}
        	}
        	
        }

        //Apply movement only if no collision detected
        if(canMove) {
            x += dx;
            y += dy;
        	
        }


        
        
        
        /*
         * For animation timing, checks whether enough time has passed @variable FRAME_DELAY
         * since the last animation frame change. If yes toggle frameToggle which flips 
         * between the two sprites used to animate walking. Update lastFrameTime to mark
         * when the frame was last updated. Gives illusion of walking animation.
         */
        long now = System.currentTimeMillis();
        if (now - lastFrameTime > FRAME_DELAY) {
            frameToggle = !frameToggle;
            lastFrameTime = now;
        }
        
        
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
		Image frame = getCurrentFrame();
		g.drawImage(frame, x, y, width, height, null);
	}
}
