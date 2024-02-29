package main;

import entity.NPC_OldMan;
import monster.MON_Fire2;
import object.OBJ_Axe;
import object.OBJ_BlueShield;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	
	// Create and Add Objects to Scene
	public void setObject() {
		
		int i = 0;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = gp.tileSize * 13;
		gp.obj[i].worldY = gp.tileSize * 16;
		i++;
		gp.obj[i] = new OBJ_Coin_Bronze(gp);
		gp.obj[i].worldX = gp.tileSize * 11;
		gp.obj[i].worldY = gp.tileSize * 18;
		i++;
		gp.obj[i] = new OBJ_Coin_Bronze(gp);
		gp.obj[i].worldX = gp.tileSize * 12;
		gp.obj[i].worldY = gp.tileSize * 18;
		i++;
		gp.obj[i] = new OBJ_Coin_Bronze(gp);
		gp.obj[i].worldX = gp.tileSize * 13;
		gp.obj[i].worldY = gp.tileSize * 18;
		i++;
		gp.obj[i] = new OBJ_Axe(gp);
		gp.obj[i].worldX = gp.tileSize * 18;
		gp.obj[i].worldY = gp.tileSize * 18;
		i++;
		gp.obj[i] = new OBJ_BlueShield(gp);
		gp.obj[i].worldX = gp.tileSize * 20;
		gp.obj[i].worldY = gp.tileSize * 18;
		i++;
		gp.obj[i] = new OBJ_Potion(gp);
		gp.obj[i].worldX = gp.tileSize * 9;
		gp.obj[i].worldY = gp.tileSize * 11;
		i++;
		gp.obj[i] = new OBJ_Potion(gp);
		gp.obj[i].worldX = gp.tileSize * 29;
		gp.obj[i].worldY = gp.tileSize * 29;
		i++;
		gp.obj[i] = new OBJ_Potion(gp);
		gp.obj[i].worldX = gp.tileSize * 34;
		gp.obj[i].worldY = gp.tileSize * 34;
		i++;
		gp.obj[i] = new OBJ_ManaCrystal(gp);
		gp.obj[i].worldX = gp.tileSize * 30;
		gp.obj[i].worldY = gp.tileSize * 30;
		i++;
		gp.obj[i] = new OBJ_ManaCrystal(gp);
		gp.obj[i].worldX = gp.tileSize * 34;
		gp.obj[i].worldY = gp.tileSize * 31;
		i++;
		gp.obj[i] = new OBJ_Heart(gp);
		gp.obj[i].worldX = gp.tileSize * 39;
		gp.obj[i].worldY = gp.tileSize * 23;
		i++;
		gp.obj[i] = new OBJ_Heart(gp);
		gp.obj[i].worldX = gp.tileSize * 38;
		gp.obj[i].worldY = gp.tileSize * 32;
		i++;
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 21;
		gp.npc[0].worldY = gp.tileSize * 21;
	}
	
	public void setMonster() {
		
		int i = 0;
		
		gp.monster[i] = new MON_Fire2(gp);
		gp.monster[i].worldX = gp.tileSize * 23;
		gp.monster[i].worldY = gp.tileSize * 29;
		i++;
		
		gp.monster[i] = new MON_Fire2(gp);
		gp.monster[i].worldX = gp.tileSize * 18;
		gp.monster[i].worldY = gp.tileSize * 33;
		i++;
		
		gp.monster[i] = new MON_Fire2(gp);
		gp.monster[i].worldX = gp.tileSize * 33;
		gp.monster[i].worldY = gp.tileSize * 18;
		i++;
		
		gp.monster[i] = new MON_Fire2(gp);
		gp.monster[i].worldX = gp.tileSize * 23;
		gp.monster[i].worldY = gp.tileSize * 10;
		i++;
		
		gp.monster[i] = new MON_Fire2(gp);
		gp.monster[i].worldX = gp.tileSize * 16;
		gp.monster[i].worldY = gp.tileSize * 16;
		i++;
	}
}
