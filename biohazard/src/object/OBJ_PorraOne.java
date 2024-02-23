package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_PorraOne extends Entity{
	
	public OBJ_PorraOne (GamePanel gp) {
		super(gp);
		
		name = "Porra Electrica";
		down1 = setup("/objects/porra",gp.tileSize,gp.tileSize);
		attackValue = 1;
	}

}
