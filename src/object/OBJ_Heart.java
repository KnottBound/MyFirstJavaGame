package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {
	
	public OBJ_Heart(GamePanel gp) {
		
		super(gp);
		
		name = "Heart";			
		image = setup("/res/objects/heartfull", gp.tileSize, gp.tileSize);
		image2 = setup("/res/objects/hearthalf", gp.tileSize, gp.tileSize);
		image3 = setup("/res/objects/heartempty", gp.tileSize, gp.tileSize);

	}
}
