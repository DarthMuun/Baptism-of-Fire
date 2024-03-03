package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HealthElix extends Entity{
	
	public static final String objName = "Healing Infusion";
	
	GamePanel gp;
	
	public OBJ_HealthElix (GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		value = 5;
		down1 = setup("/objects/health",gp.tileSize,gp.tileSize);
		description = "[Health Potion]\nThe name say it you earn " + value + " pts of health";
		price = 100;
		stackable = true;

	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "You have recovered +" + name + " it's time to move";
		
	}
	
	public boolean use (Entity entity) {
		
		startDialogue(this,0);
		entity.life += value;
		gp.playSE(15);
		return true;
	}

}
