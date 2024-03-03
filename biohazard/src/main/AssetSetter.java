package main;

import enemies.MTNT_Orc;
import enemies.MTNT_Slime;
import entity.NPC_MrQS;
import entity.NPC_Prototype;
import object.OBJ_Ammo;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_HealthElix;
import object.OBJ_ItemOne;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Parts;
import object.OBJ_RiotShield;
import object.OBJ_wrench;
import tiles_interactive.IT_SoftWall;

public class AssetSetter {

    GamePanel gp;
    
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
   
    public void setObject() {
    	
    	int mapNum = 0;
    	int i = 0;
    	gp.obj[mapNum][i] = new OBJ_HealthElix(gp);
    	gp.obj[mapNum][i].worldX = gp.tileSize*26;
    	gp.obj[mapNum][i].worldY = gp.tileSize*70;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_HealthElix(gp);
    	gp.obj[mapNum][i].worldX = gp.tileSize*26;
    	gp.obj[mapNum][i].worldY = gp.tileSize*66;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_HealthElix(gp);
    	gp.obj[mapNum][i].worldX = gp.tileSize*22;
    	gp.obj[mapNum][i].worldY = gp.tileSize*66;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_HealthElix(gp);
    	gp.obj[mapNum][i].worldX = gp.tileSize*22;
    	gp.obj[mapNum][i].worldY = gp.tileSize*70;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_Lantern(gp);
    	gp.obj[mapNum][i].worldX = gp.tileSize*23;
    	gp.obj[mapNum][i].worldY = gp.tileSize*79;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_Chest(gp);
    	gp.obj[mapNum][i].setLoot(new OBJ_wrench(gp));
    	gp.obj[mapNum][i].worldX = gp.tileSize*24;
    	gp.obj[mapNum][i].worldY = gp.tileSize*96;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_Chest(gp);
    	gp.obj[mapNum][i].setLoot(new OBJ_HealthElix(gp));
    	gp.obj[mapNum][i].worldX = gp.tileSize*25;
    	gp.obj[mapNum][i].worldY = gp.tileSize*93;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_Chest(gp);
    	gp.obj[mapNum][i].setLoot(new OBJ_HealthElix(gp));
    	gp.obj[mapNum][i].worldX = gp.tileSize*23;
    	gp.obj[mapNum][i].worldY = gp.tileSize*88;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_Door(gp);
    	gp.obj[mapNum][i].worldX = gp.tileSize*41;
    	gp.obj[mapNum][i].worldY = gp.tileSize*32;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_Key(gp);
    	gp.obj[mapNum][i].worldX = gp.tileSize*21;
    	gp.obj[mapNum][i].worldY = gp.tileSize*15;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_RiotShield(gp);
    	gp.obj[mapNum][i].worldX = gp.tileSize*67;
    	gp.obj[mapNum][i].worldY = gp.tileSize*79;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_Chest(gp);
    	gp.obj[mapNum][i].setLoot(new OBJ_HealthElix(gp));
    	gp.obj[mapNum][i].worldX = gp.tileSize*95;
    	gp.obj[mapNum][i].worldY = gp.tileSize*35;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_Chest(gp);
    	gp.obj[mapNum][i].setLoot(new OBJ_HealthElix(gp));
    	gp.obj[mapNum][i].worldX = gp.tileSize*95;
    	gp.obj[mapNum][i].worldY = gp.tileSize*37;
    	i++;


    }
    public void setNPC() {
    	
       	//Map0
    	int mapNum = 0;
    	int i = 0;
    	gp.npc[mapNum][i] = new NPC_Prototype(gp);
    	gp.npc[mapNum][i].worldX = gp.tileSize*24;
    	gp.npc[mapNum][i].worldY = gp.tileSize*76;
    	i++;
    	
    	//Map1
    	mapNum = 1;
    	i = 0;
    	gp.npc[mapNum][i] = new NPC_MrQS(gp);
    	gp.npc[mapNum][i].worldX = gp.tileSize*24;
    	gp.npc[mapNum][i].worldY = gp.tileSize*67;
    	i++;
    	gp.npc[mapNum][i] = new NPC_Prototype(gp);
    	gp.npc[mapNum][i].worldX = gp.tileSize*24;
    	gp.npc[mapNum][i].worldY = gp.tileSize*75;
    	i++;

    }
    
    
    public void setEnemies() {
    	
    	int mapNum = 0;
    	int i = 0;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*39;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*34;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*42;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*34;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*41;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*35;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*51;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*36;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*55;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*34;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*55;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*38;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*75;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*38;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*75;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*34;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*79;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*34;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*79;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*38;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*76;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*27;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Orc(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*73;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*18;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Orc(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*71;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*18;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*61;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*56;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*60;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*61;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*62;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*61;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*92;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*77;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*92;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*81;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*88;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*81;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*88;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*77;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Orc(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*69;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*79;
    	i++;
     	gp.enemies[mapNum][i] = new MTNT_Orc(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*43;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*15;
    	i++;
     	gp.enemies[mapNum][i] = new MTNT_Orc(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*39;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*15;
    	i++;
     	gp.enemies[mapNum][i] = new MTNT_Orc(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*32;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*36;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*24;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*36;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*17;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*34;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*17;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*34;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*17;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*38;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Orc(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*21;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*12;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*24;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*15;
    	i++;
       	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*18;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*15;
    	i++;
    }
    
    public void setInteractiveTile() {
    	
    	int mapNum = 0;
    	int i = 0;
    	gp.iTile[mapNum][i] = new IT_SoftWall(gp,45,35);i++;
    	gp.iTile[mapNum][i] = new IT_SoftWall(gp,45,36);i++;
    	gp.iTile[mapNum][i] = new IT_SoftWall(gp,45,37);i++;
    	gp.iTile[mapNum][i] = new IT_SoftWall(gp,37,35);i++;
    	gp.iTile[mapNum][i] = new IT_SoftWall(gp,37,36);i++;
    	gp.iTile[mapNum][i] = new IT_SoftWall(gp,37,37);i++;
    	i++;

    }
}