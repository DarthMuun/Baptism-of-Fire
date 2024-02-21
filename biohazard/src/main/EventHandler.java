package main;

import java.awt.Rectangle;

public class EventHandler {
	
	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 64;
		eventRect.y = 64;
		eventRect.width = 64;
		eventRect.height = 64;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
	}	
	
	public void checkEvent() {
		
		if(hit(49,79,"any") == true) {damagePit(gp.dialogueState);}
		if(hit(49,68,"any") == true) {healingBlock(gp.dialogueState);}
	}
	
	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect.x = eventCol*gp.tileSize + eventRect.x;
		eventRect.y = eventRow*gp.tileSize + eventRect.y;
		
		if(gp.player.solidArea.intersects(eventRect)) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;
	}

	public void damagePit(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Duele no?";
		gp.player.life -= 1;
	}
	
	public void healingBlock(int gameState) {
		
		if(gp.keyH.healPressed == true) {
			gp.gameState = gameState;
			gp.ui.currentDialogue = "Estas curado!";
			gp.player.life = gp.player.maxLife;
		}
		gp.keyH.healPressed = false;
		
	}
}
