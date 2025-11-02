package engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputManager implements KeyListener {

    private final Map<Integer, Boolean> keyStates = new HashMap<>();

    public boolean isKeyPressed(int keyCode) {
    	
    	//returns boolean value associate with the specified key, if key exists in map
    	// the method returns the corresponding state. If the key is not found it returns
    	// false, the provided default value.
        return keyStates.getOrDefault(keyCode, false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyStates.put(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyStates.put(e.getKeyCode(), false);
    }

    
    
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    
}