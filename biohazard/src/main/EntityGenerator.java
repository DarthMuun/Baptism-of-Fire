package main;

import entity.Entity;
import object.OBJ_Ammo;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_HealthElix;
import object.OBJ_Heart;
import object.OBJ_ItemOne;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Missile;
import object.OBJ_Parts;
import object.OBJ_PorraOne;
import object.OBJ_RiotShield;
import object.OBJ_ShieldOne;
import object.OBJ_wrench;

public class EntityGenerator {
	
	GamePanel gp;
	
	public EntityGenerator(GamePanel gp) {
		this.gp = gp;
	}
	public Entity getObject(String itemName) {
		
		Entity obj = null;
		
		switch(itemName) {
		case OBJ_wrench.objName: obj = new OBJ_wrench(gp); break;
		case OBJ_ItemOne.objName: obj = new OBJ_ItemOne(gp); break;
		case OBJ_Chest.objName: obj = new OBJ_Chest(gp); break;
		case OBJ_Parts.objName: obj = new OBJ_Parts(gp); break;
		case OBJ_Door.objName: obj = new OBJ_Door(gp); break;
		case OBJ_Missile.objName: obj = new OBJ_Missile(gp); break;
		case OBJ_Heart.objName: obj = new OBJ_Heart(gp); break;
		case OBJ_Key.objName: obj = new OBJ_Key(gp); break;
		case OBJ_Lantern.objName: obj = new OBJ_Lantern(gp); break;
		case OBJ_Ammo.objName: obj = new OBJ_Ammo(gp); break;
		case OBJ_HealthElix.objName: obj = new OBJ_HealthElix(gp); break;
		case OBJ_ShieldOne.objName: obj = new OBJ_ShieldOne(gp); break;
		case OBJ_RiotShield.objName: obj = new OBJ_RiotShield(gp); break;
		case OBJ_PorraOne.objName: obj = new OBJ_PorraOne(gp); break;
		
		}
		return obj;
	}

}
