package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Fire2 extends Entity {

	GamePanel gp;
	
	public MON_Fire2(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = "Fire EleBlob";
		speed = 1;
		maxLife = 10;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 2;
		
		solidArea.x = 4;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/res/monster/FireBlob2-a", gp.tileSize, gp.tileSize);
		up2 = setup("/res/monster/FireBlob2-b", gp.tileSize, gp.tileSize);
		down1 = setup("/res/monster/FireBlob2-a", gp.tileSize, gp.tileSize);
		down2 = setup("/res/monster/FireBlob2-b", gp.tileSize, gp.tileSize);
		left1 = setup("/res/monster/FireBlob2-a", gp.tileSize, gp.tileSize);
		left2 = setup("/res/monster/FireBlob2-b", gp.tileSize, gp.tileSize);
		right1 = setup("/res/monster/FireBlob2-a", gp.tileSize, gp.tileSize);
		right2 = setup("/res/monster/FireBlob2-b", gp.tileSize, gp.tileSize);
	}
	
	public void setAction( ) {
		
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
	
	public void damageReaction() {
		
		actionLockCounter = 0;
		direction = gp.player.direction; // Move in player Direction
	}
}