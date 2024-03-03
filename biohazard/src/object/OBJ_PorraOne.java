package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_PorraOne extends Entity{
	
	public static final String objName = "Electric Baton";
	
	public OBJ_PorraOne (GamePanel gp) {
		super(gp);
		
		type = type_weapon;
		name = objName;
		down1 = setup("/objects/porra",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nGood luck buddy";
		attackValue = 2;
		attackArea.width = 45;
		attackArea.height = 45;
		price = 150;
		knockBackPower = 10;
		motion1_duration = 5;
		motion2_duration = 15;
	}

}
