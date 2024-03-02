package enemies;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Ammo;
import object.OBJ_Blagh;
import object.OBJ_Heart;
import object.OBJ_Parts;

public class MTNT_Slime extends Entity{
	
	GamePanel gp;
	
	public MTNT_Slime(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_enemy;
		name = "Baby Gloop";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 6;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 3;
		projectile = new OBJ_Blagh(gp);
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		
		up1 = setup("/enemies/greenslime_down_1",gp.tileSize,gp.tileSize);
		up2 = setup("/enemies/greenslime_down_3",gp.tileSize,gp.tileSize);
		down1 = setup("/enemies/greenslime_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/enemies/greenslime_down_3",gp.tileSize,gp.tileSize);
		left1 = setup("/enemies/greenslime_down_1",gp.tileSize,gp.tileSize);
		left2 = setup("/enemies/greenslime_down_3",gp.tileSize,gp.tileSize);
		right1 = setup("/enemies/greenslime_down_1",gp.tileSize,gp.tileSize);
		right2 = setup("/enemies/greenslime_down_3",gp.tileSize,gp.tileSize);
		
	}
	
	public void setAction() {

		if(onPath == true) {
			
			//Check Stop Chasing
			checkStopChasingorNot(gp.player, 15, 100);
			
			//Search Direcion
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

		}
		else {
			
			//Check Start Chasing
			checkStartChasingorNot(gp.player, 5, 100);
			
			//Get a Random Directon
			getRandomDirection();

		}
	}

	
	public void damageAction() {
		
		actionLockCounter = 0;
		onPath = true;
	}
	public void checkDrop() {
	    // Cast a die
	    int i = new Random().nextInt(100) + 1;

	    // Set the monster drop
	    if (i < 50) {
	        dropItem(new OBJ_Parts(gp));
	    } else if (i < 75) {
	        dropItem(new OBJ_Heart(gp));
	    } else {
	        dropItem(new OBJ_Ammo(gp));
	    }
	}

}