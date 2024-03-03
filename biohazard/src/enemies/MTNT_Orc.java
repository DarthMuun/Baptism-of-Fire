package enemies;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Ammo;
import object.OBJ_Heart;
import object.OBJ_Parts;

public class MTNT_Orc extends Entity {

    GamePanel gp;

    public MTNT_Orc(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_enemy;
        name = "Abomination";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 8;
        defense = 2;
        exp = 10;
        knockBackPower = 1;
        
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 35;
        attackArea.height = 35;
        motion1_duration = 40;
        motion2_duration = 85;
        


        getImage();
        getAttackImage();
    }

    public void getImage() {
        up1 = setup("/enemies/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/enemies/orc_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/enemies/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/enemies/orc_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/enemies/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/enemies/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/enemies/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/enemies/orc_right_2", gp.tileSize, gp.tileSize);
    }

    public void getAttackImage() {
    	
        attackUp1 = setup("/enemies/orc_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/enemies/orc_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/enemies/orc_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/enemies/orc_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/enemies/orc_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/enemies/orc_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/enemies/orc_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/enemies/orc_attack_right_2", gp.tileSize * 2, gp.tileSize);
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
		
		//Check if it attacks
		if(attacking == false) {
			checkAttackOrNot(30, gp.tileSize*4, gp.tileSize);	}
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
