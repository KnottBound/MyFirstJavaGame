package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Helmet extends Entity {
	
	public OBJ_Helmet(GamePanel gp) {
		super(gp);
		
		name = "Chest";
		down1 = setup("/res/objects/helmet", gp.tileSize, gp.tileSize);
		}
}

