package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Parts extends Entity{
	
	GamePanel gp;
	
	public OBJ_Parts(GamePanel gp){
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = "Metal Parts";
		value = 3;
		down1 = setup("/objects/parts",gp.tileSize,gp.tileSize);
	}
	public void use (Entity entity) {
		
		gp.playSE(20);
		gp.ui.addMessage("Has Recogido " + value + " partes");
		gp.player.parts += value;
	}
}
