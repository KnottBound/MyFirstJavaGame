package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
import object.SuperObject;
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
	KeyHandler keyH = new KeyHandler();
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread; // Useful for repeating a process again and again like updating screen for FPS
	
	// ENTITY & OBJECT
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10]; // 10 slots of object
	
	// Create Constructor of Game Obj
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set size of this class (JPanel)
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // improves rendering performance.
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		
		playMusic(0);
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
	
		player.update();
	}

	public void paintComponent(Graphics g) { // Build into JFrame
		
		super.paintComponent(g); // Parent graph of 'this' 'super' graph which is JPanel
		
		Graphics2D g2 = (Graphics2D)g; // It has some functions for 2D games
		
		// DEBUG 
		long drawStart = 0;
		if(keyH.checkDrawTime == true) {
			drawStart = System.nanoTime();	
		} // END Debug
		
		// TILES
		tileM.draw(g2); // Like a layer, needs to be first.
		
		// OBJECT
		for (int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		// PLAYER
		player.draw(g2);
		
		//UI
		ui.draw(g2);
		
		// DEBUG
		if(keyH.checkDrawTime == true) {
		
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time: " + passed, 10, 400);
			System.out.println("Draw Time: " + passed);
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
