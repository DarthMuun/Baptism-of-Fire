package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ItemOne extends Entity{
	
	public static final String objName = "StaminUp";

	public OBJ_ItemOne(GamePanel gp) {
		super(gp);
		
		name = objName;
		speed = 3;
		down1 = setup("/objects/staminup",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nPfft! thats gonna be savage";
		price = 300;
		stackable = true;
	}
	
}
