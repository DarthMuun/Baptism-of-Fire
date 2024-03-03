package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ShieldOne extends Entity {
	
	public OBJ_ShieldOne (GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name = "Shield";
		down1 = setup("/objects/shiled",gp.tileSize,gp.tileSize);
		defenseValue = 1;
		description = "[" + name + "]\nHonestly is just a toy...";
		price = 50;
	}

}
