package main;

import java.awt.Rectangle;

public class EventHandler {
	
	GamePanel gp;
	
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
    
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.width = 64;
		eventRect.height = 64;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
		
	}	
	
	public void checkEvent() {
		
		//Teleport "Tuneles"
		
		//IDA
		if(hit(38, 79, "right") == true) {teleport(gp.dialogueState);}
		if(hit(39, 49, "up") == true) {teleport2(gp.dialogueState);}
		if(hit(77, 46, "down") == true) {teleport3(gp.dialogueState);}
		if(hit(61, 62, "down") == true) {teleport4(gp.dialogueState);}
		if(hit(15, 33, "up") == true) {teleport5(gp.dialogueState);}
		if(hit(77, 26, "up") == true) {teleport11(gp.dialogueState);}
		
		//Retorno
		if(hit(39, 62, "down") == true) {teleport6(gp.dialogueState);}
		if(hit(41, 40, "down") == true) {teleport7(gp.dialogueState);}
		if(hit(61, 49, "up") == true) {teleport8(gp.dialogueState);}
		if(hit(96, 79, "right") == true) {teleport9(gp.dialogueState);}
		if(hit(21, 19, "down") == true) {teleport10(gp.dialogueState);}
		if(hit(72, 19, "down") == true) {teleport12(gp.dialogueState);}
		

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

	public void teleport(int gameState) {
		
		if (gp.keyH.interactPressed == true) {
		gp.playSE(2);
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Area A2";
		gp.player.worldX = gp.tileSize*39;
		gp.player.worldY = gp.tileSize*62;
		}
		gp.keyH.interactPressed = false;
		
	}
	
	public void teleport2(int gameState) {
		
	    if (gp.keyH.interactPressed == true) {
	    	gp.playSE(2);
	        gp.gameState = gameState;
	        gp.ui.currentDialogue = "Area A3";
	        gp.player.worldX = gp.tileSize * 41; 
	        gp.player.worldY = gp.tileSize * 40; 
	    }
	    gp.keyH.interactPressed = false;
	}   
    
	public void teleport3(int gameState) {
		
	    if (gp.keyH.interactPressed == true) {
	    	gp.playSE(2);
	        gp.gameState = gameState;
	        gp.ui.currentDialogue = "Area A4";
	        gp.player.worldX = gp.tileSize * 61; 
	        gp.player.worldY = gp.tileSize * 49;     
	    }
	    gp.keyH.interactPressed = false;
    }
	
	public void teleport4(int gameState) {
		
	    if (gp.keyH.interactPressed == true) {
	    	gp.playSE(2);
	        gp.gameState = gameState;
	        gp.ui.currentDialogue = "Area A5";
	        gp.player.worldX = gp.tileSize * 96; 
	        gp.player.worldY = gp.tileSize * 79;        
	    }
	    gp.keyH.interactPressed = false;
    }
	
	public void teleport5(int gameState) {
		
	    if (gp.keyH.interactPressed == true) {
	    	gp.playSE(2);
	        gp.gameState = gameState;
	        gp.ui.currentDialogue = "Area A7";
	        gp.player.worldX = gp.tileSize * 21; 
	        gp.player.worldY = gp.tileSize * 19;
	    }
	    gp.keyH.interactPressed = false;
    }
	
	
	public void teleport6(int gameState) {
		
	    if (gp.keyH.interactPressed == true) {
	    	gp.playSE(2);
	        gp.gameState = gameState;
	        gp.ui.currentDialogue = "Area A1";
	        gp.player.worldX = gp.tileSize * 38; 
	        gp.player.worldY = gp.tileSize * 79;   
	    }
	    gp.keyH.interactPressed = false;
    }
	
	public void teleport7(int gameState) {
		
	    if (gp.keyH.interactPressed == true) {
	    	gp.playSE(2);
	        gp.gameState = gameState;
	        gp.ui.currentDialogue = "Area A2";
	        gp.player.worldX = gp.tileSize * 39;
	        gp.player.worldY = gp.tileSize * 49;  
	    }
	    gp.keyH.interactPressed = false;
	}
	    
	public void teleport8(int gameState) {
		
	    if (gp.keyH.interactPressed == true) {
	    	gp.playSE(2);
	        gp.gameState = gameState;
	        gp.ui.currentDialogue = "Area A3";
	        gp.player.worldX = gp.tileSize * 77;
	        gp.player.worldY = gp.tileSize * 46;  
	    }
	    gp.keyH.interactPressed = false;
    }
	
	public void teleport9(int gameState) {
		
	    if (gp.keyH.interactPressed == true) {
	    	gp.playSE(2);
	        gp.gameState = gameState;
	        gp.ui.currentDialogue = "Area A4";
	        gp.player.worldX = gp.tileSize * 61;
	        gp.player.worldY = gp.tileSize * 62;
	    }
	    gp.keyH.interactPressed = false;
    }
	
	public void teleport10(int gameState) {
		
	    if (gp.keyH.interactPressed == true) {
	    	gp.playSE(2);
	        gp.gameState = gameState;
	        gp.ui.currentDialogue = "Area A3";
	        gp.player.worldX = gp.tileSize * 15; 
	        gp.player.worldY = gp.tileSize * 33;	        
	    }
	    gp.keyH.interactPressed = false;
    }
	
	public void teleport11(int gameState) {
		
	    if (gp.keyH.interactPressed == true) {
	    	gp.playSE(2);
	        gp.gameState = gameState;
	        gp.ui.currentDialogue = "Area A6";
	        gp.player.worldX = gp.tileSize * 72; 
	        gp.player.worldY = gp.tileSize * 19;        
	    }
	    gp.keyH.interactPressed = false;
    }
	
	public void teleport12(int gameState) {
		
	    if (gp.keyH.interactPressed == true) {
	    	gp.playSE(2);
	        gp.gameState = gameState;
	        gp.ui.currentDialogue = "Area A3";
	        gp.player.worldX = gp.tileSize * 77; 
	        gp.player.worldY = gp.tileSize * 26; 
	    }
	    gp.keyH.interactPressed = false;
    }
	public void damagePit(int gameState) {
		
		gp.gameState = gameState;
		gp.playSE(6);
		gp.ui.currentDialogue = "Duele no?";
		gp.player.life -= 1;
	}
	
	public void healingBlock(int gameState) {
		
		//if(gp.keyH.healPressed == true) {
			//gp.gameState = gameState;
			//gp.player.attackCanceled = true;
			//gp.playSE(3);
			//gp.ui.currentDialogue = "Estas curado!";
			//gp.player.life = gp.player.maxLife;
			//gp.aSetter.setEnemies();
		//}
		//gp.keyH.healPressed = false;
		
	}
}
