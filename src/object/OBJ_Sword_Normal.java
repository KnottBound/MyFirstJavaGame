package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

	public OBJ_Sword_Normal(GamePanel gp) {
		
		super(gp);
		type = type_sword;
		name = "Normal Sword";
		down1 = setup("/res/objects/sword", gp.tileSize, gp.tileSize);
		attackValue = 4;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "[" + name +"]\nAn Old Sword";
	}

}