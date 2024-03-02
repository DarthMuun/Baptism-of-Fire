package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ItemOne extends Entity{

	public OBJ_ItemOne(GamePanel gp) {
		super(gp);
		
		name = "Stamin Up";
		speed = 3;
		down1 = setup("/objects/staminup",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nPfft! los esteroides se quedan cortos";
		price = 300;
		stackable = true;
	}
	
}
