package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed; // Declared boolean variables
	
	// DEBUG
	boolean showDebugText = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// We don't use this
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
			
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			titleState(code);
		}
		
		// PLAY STATE
		else if(gp.gameState == gp.playState) {
				playState(code);
		}
		
		// PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		
		// DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}
			
		// CHARACTER STATE
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
	}

	public void titleState(int code) {
		if(gp.ui.titleScreenState == 0 ) {
			
			if (code == KeyEvent.VK_W) { // If KeyEvent Pressed == true
				gp.ui.commandNum--; // Assigned true to variable
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if (code == KeyEvent.VK_S) { // If KeyEvent == true
				gp.ui.commandNum++; // Assigned true to variable
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if (code == KeyEvent.VK_ENTER) { // If KeyEvent == true
				if(gp.ui.commandNum == 0) {
					
					gp.ui.titleScreenState = 1;
				}
				if(gp.ui.commandNum == 1) {
					// add later
				}
				if(gp.ui.commandNum == 2) {
					
					System.exit(0);
				}
			}	
		}
		else if(gp.ui.titleScreenState == 1 ) {
			
			if (code == KeyEvent.VK_W) { // If KeyEvent Pressed == true
				gp.ui.commandNum--; // Assigned true to variable
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3;
				}
			}
			if (code == KeyEvent.VK_S) { // If KeyEvent == true
				gp.ui.commandNum++; // Assigned true to variable
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if (code == KeyEvent.VK_ENTER) { // If KeyEvent == true
				if(gp.ui.commandNum == 0) {
					System.out.println("Thing Selected, do some stuff");
					gp.gameState = gp.playState; // Starting game here
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 1) {
					System.out.println("Thing 2 Selected, do some stuff");
					gp.gameState = gp.playState; // Starting game here
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 2) {
					System.out.println("Thing 3 Selected, do some stuff");
					gp.gameState = gp.playState; // Starting game here
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 3) {
					
					gp.ui.titleScreenState = 0;
				}
		}
	}
	}
	
	public void playState(int code) {
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
		if (code == KeyEvent.VK_P) { // If KeyEvent == true
			gp.gameState = gp.pauseState;
		}
		if (code == KeyEvent.VK_ENTER) { // If KeyEvent == true
			enterPressed = true;
		}
		if (code == KeyEvent.VK_F) { // If KeyEvent == true
			shotKeyPressed = true;
		}
		if (code == KeyEvent.VK_C) { // If KeyEvent == true
			gp.gameState = gp.characterState;
		}
		// DEBUG
		if(code == KeyEvent.VK_T) {
			if(showDebugText == false) {
				showDebugText = true;
			} else if (showDebugText == true) {
				showDebugText = false;
			}
		}
		if(code == KeyEvent.VK_R) {
			gp.tileM.loadMap("/res/maps/map01.txt");
		}
	}
	
	public void pauseState(int code) {
		if (code == KeyEvent.VK_P) { // If KeyEvent == true
			gp.gameState = gp.playState;
		}
	}
	
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
		}
	}
	
	public void characterState(int code) {
		
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_W) {
			if(gp.ui.slotRow != 0) {
				gp.ui.slotRow--;
				gp.playSE(9);	
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.slotCol != 0) {
				gp.ui.slotCol--;
				gp.playSE(9);	
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.slotRow != 3) {
				gp.ui.slotRow++;
				gp.playSE(9);	
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.slotCol != 4) {
				gp.ui.slotCol++;
				gp.playSE(9);	
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
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
		if (code == KeyEvent.VK_F) { // If KeyEvent == true
			shotKeyPressed = false;
		}
	}

}
