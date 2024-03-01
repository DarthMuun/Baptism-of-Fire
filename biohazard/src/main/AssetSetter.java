package main;

import enemies.MTNT_Slime;
import entity.NPC_MrQS;
import entity.NPC_Prototype;
import object.OBJ_Ammo;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_HealthElix;
import object.OBJ_ItemOne;
import object.OBJ_Key;
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
    	gp.obj[mapNum][i] = new OBJ_wrench(gp);
    	gp.obj[mapNum][i].worldX = gp.tileSize*24;
    	gp.obj[mapNum][i].worldY = gp.tileSize*68;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_Door(gp);
    	gp.obj[mapNum][i].worldX = gp.tileSize*41;
    	gp.obj[mapNum][i].worldY = gp.tileSize*32;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_Key(gp);
    	gp.obj[mapNum][i].worldX = gp.tileSize*67;
    	gp.obj[mapNum][i].worldY = gp.tileSize*79;
    	i++;
    	gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key(gp));
    	gp.obj[mapNum][i].worldX = gp.tileSize*22;
    	gp.obj[mapNum][i].worldY = gp.tileSize*77;
    	i++;
    }
    public void setNPC() {
    	
    	//Map0
    	int mapNum = 0;
    	int i = 0;
    	gp.npc[mapNum][i] = new NPC_Prototype(gp);
    	gp.npc[mapNum][i].worldX = gp.tileSize*20;
    	gp.npc[mapNum][i].worldY = gp.tileSize*78;
    	i++;
    	
    	//Map1
    	mapNum = 1;
    	i = 0;
    	gp.npc[mapNum][i] = new NPC_MrQS(gp);
    	gp.npc[mapNum][i].worldX = gp.tileSize*24;
    	gp.npc[mapNum][i].worldY = gp.tileSize*67;
    	i++;

    }
    
    
    public void setEnemies() {
    	
    	int mapNum = 0;
    	int i = 0;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*39;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*33;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*43;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*33;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*39;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*36;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*43;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*36;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*60;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*52;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*62;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*55;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*60;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*59;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*14;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*35;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*15;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*37;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*17;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*36;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*76;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*35;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*78;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*35;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*78;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*37;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*76;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*37;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*73;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*16;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*71;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*16;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*72;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*14;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*91;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*78;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*89;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*78;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*89;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*80;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*91;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*80;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*90;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*92;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*90;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*87;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*78;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*69;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*80;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*69;
    	i++;
    	gp.enemies[mapNum][i] = new MTNT_Slime(gp);
    	gp.enemies[mapNum][i].worldX = gp.tileSize*79;
    	gp.enemies[mapNum][i].worldY = gp.tileSize*78;
    	i++;

    }
    
    public void setInteractiveTile() {
    	
    	int mapNum = 0;
    	int i = 0;
    	gp.iTile[mapNum][i] = new IT_SoftWall(gp,45,35);i++;
    	gp.iTile[mapNum][i] = new IT_SoftWall(gp,45,36);i++;
    	gp.iTile[mapNum][i] = new IT_SoftWall(gp,45,37);i++;
    	i++;
    }
}