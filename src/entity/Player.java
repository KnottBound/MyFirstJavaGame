package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;


public class Player extends Entity {

	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public boolean attackCancelled = false;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp); // Calling constructor of super class
		

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
		getPlayerAttackImage();
		setItems();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23; // Player position on world map : Starting Position
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
		
		// PLAYER STATUS
		level = 1;
		maxLife = 6;
		life = maxLife;
		strength = 1; // The more Strength more Damage
		dexterity = 1; // The more Dex less Damage Receives
		exp = 0;
		nextLevelExp = 5;
		coin = 0;
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		projectile = new OBJ_Fireball(gp);
		attack = getAttack(); // Total Attack Value Strength + Weapon
		defense = getDefense(); // Total Defense Value Dexterity + Shield
	}
	
	public void setItems() {
		
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Key(gp));
	}

	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		
		return defense = dexterity * currentShield.defenseValue;
	}
	
	public void getPlayerImage() {
		
		up1 = setup("/res/player/up1", gp.tileSize, gp.tileSize);
		up2 = setup("/res/player/up2", gp.tileSize, gp.tileSize);
		down1 = setup("/res/player/down1", gp.tileSize, gp.tileSize);
		down2 = setup("/res/player/down2", gp.tileSize, gp.tileSize);
		right1 = setup("/res/player/right1", gp.tileSize, gp.tileSize);
		right2 = setup("/res/player/right2", gp.tileSize, gp.tileSize);
		left1 = setup("/res/player/left1", gp.tileSize, gp.tileSize);
		left2 = setup("/res/player/left2", gp.tileSize, gp.tileSize);
	}
	
	public void getPlayerAttackImage() {
		
		if(currentWeapon.type == type_sword) {
			attackUp1 = setup("/res/player/up1attack1", gp.tileSize, gp.tileSize * 2);
			attackUp2 = setup("/res/player/up2attack2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setup("/res/player/down1attack1", gp.tileSize, gp.tileSize * 2);
			attackDown2 = setup("/res/player/down1attack2", gp.tileSize, gp.tileSize * 2);
			attackRight1 = setup("/res/player/right1attack1", gp.tileSize * 2, gp.tileSize);
			attackRight2 = setup("/res/player/right2attack2", gp.tileSize * 2, gp.tileSize);
			attackLeft1 = setup("/res/player/left1attack1", gp.tileSize * 2, gp.tileSize);
			attackLeft2 = setup("/res/player/left2attack2", gp.tileSize * 2, gp.tileSize);	
		}
		if(currentWeapon.type == type_axe) {
			attackUp1 = setup("/res/player/up1attack1axe", gp.tileSize, gp.tileSize * 2);
			attackUp2 = setup("/res/player/up2attack2axe", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setup("/res/player/down1attack1axe", gp.tileSize, gp.tileSize * 2);
			attackDown2 = setup("/res/player/down1attack2axe", gp.tileSize, gp.tileSize * 2);
			attackRight1 = setup("/res/player/right1attack1axe", gp.tileSize * 2, gp.tileSize);
			attackRight2 = setup("/res/player/right2attack2axe", gp.tileSize * 2, gp.tileSize);
			attackLeft1 = setup("/res/player/left1attack1axe", gp.tileSize * 2, gp.tileSize);
			attackLeft2 = setup("/res/player/left2attack2axe", gp.tileSize * 2, gp.tileSize);	
		}
	}
	
	public void update() {
		
		if(attacking == true) { // Where we handle attacking
			
			attacking();
		}
		
		else if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
		
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
			
			// CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			// CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			// CHECK EVENT COLLISION
			gp.eHandler.checkEvent();
			
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false && keyH.enterPressed == false) {
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
			
			if(keyH.enterPressed == true && attackCancelled == false) {
				gp.playSE(7);
				attacking = true;
				spriteCounter = 0;
			}
			
			attackCancelled = false;
			gp.keyH.enterPressed = false;
			
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
		if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30) {
			
			// SET DEFAULT COORDINATES, DIRECTION AND USER
			projectile.set(worldX, worldY, direction, true, this);
			
			// ADD IT TO THE LIST
			gp.projectileList.add(projectile);
			
			shotAvailableCounter = 0;
			
			gp.playSE(10);
		}
		
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
		if(shotAvailableCounter < 30) { // Fire ball timer
			shotAvailableCounter++;
		}
	}
	
	public void attacking() {
		spriteCounter++;
		
		if(spriteCounter <= 5 ) { // FIRST 5 FRAMES OF ATTACK
			spriteNum = 1;
		}
		if(spriteCounter > 5 && spriteCounter <= 25) { // SECOND ATTACK PIC
			spriteNum = 2;
			
			// SAVE THE CURRENT WorldX, WorldY, solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			// Adjust player's worldX/Y for the attackArea
			switch(direction) {
				case "up": worldY -= attackArea.height; break;
				case "down": worldY += attackArea.height; break;
				case "left": worldX -= attackArea.width; break;
				case "right": worldX += attackArea.height;break;
			}
			
			// attackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;

			// Check monster collision with the update worldX, worldY, and solidArea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex, attack);
			
			// AFTER COLLISION - > Restore position
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void pickUpObject(int i) {
		
		String text;
		
		if (i != 999) { // Any number fine, just can not be in the OBJ array
			if(inventory.size() != maxInventorySize) {
				inventory.add(gp.obj[i]);
				gp.playSE(1);
				text = "Got a " + gp.obj[i].name + "!";
			} 
			else {
				text = "Your inventory is full!";
			}
			gp.ui.addMessage(text);
			gp.obj[i] = null;
		}
	}
	
	public void interactNPC(int i) {
		
		if(gp.keyH.enterPressed == true) {
			
		if (i != 999) { // Any number fine, just can not be in the OBJ array
				attackCancelled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();	
			}
		}
	}
	
	public void contactMonster(int i) {
		
		if (i != 999) {
		
			if(invincible == false && gp.monster[i].dying == false) {
				gp.playSE(5);
				
				 // DAMAGE CALCULATION
				 int damage = gp.monster[i].attack - defense; // This is damage amount
				 
				 if(damage < 0) {
					 damage = 0;
				 }
				
				life -= damage;
				invincible = true;
			}
		}
	}
	
	public void damageMonster(int i, int attack) {
		
		if(i != 999) {
			
			 if(gp.monster[i].invincible == false) {
				 
				 gp.playSE(6);
				 
				 
				 // DAMAGE CALCULATION
				 int damage = attack - gp.monster[i].defense; // This is damage amount
				 
				 if(damage < 0) {
					 damage = 0;
				 }
				 
				 gp.monster[i].life -= damage;
				 gp.ui.addMessage(damage + " damage!");
				 gp.monster[i].invincible = true;
				 gp.monster[i].damageReaction();
				 
				 if(gp.monster[i].life <= 0) {
				
					 gp.monster[i].dying = true;
					 gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
					 gp.ui.addMessage("exp +" + gp.monster[i].exp);
					 exp += gp.monster[i].exp;
					 checkLevelUp();
				 }
			 }
		} else {
			//System.out.println("Miss!");
		}
	}
	
	public void checkLevelUp() {
		
		if(exp >= nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp * 2;
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack(); // recalculate it
			defense = getDefense();
			gp.playSE(8);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You are level " + level + " now!\n" +
									"You feel stronger now.";
		}
	}
	
	public void selectItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
		
		if(itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_sword || selectedItem.type == type_axe) {
				currentWeapon = selectedItem;
				attack = getAttack();	
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_shield) {
				
				currentShield = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.type == type_consumable) {
				
				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch(direction) {
		case "up":
			if(attacking == false) {
				if (spriteNum == 1) { image = up1; }
				if (spriteNum == 2) { image = up2; }	
			}
			if(attacking == true) {
				tempScreenY = screenY - gp.tileSize; // This is due to sprite size
				if (spriteNum == 1) { image = attackUp1; }
				if (spriteNum == 2) { image = attackUp2; }	
			}
			break;
		case "down":
			if(attacking == false) {
			if (spriteNum == 1) { image = down1; }
			if (spriteNum == 2) { image = down2; }
			}
			if(attacking == true) {
				if (spriteNum == 1) { image = attackDown1; }
				if (spriteNum == 2) { image = attackDown2; }	
			}
			break;
		case "left":
			if(attacking == false) {
			if (spriteNum == 1) { image = left1; }
			if (spriteNum == 2) { image = left2; }
			}
			if(attacking == true) {
				tempScreenX = screenX - gp.tileSize; // This is due to sprite size
				if (spriteNum == 1) { image = attackLeft1; }
				if (spriteNum == 2) { image = attackLeft2; }	
			}
			break;
		case "right":
			if(attacking == false) {
			if (spriteNum == 1) { image = right1; }
			if (spriteNum == 2) { image = right2; }
			}
			if(attacking == true) {
				if (spriteNum == 1) { image = attackRight1; }
				if (spriteNum == 2) { image = attackRight2; }	
			}
			break;
		}
		
		if(invincible == true) {
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		
		// RESET ALPHA
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		// DEBUG TEXT
		//g2.setFont(new Font("Arial", Font.PLAIN, 26));
		//g2.setColor(Color.white);
		//g2.drawString("Invincible: " + invincibleCounter, 10, 400);
	}
}
