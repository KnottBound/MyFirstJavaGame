package main;

public class EventHandler {

	GamePanel gp;
	EventRect eventRect[][];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow]; // We have an eventRect on every tile
		
		int col = 0;
		int row = 0;
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
		
			eventRect[col][row] = new EventRect(); // Trigger Point
			eventRect[col][row].x = 23;
			eventRect[col][row].y = 23;
			eventRect[col][row].width = 2;
			eventRect[col][row].height = 2;
			eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
			eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
	
	public void checkEvent() {
		
		// CHECK IF PLAYER CHARACTER IS MORE THAN ! TILE AWAY FROM LAST EVENT
		int xDistance = Math.abs(gp.player.worldX - previousEventX); // return absolute values of calculation, even if negative, pure distance
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance); // great number btw the too
		if(distance > gp.tileSize) { // if greater than 1 tile the event can now re-trigger
			canTouchEvent = true;
		}
		
		if(canTouchEvent == true) {
		
			// EVENTS
			//if (hit(13, 13, "up" ) == true) { teleport(gp.dialogueState);}
			if (hit(27, 16, "right" ) == true) { damagePit(27, 16, gp.dialogueState);}
			if (hit(29, 16, "any" ) == true) { damagePit(27, 16, gp.dialogueState);}
			if (hit(7, 6, "up" ) == true) { healingPool(7, 6, gp.dialogueState);}
		}
	}
	
	public boolean hit(int col, int row, String reqDirection) { // Checks Event Collision
		
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
	
		if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
				
				previousEventX = gp.player.worldX; // Saving cord of where event happened
				previousEventY = gp.player.worldY;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
		
		return hit;
		
	}
	
	public void teleport(int col, int row, int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You Teleported!";
		gp.player.worldX = gp.tileSize * 16;
		gp.player.worldY = gp.tileSize * 23;
	
	}
	
	public void damagePit(int col, int row, int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = " You fall into a pit!";
		gp.player.life -= 1;
		
		//eventRect[col][row].eventDone = true; <- This one is for one time damage
		canTouchEvent = false;
	}
	
	public void healingPool(int col, int row, int gameState) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCancelled = true;
			gp.ui.currentDialogue = "You drink and your health and mana \n have been recovered!";
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			
			gp.aSetter.setMonster();
		}
	}
}
