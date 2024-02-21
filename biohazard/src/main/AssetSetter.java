package main;

import enemies.MTNT_Slime;
import entity.NPC_Prototype;
import object.OBJ_Door;

public class AssetSetter {

    GamePanel gp;
    
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject() {
    }
    public void setNPC() {
    	
    	gp.npc[1] = new NPC_Prototype(gp);
    	gp.npc[1].worldX = gp.tileSize*50;
    	gp.npc[1].worldY = gp.tileSize*68;

    }
    
    
    public void setEnemies() {
    	
    	gp.enemies[0] = new MTNT_Slime(gp);
    	gp.enemies[0].worldX = gp.tileSize*65;
    	gp.enemies[0].worldY = gp.tileSize*83;
  
    }
}