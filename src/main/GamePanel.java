package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{ // class inherits JPanel Class

	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile : default size of tiles
	final int scale = 3; // scale up tiles
	
	public final int tileSize = originalTileSize * scale; // 48x48 displayed on game screen
	
	// How many tiles horizontal & vertical? 
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // Width : 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // Height : 576 pixels
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	// Setting Frames per Second
	int FPS = 60;
	
	//SYSTEM
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread; // Useful for repeating a process again and again like updating screen for FPS
	
	// ENTITY & OBJECT
	public Player player = new Player(this,keyH);
	public Entity obj[] = new Entity[15]; // 10 slots of object
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	public ArrayList<Entity> projectileList = new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>();	

	// GAME STATE - Draw different things based on game states
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;

	
	// Create Constructor of Game Object
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set size of this class (JPanel)
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // improves rendering performance.
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		//playMusic(0);
		gameState = titleState;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this); // Instantiate Thread by passing GamePanel class to this thread constructor.
		gameThread.start();
	}

	@Override
	public void run() { // Runs when Thread gameThread called
		
		//Game LOOP : Core of Game!
		double drawInterval = 1000000000/FPS; // Drawing screen 0.016666 seconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) { // as long as gameThread exists repeats process
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				// 1 UPDATE: update information such as character position
				update();			
				// 2 DRAW: draw the screen with new information
				repaint(); // <- how you call paintComponent, little weird.	
				delta--;
				drawCount++;
			}
			
			if (timer >= 1000000000) {
				//Test for FPS: System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		
		if(gameState == playState ) {	
			// PLAYER
			player.update();	
			// NPC
			for (int i = 0; i < npc.length; i++) { // For Loop through all NPCs in array and update them all
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					
					if(monster[i].alive == true && monster[i].dying == false) {
						
						monster[i].update();	
					}
					if(monster[i].alive != true) {
						monster[i].checkDrop();
						monster[i] = null;	
					}
				}
			}
			for(int i = 0; i < projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					
					if(projectileList.get(i).alive == true) {
						
						projectileList.get(i).update();	
					}
					if(projectileList.get(i).alive != true) {
						
						projectileList.remove(i);	
					}
				}
			}
		}
		if(gameState == pauseState) {
			//nothing
			stopMusic();
		}
	}

	public void paintComponent(Graphics g) { // Build into JFrame
		
		super.paintComponent(g); // Parent graph of 'this' 'super' graph which is JPanel
		
		Graphics2D g2 = (Graphics2D)g; // It has some functions for 2D games
		
		// DEBUG 
		long drawStart = 0;
		if(keyH.showDebugText == true) {
			drawStart = System.nanoTime();	
		} // END Debug
		
		// TITLE SCREEN
		if(gameState == titleState) {
			
			ui.draw(g2);
		}
		// OTHER SCREENS
		else {
		
			// TILES
			tileM.draw(g2); // Like a layer, needs to be first.
			
			// ADD ALL ENTITIES TO LIST
			entityList.add(player);
			
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			for(int i = 0; i < projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					entityList.add(projectileList.get(i));
				}
			}
			
			
			// SORT
			Collections.sort(entityList, new Comparator<Entity>() { // Compare entities by specified properties

				@Override
				public int compare(Entity e1, Entity e2) {

					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
				
			});
			
			// DRAW ENTITIES
			for(int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			
			// EMPTY ENTITY LIST
				entityList.clear();
			
			//UI
			ui.draw(g2);
		}
		
		// DEBUG
		if(keyH.showDebugText == true) {
		
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setFont(new Font("Arial", Font.PLAIN, 20));
			g2.setColor(Color.white);
			int x = 10;
			int y = 400;
			int lineHeight = 20;
			
			g2.drawString("WorldX" + player.worldX, x, y); 
			y += lineHeight;
			g2.drawString("WorldY" + player.worldY, x, y);
			y += lineHeight;
			g2.drawString("Col" + (player.worldX + player.solidArea.x) / tileSize, x, y);
			y += lineHeight;
			g2.drawString("Row" + (player.worldY + player.solidArea.y) / tileSize, x, y);
			y += lineHeight;
			g2.drawString("Draw Time: " + passed, x, y);
			y += lineHeight;
		} // End Debug
		
		g2.dispose(); // Disposes of g2 and releases resources
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		
		music.stop();
	}
	
	public void playSE(int i) {
		
		se.setFile(i);
		se.play();
	}
}
