package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {

	GamePanel gp;
	Font arial_40, arial_80B; //HOW YOU PUT TEXT ON UI
	BufferedImage keyImage; // HOW YOU PUT Image ON UI
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40); //HOW YOU PUT TEXT ON UI
		arial_80B = new Font("Arial", Font.BOLD, 80); //HOW YOU PUT TEXT ON UI
		OBJ_Key key = new OBJ_Key(gp); // HOW YOU PUT Image ON UI
		keyImage = key.image; // HOW YOU PUT Image ON UI
	}
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		if(gameFinished == true) {
			
			g2.setFont(arial_40); // HOW YOU PUT TEXT ON SCREEN
			g2.setColor(Color.white); //HOW YOU PUT TEXT ON SCREEN
			
			String text;
			int textLength;
			int x;
			int y;

			text = "You found the Treasure!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Returns length of Text
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenWidth / 2 - gp.tileSize * 3;
			g2.drawString(text, x, y);
			
			text = "Your time is: " + dFormat.format(playTime) + "!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Returns length of Text
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 + gp.tileSize * 4;
			g2.drawString(text, x, y);
			
			g2.setFont(arial_80B); // HOW YOU PUT TEXT ON SCREEN
			g2.setColor(Color.green); //HOW YOU PUT TEXT ON SCREEN
			text = "Congratulations!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Returns length of Text
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenWidth / 2 + gp.tileSize * 1;
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
			
		} else {
		
			g2.setFont(arial_40); // HOW YOU PUT TEXT ON SCREEN
			g2.setColor(Color.white); //HOW YOU PUT TEXT ON SCREEN
			//g2.drawString("Key: " + gp.player.hasKey, 25, 50); //HOW YOU PUT TEXT ON SCREEN
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null); // How you draw Image on UI
			g2.drawString("x " + gp.player.hasKey, 74, 65);
			
			// TIMER
			playTime += (double) 1 / 60; // Our FPS is 60 so we increment 1/60th each frame.
			g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11,65);
			
			//On screen Messages
			if(messageOn == true) {
				
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
				
				messageCounter++;
				
				if(messageCounter > 120) {
					
					messageCounter = 0;
					messageOn = false;
				}
			}
		}
	}
}
