package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {
	
	public static final String objName = "Key";

    GamePanel gp;

    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable; 
        name = objName;
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nGo and open the door";
        price = 500;
        stackable = false;
        
        setDialogue();
    }
    
	public void setDialogue() {
		
		dialogues[0][0] = "You use the " + name + " it's time to move";
		
		dialogues[1][0] = "What are you doing? \nAre you stupid?";
	}
	
    public boolean use(Entity entity) {

        int objIndex = getDetected(entity, gp.obj, "Door");

        if (objIndex != -1) { 
            startDialogue(this,0);
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        } else {
        	startDialogue(this,0);
            return false; 
        }
    }
	
}
