package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Ammo extends Entity{
	
	GamePanel gp;
	
	public OBJ_Ammo(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Municion Misiles";
		image = setup("/objects/full",gp.tileSize,gp.tileSize);	
		image2 = setup("/objects/empty",gp.tileSize,gp.tileSize);	
	}

}
