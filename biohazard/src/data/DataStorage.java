package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{
	
	//Player Stats
	int level;
	int maxLife;
	int life;
	int maxAmmo;
	int ammo;
	int strength;
	int dexterity;
	int exp;
	int nextLevelExp;
	int parts;
	
	//Players Iventory
	
	ArrayList<String>itemNames = new ArrayList<>();
	ArrayList<Integer>itemAmounts = new ArrayList<>();
	int currentWeaponSlot;
	int currentShieldSlot;
	
	//Object on Map
	String mapObjectNames[][];
	int mapObjectWorldX[][];
	int mapObjectWorldY[][];
	String mapObjectLootNames[][];
	boolean mapObjectOpened[][];
	

}
