package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_wrench extends Entity{
	
	public OBJ_wrench (GamePanel gp) {
		super(gp);
		
		type = type_WonderWeapon;
		name = "Wrench";
		down1 = setup("/objects/wrench",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nYou can destroy metal with this";
		attackValue = 1;
		attackArea.width = 32;
		attackArea.height = 32;
		knockBackPower = 5;
		motion1_duration = 20;
		motion2_duration = 40;
	}

}
