package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion extends Entity {
	
	GamePanel gp;
	
	public OBJ_Potion(GamePanel gp) {
		
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = "Red Potion";
		int value = 5;
		down1 = setup("/res/objects/potion", gp.tileSize, gp.tileSize);
		description = "[" + name +"]\nA Red Potion.\n That heals " + value + " Health.";	
	}
	
	public void use(Entity entity) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You drink the " + name + "! \n" 
				+ "Your life has been healed by " + value;
		entity.life += value;
		
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
		gp.playSE(2);
	}
}

