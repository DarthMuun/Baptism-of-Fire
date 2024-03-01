package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_wrench extends Entity{
	
	public OBJ_wrench (GamePanel gp) {
		super(gp);
		
		type = type_WonderWeapon;
		name = "Llave Inglesa";
		down1 = setup("/objects/wrench",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nSi, hace mas daño... pero tienes menos alcance";
		attackValue = 2;
		attackArea.width = 32;
		attackArea.height = 32;
		knockBackPower = 5;
	}

}
