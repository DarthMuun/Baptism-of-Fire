package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_PorraOne extends Entity{
	
	public OBJ_PorraOne (GamePanel gp) {
		super(gp);
		
		type = type_weapon;
		name = "Porra Electrica";
		down1 = setup("/objects/porra",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nCool, pero solo hace 1 de daño WTF?";
		attackValue = 1;
		attackArea.width = 45;
		attackArea.height = 45;
		price = 150;
		knockBackPower = 10;
	}

}
