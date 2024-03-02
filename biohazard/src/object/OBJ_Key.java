package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    GamePanel gp;

    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable; // Corregir el valor de type
        name = "Key";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize); // Asegúrate de definir setup o reemplazarlo
        description = "[" + name + "]\nGo and open the door";
        price = 500;
        stackable = false;
    }
	
	 public boolean use(Entity entity) {

		 gp.gameState = gp.dialogueState;

		 int objIndex = getDetected(entity, gp.obj, "Door");// Llamar a los métodos getObj() y getDetected() correctamente

	        if (objIndex != -1) { // Cambiar 999 por -1
				gp.ui.currentDialogue = "You use the " + name + " it's time to move";
				gp.playSE(3);
				gp.obj[gp.currentMap][objIndex] = null;
				return true;
	        } else {
				gp.ui.currentDialogue = "What are you doing? \nr u stupid?";
				return true;// Devolver false en lugar de true
	        }
	    }
	
}
