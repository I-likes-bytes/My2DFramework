package game.objects;

public class Coin extends InteractiveObject {

	private int value = 5;
	
	public Coin(int x, int y, int width, int height) {
		super(x, y, width, height);

		this.setImage("image/objectAssets/Coin.png");
	}

	
	@Override
	public void onInteract() {
		// TODO Auto-generated method stub
		if(!collected) {
			collect(); //Mark object as collected so it disappears
			System.out.println("Collected coin worth " + value + " dollars!");
			
		}
	}
	
	public int getValue() {
		return value;
		
	}
	
	
}
