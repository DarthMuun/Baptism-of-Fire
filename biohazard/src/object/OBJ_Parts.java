package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Parts extends Entity{
	
	public static final String objName = "Metal Parts";
	
	GamePanel gp;
	
	public OBJ_Parts(GamePanel gp){
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		value = 3;
		down1 = setup("/objects/parts",gp.tileSize,gp.tileSize);
	}
	public boolean use (Entity entity) {
		
		gp.playSE(20);
		gp.ui.addMessage("Has Recogido " + value + " partes");
		gp.player.parts += value;
		return true;
	}
}
