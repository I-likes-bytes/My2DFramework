package game;

import java.awt.Graphics2D;
import java.util.ArrayList;

import engine.GameObject;
import engine.Updatable;


public class WorldManager implements Updatable {

	private ArrayList<GameObject> objects; //Holds every object currently in the world
	private ArrayList<GameObject> solidObjects; //Holds solid object for object based collision detection
	
	private TileMap tileMap;
	
	
	public WorldManager() {
		objects = new ArrayList<>();
		solidObjects = new ArrayList<>();
		
		tileMap = new TileMap();
		
	}

	
	//Method to add object to the world
	public void addObject(GameObject obj) {
		objects.add(obj);
		
	}
	
	//Method to remove object from the world
	public void removeObject(GameObject obj) {
		objects.remove(obj);
		
	}
	
	//Updating all game objects
	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
		tileMap.update(deltaTime);
		
		for(GameObject obj : objects) {
			obj.update(deltaTime);
			
		}
		
	} 
	
	//Draw all game objects
	public void draw(Graphics2D g) {
		
		tileMap.draw(g);
		
		for(GameObject obj : objects) {
			obj.draw(g);
		}
		
	}
	
	//Getter to return all objects
	public ArrayList<GameObject> getObjects(){
		return objects;
		
	}
	
	//Getter to return all solid objects
	public ArrayList<GameObject> getSolidObjects(){
		return solidObjects;
		
	}
	
	//Getter for access to TileMap's methods.
	public TileMap getTileMap() {
		return tileMap;
		
	}

}
