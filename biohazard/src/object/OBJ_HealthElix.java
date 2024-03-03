package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HealthElix extends Entity{
	
	GamePanel gp;
	
	public OBJ_HealthElix (GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = "Health Potion";
		value = 5;
		down1 = setup("/objects/health",gp.tileSize,gp.tileSize);
		description = "[Health Potion]\nThe name say it you earn " + value + " pts of health";
		price = 100;
		stackable = true;

	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "You use the " + name + " it's time to move";
		
		dialogues[1][0] = "What are you doing? \nAre you stupid?";
	}
	
	public boolean use (Entity entity) {
		
		startDialogue(this,0);
		entity.life += value;
		gp.playSE(15);
		return true;
	}

}
