package game.world;

import java.awt.Graphics2D;
import java.util.ArrayList;

import engine.core.GameObject;
import engine.core.Updatable;

import game.objects.InteractiveObject;


public class WorldManager implements Updatable {

	private ArrayList<GameObject> allObjects; //Everything that updates/draws
	private ArrayList<GameObject> solidObjects; //Collision detection
	private ArrayList<InteractiveObject> interactiveObjects; //Things the player interacts with
	
	
	private TileMap tileMap;
	
	
	public WorldManager() {
		allObjects = new ArrayList<>();
		solidObjects = new ArrayList<>();
		interactiveObjects = new ArrayList<>();
		
		tileMap = new TileMap();
		
	}

	
	//Method to add object to the world
	public void addObject(GameObject obj) {
		allObjects.add(obj);
		
		//Classify object based on type or property
		if(obj.isSolid()) {
			solidObjects.add(obj); //COMEBACK AND DELTE
		}
		
		//Pattern Matching for instanceof is actually really cool
		if(obj instanceof InteractiveObject interactive) {
			interactiveObjects.add(interactive);
		}
		
	}
	
	//Method to remove object from the world
	public void removeObject(GameObject obj) {
		allObjects.remove(obj);
		solidObjects.remove(obj);
		
		if(obj instanceof InteractiveObject interactive) {
			interactiveObjects.remove(interactive);
		}
		
	}
	
	//Updating all game objects
	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
		tileMap.update(deltaTime);
		
		//Update all objects
		for(GameObject obj : allObjects) {
			obj.update(deltaTime);
			
			
			//Remove if InteractiveObject and has been collected
			if(obj instanceof InteractiveObject ) {
				InteractiveObject interactive = (InteractiveObject) obj;
				
				if(interactive.isCollected()) {
					removeObject(interactive);
					
				}
			}
			
		}
		
	} 
	
	//Draw all game objects
	public void draw(Graphics2D g) {
		
		tileMap.draw(g);
		
		for(GameObject obj : allObjects) {
			obj.draw(g);
		}
		
	}
	
	//Getter to return all objects
	public ArrayList<GameObject> getObjects(){
		return allObjects;
		
	}
	
	//Getter to return all solid objects
	public ArrayList<GameObject> getSolidObjects(){
		return solidObjects;
		
	}
	
	public ArrayList<InteractiveObject> getInteractiveObjects(){
		return interactiveObjects;
		
	}
	
	//Getter for access to TileMap's methods.
	public TileMap getTileMap() {
		return tileMap;
		
	}

}
