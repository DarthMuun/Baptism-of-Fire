package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity{
	
	public static final String objName = "Capsule";
	
	public OBJ_Lantern(GamePanel gp) {
		super(gp);
		
		type = type_light;
		name = objName;
		down1 = setup("/objects/lantern",gp.tileSize,gp.tileSize);
		description = "[Capsule] \nDON'T FUCKING SELL IT!";
		price = 1000;
		lightRadius = 200;
	}

}
