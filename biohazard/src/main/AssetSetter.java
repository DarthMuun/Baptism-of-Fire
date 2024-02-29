package main;

import enemies.MTNT_Slime;
import entity.NPC_Prototype;
import object.OBJ_Ammo;
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
    	
    	int i = 0;
    	gp.obj[i] = new OBJ_Key(gp);
    	gp.obj[i].worldX = gp.tileSize*67;
    	gp.obj[i].worldY = gp.tileSize*79;
    	i++;
    	gp.obj[i] = new OBJ_wrench(gp);
    	gp.obj[i].worldX = gp.tileSize*24;
    	gp.obj[i].worldY = gp.tileSize*68;
    	i++;
    	gp.obj[i] = new OBJ_RiotShield(gp);
    	gp.obj[i].worldX = gp.tileSize*70;
    	gp.obj[i].worldY = gp.tileSize*13;
    	i++;
    	gp.obj[i] = new OBJ_HealthElix(gp);
    	gp.obj[i].worldX = gp.tileSize*24;
    	gp.obj[i].worldY = gp.tileSize*77;
    	i++;
    	gp.obj[i] = new OBJ_HealthElix(gp);
    	gp.obj[i].worldX = gp.tileSize*53;
    	gp.obj[i].worldY = gp.tileSize*34;
    	i++;
    	gp.obj[i] = new OBJ_Parts(gp);
    	gp.obj[i].worldX = gp.tileSize*95;
    	gp.obj[i].worldY = gp.tileSize*36;
    	i++;
    	gp.obj[i] = new OBJ_Parts(gp);
    	gp.obj[i].worldX = gp.tileSize*24;
    	gp.obj[i].worldY = gp.tileSize*81;
    	i++;
    	gp.obj[i] = new OBJ_Ammo(gp);
    	gp.obj[i].worldX = gp.tileSize*2;
    	gp.obj[i].worldY = gp.tileSize*79;
    	i++;
    }
    public void setNPC() {
    	
    	gp.npc[0] = new NPC_Prototype(gp);
    	gp.npc[0].worldX = gp.tileSize*24;
    	gp.npc[0].worldY = gp.tileSize*78;

    }
    
    
    public void setEnemies() {
    	
    	int i = 0;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*39;
    	gp.enemies[i].worldY = gp.tileSize*33;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*43;
    	gp.enemies[i].worldY = gp.tileSize*33;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*39;
    	gp.enemies[i].worldY = gp.tileSize*36;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*43;
    	gp.enemies[i].worldY = gp.tileSize*36;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*60;
    	gp.enemies[i].worldY = gp.tileSize*52;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*62;
    	gp.enemies[i].worldY = gp.tileSize*55;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*60;
    	gp.enemies[i].worldY = gp.tileSize*59;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*14;
    	gp.enemies[i].worldY = gp.tileSize*35;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*15;
    	gp.enemies[i].worldY = gp.tileSize*37;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*17;
    	gp.enemies[i].worldY = gp.tileSize*36;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*76;
    	gp.enemies[i].worldY = gp.tileSize*35;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*78;
    	gp.enemies[i].worldY = gp.tileSize*35;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*78;
    	gp.enemies[i].worldY = gp.tileSize*37;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*76;
    	gp.enemies[i].worldY = gp.tileSize*37;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*73;
    	gp.enemies[i].worldY = gp.tileSize*16;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*71;
    	gp.enemies[i].worldY = gp.tileSize*16;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*72;
    	gp.enemies[i].worldY = gp.tileSize*14;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*91;
    	gp.enemies[i].worldY = gp.tileSize*78;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*89;
    	gp.enemies[i].worldY = gp.tileSize*78;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*89;
    	gp.enemies[i].worldY = gp.tileSize*80;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*91;
    	gp.enemies[i].worldY = gp.tileSize*80;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*90;
    	gp.enemies[i].worldY = gp.tileSize*92;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*90;
    	gp.enemies[i].worldY = gp.tileSize*87;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*78;
    	gp.enemies[i].worldY = gp.tileSize*69;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*80;
    	gp.enemies[i].worldY = gp.tileSize*69;
    	i++;
    	gp.enemies[i] = new MTNT_Slime(gp);
    	gp.enemies[i].worldX = gp.tileSize*79;
    	gp.enemies[i].worldY = gp.tileSize*78;
    	i++;

    }
    
    public void setInteractiveTile() {
    	
    	int i = 0;
    	gp.iTile[i] = new IT_SoftWall(gp,65,35);i++;
    	gp.iTile[i] = new IT_SoftWall(gp,65,36);i++;
    	gp.iTile[i] = new IT_SoftWall(gp,65,37);i++;
    	gp.iTile[i] = new IT_SoftWall(gp,60,55);i++;
    	gp.iTile[i] = new IT_SoftWall(gp,61,55);i++;
    	gp.iTile[i] = new IT_SoftWall(gp,62,55);i++;
    	
    }
}