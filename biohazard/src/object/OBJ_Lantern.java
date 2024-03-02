package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity{
	
	public OBJ_Lantern(GamePanel gp) {
		super(gp);
		
		type = type_light;
		name = "Capsule";
		down1 = setup("/objects/lantern",gp.tileSize,gp.tileSize);
		description = "[Capsule] \nIf you don't use it you're fucked";
		price = 1000;
		lightRadius = 200;
	}

}
