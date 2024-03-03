package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_PorraOne extends Entity{
	
	public OBJ_PorraOne (GamePanel gp) {
		super(gp);
		
		type = type_weapon;
		name = "Electric Baton";
		down1 = setup("/objects/porra",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nGood luck buddy";
		attackValue = 1;
		attackArea.width = 45;
		attackArea.height = 45;
		price = 150;
		knockBackPower = 10;
		motion1_duration = 5;
		motion2_duration = 15;
	}

}
