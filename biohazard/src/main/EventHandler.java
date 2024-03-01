package main;

import entity.Entity;

public class EventHandler {
	
	GamePanel gp;
	EventRect eventRect[][][];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	int tempMap, tempCol, tempRow;
    
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 64;
			eventRect[map][col][row].height = 64;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
				
				if (row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
		
	}	
	
	public void checkEvent() {
		
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if (distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if (canTouchEvent == true) {
		
			//Teleport "Tuneles"
			
			//IDA
			if (hit(0, 38, 79, "right")) { teleport1(0, 39, 62); }
			else if (hit(0, 39, 49, "up")) { teleport1(0, 41, 40); }
			else if (hit(0, 77, 46, "down")) { teleport1(0, 61, 49); }
			else if (hit(0, 61, 62, "down")) { teleport1(0, 96, 79); }
			else if (hit(0, 15, 33, "up")) { teleport1(0, 21, 19); }
			else if (hit(0, 77, 26, "up")) { teleport1(0, 72, 19); }
			
			//Retorno
			else if (hit(0, 39, 62, "down")) { teleport2(0, 38, 79); }
			else if (hit(0, 41, 40, "down")) { teleport2(0, 39, 49); }
			else if (hit(0, 61, 49, "up")) { teleport2(0, 77, 46); }
			else if (hit(0, 96, 79, "right")) { teleport2(0, 61, 62); }
			else if (hit(0, 21, 19, "down")) { teleport2(0, 15, 33); }
			else if (hit(0, 72, 19, "down")) { teleport2(0, 77, 26); }
			
			//NEW MAP
			else if (hit(0, 41, 14, "up")) { teleport1(1, 24, 81); }
			else if (hit(1, 24, 81, "down")) { teleport2(0, 41, 14); }
			
			//MrQS
			else if (hit(0, 24, 67, "up")) { speak(gp.npc[1][0]); }
		}		

	}
	
	public boolean hit(int map, int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		if (map == gp.currentMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
			
			if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
				if (gp.player.direction.equals(reqDirection) || reqDirection.equals("any")) {
					hit = true;
					
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
				}
			}
			
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		
		return hit;
	}
	
	public void teleport1(int map, int col,int row) {
		
		gp.gameState = gp.transitionState;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		canTouchEvent = false;
		gp.playSE(18);
		
	}
	
	public void teleport2(int map,int col,int row) {
		
		gp.gameState = gp.transitionState;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		canTouchEvent = false;
		gp.playSE(19);
		
	}
	
	public void speak(Entity entity) {
		
		if (gp.keyH.enterPressed) {
			gp.gameState = gp.dialogueState;
			gp.player.attackCanceled = true;
			entity.speak();
		}
	}
	
}
