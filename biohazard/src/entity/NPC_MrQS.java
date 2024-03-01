package entity;

import main.GamePanel;
import object.OBJ_HealthElix;
import object.OBJ_ItemOne;
import object.OBJ_Key;
import object.OBJ_PorraOne;
import object.OBJ_RiotShield;
import object.OBJ_ShieldOne;

public class NPC_MrQS extends Entity {
	
	public NPC_MrQS(GamePanel gp) {
		super(gp);
		
		
		direction = "down";
		speed = 0;
		
		getImage();
		setDialogue();
		setItems();
	}

	public void getImage() {
		
		
		up1 = setup ("/npc/MrQSDown1",gp.tileSize,gp.tileSize);
		up2 = setup ("/npc/MrQSDown2",gp.tileSize,gp.tileSize);
		down1 = setup ("/npc/MrQSDown1",gp.tileSize,gp.tileSize);
		down2 = setup ("/npc/MrQSDown2",gp.tileSize,gp.tileSize);
		left1 = setup("/npc/MrQSDown1",gp.tileSize,gp.tileSize);
		left2 = setup ("/npc/MrQSDown2",gp.tileSize,gp.tileSize);
		left3 = setup ("/npc/MrQSDown1",gp.tileSize,gp.tileSize);
		right1 = setup("/npc/MrQSDown2",gp.tileSize,gp.tileSize);
		right2 = setup ("/npc/MrQSDown1",gp.tileSize,gp.tileSize);
		right3 = setup ("/npc/MrQSDown2",gp.tileSize,gp.tileSize);
	
		
	}
	
	public void setDialogue() {
		
		dialogues[0] = "So... the world gets fucked \nbecause of me, right? Well, let's \nsee what you bring this time";

	}
	
	public void setItems() {
		
		inventory.add(new OBJ_HealthElix (gp));
		inventory.add(new OBJ_ItemOne (gp));
		inventory.add(new OBJ_PorraOne (gp));
		inventory.add(new OBJ_ShieldOne(gp));
		inventory.add(new OBJ_RiotShield (gp));
		inventory.add(new OBJ_Key (gp));

	}
	
	public void speak() {
		
		super.speak();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
}
