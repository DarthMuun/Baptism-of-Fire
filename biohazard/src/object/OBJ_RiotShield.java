package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_RiotShield extends Entity{

	public OBJ_RiotShield (GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name = "Riot Shield";
		down1 = setup("/objects/riotshield",gp.tileSize,gp.tileSize);
		defenseValue = 3;
		description = "[" + name + "]\nPrepare your weapons";
		price = 200;
	}
}
