package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity{

	
	public NPC_OldMan (GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		
		up1 = setup("/res/npc/up1npc", gp.tileSize, gp.tileSize);
		up2 = setup("/res/npc/up2npc", gp.tileSize, gp.tileSize);
		down1 = setup("/res/npc/down1npc", gp.tileSize, gp.tileSize);
		down2 = setup("/res/npc/down2npc", gp.tileSize, gp.tileSize);
		right1 = setup("/res/npc/right1npc", gp.tileSize, gp.tileSize);
		right2 = setup("/res/npc/right2npc", gp.tileSize, gp.tileSize);
		left1 = setup("/res/npc/left1npc", gp.tileSize, gp.tileSize);
		left2 = setup("/res/npc/left2npc", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0] = "Hello, there!";
		dialogues[1] = "Are you looking for treasure?";
		dialogues[2] = "I am the most sexy of all the sexy \npeople in the entire island. \nComing look at my sexiness please.";
		dialogues[3] = "Well, good luck!";
		
	}
	
	public void setAction() { // NPC AI
		
		actionLockCounter++;
		
		if(actionLockCounter == 120) { // Won't change for next 120 frames.
			
			Random random = new Random();
			int i = random.nextInt(100) + 1; // Pick a number from 1 to 100
			
			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";
			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
			
		}
		
	}
	
	public void speak() { 
		super.speak();
		// Maybe we want character specific stuff like an item or something. Makes customizing in future easier
	}
}
