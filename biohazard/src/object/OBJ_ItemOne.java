package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ItemOne extends Entity{

	public OBJ_ItemOne(GamePanel gp) {
		super(gp);
		
		name = "ItemonOne";
		down1 = setup("/objects/staminup");
	}
	
}
