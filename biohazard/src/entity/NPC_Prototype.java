package entity;


import java.util.Random;

import main.GamePanel;


public class NPC_Prototype extends Entity {
	
	public NPC_Prototype(GamePanel gp) {
		super(gp);
		
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}

	public void getImage() {
		
		
		up1 = setup ("/npc/npc_up1",gp.tileSize,gp.tileSize);
		up2 = setup ("/npc/npc_up2",gp.tileSize,gp.tileSize);
		down1 = setup ("/npc/npc_down1",gp.tileSize,gp.tileSize);
		down2 = setup ("/npc/npc_down2",gp.tileSize,gp.tileSize);
		left1 = setup("/npc/npc_left1",gp.tileSize,gp.tileSize);
		left2 = setup ("/npc/npc_left2",gp.tileSize,gp.tileSize);
		left3 = setup ("/npc/npc_left3",gp.tileSize,gp.tileSize);
		right1 = setup("/npc/npc_right1",gp.tileSize,gp.tileSize);
		right2 = setup ("/npc/npc_right2",gp.tileSize,gp.tileSize);
		right3 = setup ("/npc/npc_right3",gp.tileSize,gp.tileSize);
	
		
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "Hello \nMr.Vargas";
		dialogues[0][1] = "Welcome to the frist \nTechnical Demo";
		dialogues[0][2] = "Explore the map and \nfind the timer";
		dialogues[0][3] = "Good luck!";
		
		dialogues[1][0] = "Hello Mr.Vargas";
		dialogues[1][1] = "Prepare for this...";
		dialogues[1][2] = "Every time that you return to the tunels \nall enemie will revive";
		dialogues[1][3] = "don' die okey?";
	}
	
	public void setAction() {
		
		actionLockCounter ++;
		
		if(actionLockCounter == 120) {
			
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <=50) {
				direction = "down";
			}
			if(i > 50 && i <=75) {
				direction = "left";
			}
			if(i > 75 && i <=100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
			
		}
		
	}
	
	public void speak() {
		
		facePlayer();
		startDialogue(this,dialogueSet);
	}
	
}
