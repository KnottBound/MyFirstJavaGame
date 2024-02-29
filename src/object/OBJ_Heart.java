package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {
	
	GamePanel gp;
	
	public OBJ_Heart(GamePanel gp) {
		
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = "Heart";
		value = 2;
		down1 = setup("/res/objects/heartfull", gp.tileSize, gp.tileSize); // We can place on map
		image = setup("/res/objects/heartfull", gp.tileSize, gp.tileSize);
		image2 = setup("/res/objects/hearthalf", gp.tileSize, gp.tileSize);
		image3 = setup("/res/objects/heartempty", gp.tileSize, gp.tileSize);

	}
	
	public void use(Entity entity) {
		gp.playSE(2);
		gp.ui.addMessage("Life + " + value);
		entity.life += value;
	}
}
