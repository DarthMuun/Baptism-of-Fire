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
		
		dialogues[0] = "Bienvenidos!";
		dialogues[1] = "Ante ustedes...";
		dialogues[2] = "Mini_QS MK1";
		dialogues[3] = "Sigue Explorando!";
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
		
		super.speak();
	}
	
}
