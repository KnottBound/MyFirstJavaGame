package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed; // Declared boolean variables
	
	// DEBUG
	boolean checkDrawTime = false;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// We don't use this
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) { // If KeyEvent Pressed == true
			upPressed = true; // Assigned true to variable
		}
		if (code == KeyEvent.VK_S) { // If KeyEvent == true
			downPressed = true; // Assigned true to variable
		}
		if (code == KeyEvent.VK_A) { // If KeyEvent == true
			leftPressed = true; // Assigned true to variable
		}
		if (code == KeyEvent.VK_D) { // If KeyEvent == true
			rightPressed = true; // Assigned true to variable
		}
		
		// DEBUG
		if(code == KeyEvent.VK_T) {
			if(checkDrawTime == false) {
				checkDrawTime = true;
			} else if (checkDrawTime == true) {
				checkDrawTime = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) { // If KeyEvent Released == true
			upPressed = false; // Assigned false to variable
		}
		if (code == KeyEvent.VK_S) { // If KeyEvent == true
			downPressed = false; // Assigned false to variable
		}
		if (code == KeyEvent.VK_A) { // If KeyEvent == true
			leftPressed = false; // Assigned false to variable
		}
		if (code == KeyEvent.VK_D) { // If KeyEvent == true
			rightPressed = false; // Assigned false to variable
		}
	}

}
