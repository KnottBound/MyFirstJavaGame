package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Helmet;
import object.OBJ_Key;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	
	// Create and Add Objects to Scene
	public void setObject() {
		
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 3 * gp.tileSize;
		gp.obj[0].worldY = 47 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = 24 * gp.tileSize;
		gp.obj[1].worldY = 13 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Key(gp);
		gp.obj[2].worldX = 4 * gp.tileSize;
		gp.obj[2].worldY = 3 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Door(gp);
		gp.obj[3].worldX = 13 * gp.tileSize;
		gp.obj[3].worldY = 16 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Chest(gp);
		gp.obj[4].worldX = 13 * gp.tileSize;
		gp.obj[4].worldY = 13 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Helmet(gp);
		gp.obj[5].worldX = 9 * gp.tileSize;
		gp.obj[5].worldY = 15 * gp.tileSize;
		
	}
}
