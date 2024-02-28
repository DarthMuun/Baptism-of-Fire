package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HealthElix extends Entity{
	
	GamePanel gp;
	int value = 5;
	
	public OBJ_HealthElix (GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = "Elixir de Curacion";
		down1 = setup("/objects/health",gp.tileSize,gp.tileSize);
		description = "[Elixir de Curacion]\nEl Nombre lo dice, curate!" + value + ".";

	}
	
	public void use (Entity entity) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "Has tomado el " + name + "\n" + "Has recuperado " + value + " de vida.";
		entity.life += value;
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
		gp.playSE(3);
	}

}
