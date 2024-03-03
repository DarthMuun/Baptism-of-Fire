package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ShieldOne extends Entity {
	
	public static final String objName = "Shield";
	
	public OBJ_ShieldOne (GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name = objName;
		down1 = setup("/objects/shiled",gp.tileSize,gp.tileSize);
		defenseValue = 1;
		description = "[" + name + "]\nHonestly is just a toy...";
		price = 50;
	}

}
