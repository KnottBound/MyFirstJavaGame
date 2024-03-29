package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2); // Returns half way point of width
		screenY = gp.screenHeight/2 - (gp.tileSize/2); // Returns half way point of height
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23; // Player position on world map : Starting Position
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		up1 = setup("up1");
		up2 = setup("up2");
		down1 = setup("down1");
		down2 = setup("down2");
		right1 = setup("right1");
		right2 = setup("right2");
		left1 = setup("left1");
		left2 = setup("left2");
	}
	
	public BufferedImage setup(String imageName) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
		
			if(keyH.upPressed == true) {
				
				direction = "up";

			} else if(keyH.downPressed == true) {
				
				direction = "down";

			} else if(keyH.leftPressed == true) {
				
				direction = "left";

			} else if(keyH.rightPressed == true) {
				
				direction = "right";

			}
			
			// CHECK PLAYER COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// CHECK OBJ COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) { // Switch case checking the player direction given when key pressed
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 12) { // Player image changes every 12 seconds.
				if(spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void pickUpObject(int i) {
		
		if (i != 999) { // Any number fine, just can not be in the OBJ array
		
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key": // obj interaction
				gp.playSE(1); // SFX
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You picked up an Orb.");
				System.out.println("Key: " + hasKey);
				break;
			case "Door": // Obj interaction
				if(hasKey > 0) {
					gp.playSE(2); 
					gp.obj[i] = null;
					hasKey--;
					gp.ui.showMessage("You opened the Door!");
				} else {
					gp.ui.showMessage("Door is locked!");
				}
				System.out.println("Key: " + hasKey);
				break;
			case "Helmet": // Obj interaction
				gp.playSE(1); 
				speed += 1;
				gp.obj[i] = null;
				gp.ui.showMessage("You picked up a Helmet of Speed!");
				break;
			case "Chest":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playSE(4);
				gp.ui.showMessage("You win");
				break;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, null);
	}
}
