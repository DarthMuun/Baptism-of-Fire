package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HealthElix extends Entity{
	
	GamePanel gp;
	
	public OBJ_HealthElix (GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = "Elixir de Curacion";
		value = 5;
		down1 = setup("/objects/health",gp.tileSize,gp.tileSize);
		description = "[Elixir de Curacion]\nEl Nombre lo dice, curate!" + value + ".";
		price = 100;

	}
	
	public boolean use (Entity entity) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "Has tomado el " + name + "\n" + "Has recuperado " + value + " de vida.";
		entity.life += value;
		gp.playSE(15);
		return true;
	}

}
