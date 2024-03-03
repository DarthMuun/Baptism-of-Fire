package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{
	
	public static final String objName = "Healh Point";

	GamePanel gp;
	
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		value = 2;
		down1 = setup("/objects/pnthealth",gp.tileSize,gp.tileSize);
		image = setup("/objects/heart_full",gp.tileSize,gp.tileSize);
		image2 = setup("/objects/heart_half",gp.tileSize,gp.tileSize);
		image3 = setup("/objects/heart_blank",gp.tileSize,gp.tileSize);
	}
	
	public boolean use (Entity entity) {
		
		gp.playSE(15);
		gp.ui.addMessage("You have recovered +" + value);
		entity.life += value;
		return true;
	}
}
