package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_RiotShield extends Entity{

	public OBJ_RiotShield (GamePanel gp) {
		super(gp);
		
		type = type_shield2;
		name = "Escudo Antidisturbios";
		down1 = setup("/objects/riotshield",gp.tileSize,gp.tileSize);
		defenseValue = 3;
		description = "[" + name + "]\nEsto se ponda bueno";
	}
}
