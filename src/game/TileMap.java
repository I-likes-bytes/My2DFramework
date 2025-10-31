package game;

import java.awt.Graphics2D;
import java.util.ArrayList;

import engine.GameSettings;
import engine.Updatable;

public class TileMap implements Updatable {

	private ArrayList<Tile> tiles = new ArrayList<>();
	private int[][] mapData;
	
	public TileMap() {
		
		
		//Hardcoded map example for now.
		//Testing collision detection.
		//0 = grass 1 = water
		
		   mapData = new int[][] {
	            {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	            {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
	            {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
	            {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
	            {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
	            {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
	            {1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	        };
	        
	        loadTiles();
		
	}
	
	private void loadTiles() {
		
		for(int row = 0; row < mapData.length; row++) {
			
			for(int col = 0; col < mapData[row].length; col++) {
				
				int value = mapData[row][col];
				int x = col * GameSettings.TILE_SIZE;
				int y = row * GameSettings.TILE_SIZE;
				
				if(value == 0)
					tiles.add( new Tile(x, y, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE,"image/tileAssets/GrassTile.png", false) );
				else if(value == 1)
					tiles.add( new Tile(x, y, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE, "image/tileAssets/WaterTile.png", true) );
			}
			
		}
		
		
	}
	
	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
		for(Tile tile : tiles) {
			tile.update(deltaTime);
			
		}
	}
	
	
	public void draw(Graphics2D g) {
		for(Tile tile : tiles) {
			tile.draw(g);
			
		}
	}
	

	public ArrayList<Tile> getTiles(){
		return tiles;
		
	}
}
