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
		speed = 1;
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
	
	public void update() {
		
		super.update();
		
		int xDistance = Math.abs(worldX - gp.player.worldX);
		int yDistance = Math.abs(worldY - gp.player.worldY);
		int tileDistance = (xDistance + yDistance)/gp.tileSize;
		
		if(onPath == false && tileDistance < 5) {
			int i = new Random().nextInt(100)+1;
			if(i > 50){
				onPath = true;
			}
		}

	}
	
	public void setAction() {
		
		int xDistance = Math.abs(worldX - gp.player.worldX);
		int yDistance = Math.abs(worldY - gp.player.worldY);
		int tileDistance = (xDistance + yDistance)/gp.tileSize;
		
		if(onPath == true){
			
			if(tileDistance >20){
				onPath = false;
			}
			
			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			
			searchPath(goalCol,goalRow);
			
			int i = new Random().nextInt(100)+1;
			if(i > 99 && projectile.alive == false && shotAvailableCounter == 30) {

				projectile.set(worldX, worldY, direction, true, this);
				gp.projectileList.add(projectile);
				shotAvailableCounter = 0;
			}
		}
		else {
			actionLockCounter ++;
			
			if(actionLockCounter == 120) {
				
				Random random = new Random();
				int i = random.nextInt(100)+1;
				
				if(i <= 25) {
					direction = "up";
				}
				if(i > 25 && i <=50) {
					direction = "down";
				}
				if(i > 50 && i <=75) {
					direction = "left";
				}
				if(i > 75 && i <=100) {
					direction = "right";
				}
				
				actionLockCounter = 0;
				
			}
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