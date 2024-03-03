package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_RiotShield extends Entity{
	
	public static final String objName = "Riot Shield";

	public OBJ_RiotShield (GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name = objName;
		down1 = setup("/objects/riotshield",gp.tileSize,gp.tileSize);
		defenseValue = 2;
		description = "[" + name + "]\nPrepare your weapons";
		price = 200;
	}
}
