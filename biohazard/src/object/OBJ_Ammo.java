package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Ammo extends Entity{
	
	GamePanel gp;
	
	public OBJ_Ammo(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = "Municion Misiles";
		value = 1;
		down1 = setup("/objects/full",gp.tileSize,gp.tileSize);	
		image = setup("/objects/full",gp.tileSize,gp.tileSize);	
		image2 = setup("/objects/empty",gp.tileSize,gp.tileSize);	
	}
	
	public boolean use (Entity entity) {
		
		gp.playSE(2);
		gp.ui.addMessage("Has Recuperado +" + value + " misil");
		entity.ammo += value;
		return true ;
	}

}
