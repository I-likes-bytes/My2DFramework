package engine;

public class GameLoop implements Runnable {

	 private boolean running = false;
	 private Thread thread;
	 private Updatable game;
	
	 public GameLoop(Updatable game) {
		 this.game = game;
		 
	 }
	 
	 public void start() {
		 if(running)
			 return;
		 
		 running = true;
		 thread = new Thread(this);
		 thread.start();
		 
	 }
	 
	 public void stop() {
		 running = false;
		 
		 try {	 
			 thread.join();
			 
		 } catch(InterruptedException e) {
			 e.printStackTrace();
			 
		 }
		 
	 }
	 
	@Override
	public void run() {
		
		
		/**
		 * There are 1000 milliseconds in a second. The target FPS is 60 so each frame should last
		 * 1,000 total milliseconds / 60 updates per 1,000 Millisecond interval (aka DESIRED FPS)
		 * 1 second = 1,000 milliseconds. 1,000 milliseconds / 60 intervals = 16.6666666667 = ~16.67 milliseconds per
		 * interval. 16.67 milliseconds is how many milliseconds should pass between updates.
		 * 
		 * (now - lastTime) is the amount of time that has passed since the last iteration. It's divided it by
		 * @variable msPerUpdate to convert the amount of time that has passed to a ratio measuring the amount of 
		 * time that has passed between intervals. When the ratio is 1 : 1 (when the amount of time that has passed
		 *  since the last update is equal to the amount of time in milliseconds alloted per update interval) 
		 *  continue to the inner loop performing an update on the Game's state. @variable delta is only decremented
		 *  by 1 to save any left over time and use it for the next interval, this left over time is used to help
		 *  smooth out the FPS in the case that the system doesn't execute exactly on time. 
		 *   
		 */
		
		final double msPerUpdate = 1000.0 / GameSettings.FPS; //16.67ms
        double delta = 0; //Tracks how many frames worth of time have been accumulated
        long lastTime = System.currentTimeMillis(); //Captures the time in milliseconds when the loop starts

        while (running) {
        
            long now = System.currentTimeMillis();
            delta += (now - lastTime) / msPerUpdate;
            lastTime = now;

            /**
             * Waiting for 16.67ms worth of time to have passed, trying to update every 1/60th of a second
             */
            while (delta >= 1) {
            	//Run an update on the Game state
                game.update(1.0 / GameSettings.FPS);
                delta--; 
                		
            }        
        }

	}

	
	
}
