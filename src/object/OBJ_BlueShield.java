package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueShield extends Entity {

	public OBJ_BlueShield(GamePanel gp) {
		
		super(gp);
		type = type_shield;
		name = "Blue Shield";
		down1 = setup("/res/objects/blueshield", gp.tileSize, gp.tileSize);
		defenseValue = 3;
		description = "[" + name +"]\nA Blue Shield";
	}
}
