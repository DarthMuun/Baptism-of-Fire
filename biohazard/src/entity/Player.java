package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Lantern;
import object.OBJ_Missile;
import object.OBJ_PorraOne;
import object.OBJ_ShieldOne;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public boolean lightUpdated = false;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;
        this.gp = gp;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        // Solid Area
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
    }
	
	public void setDefaultValues() {
	
		//Spawn
		worldX = gp.tileSize * 24;
		worldY = gp.tileSize * 79;
		
		defaultSpeed = 4;
		speed = defaultSpeed;
		direction = "up";
		
		//Status
		level = 1;
		maxLife = 6;
		life = maxLife;
		maxAmmo = 4;
		ammo = maxAmmo;
		strength = 1; //More strength = more damage
		dexterity = 1; // More dexterity = less damage recieves
		exp = 0;
		nextLevelExp = 100;
		parts = 0;
		currentWeapon = new OBJ_PorraOne(gp);
		currentShield = new OBJ_ShieldOne(gp);
		projectile = new OBJ_Missile(gp);
		currentLight = null;
		attack = getAttack(); // Total attack value decided by strength and weapon
		defense = getDefense(); // Total defense value decided by dexterity and shield
		
        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
        setDialogue();
		
	}	
	
	public void setDefaultPositions() {
		
		worldX = gp.tileSize * 24;
		worldY = gp.tileSize * 79;
		direction = "up";
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "LEVEL UP " + level + ".LVL";
	}
	
	public void restoreStatus() {
		
		life = maxLife;
		ammo = maxAmmo;
		speed = defaultSpeed;
		invincible = false;
		transparent = false;
		attacking = false;
		guarding = false;
		knockBack = false;
		lightUpdated = true;
	}
	
	public void setItems() {
		
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);

		
	}
	
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		motion1_duration = currentWeapon.motion1_duration;
		motion2_duration = currentWeapon.motion2_duration;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}
	
	public int getCurrentWeaponSlot() {
		
		int currentWeaponSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentWeapon) {
				currentWeaponSlot = i;
			}
		}
		return currentWeaponSlot;
	}
	
	public int getCurrentShieldSlot() {
		
		int currentShieldSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentShield) {
				currentShieldSlot = i;
			}
		}
		return currentShieldSlot;
	}
	
	public void getImage() {		
		
		up1 = setup ("/player/up1",gp.tileSize,gp.tileSize);
		up2 = setup ("/player/up2",gp.tileSize,gp.tileSize);
		down1 = setup ("/player/down1",gp.tileSize,gp.tileSize);
		down2 = setup ("/player/down2",gp.tileSize,gp.tileSize);
		left1 = setup ("/player/left1",gp.tileSize,gp.tileSize);
		left2 = setup ("/player/left2",gp.tileSize,gp.tileSize);
		left3 = setup ("/player/left3",gp.tileSize,gp.tileSize);
		right1 = setup ("/player/right1",gp.tileSize,gp.tileSize);
		right2 = setup ("/player/right2",gp.tileSize,gp.tileSize);
		right3 = setup ("/player/right3",gp.tileSize,gp.tileSize);
		
	}

	public void getAttackImage() {
		
		if(currentWeapon.type == type_weapon) {
			
			attackUp1 = setup("/player/hitup1",gp.tileSize,gp.tileSize);
			attackUp2 = setup("/player/hitup2",gp.tileSize,gp.tileSize);
			attackDown1 = setup("/player/hitdown1",gp.tileSize,gp.tileSize);
			attackDown2 = setup("/player/hitdown2",gp.tileSize,gp.tileSize);
			attackLeft1 = setup("/player/hitleft1",gp.tileSize,gp.tileSize);
			attackLeft2 = setup("/player/hitleft2",gp.tileSize,gp.tileSize);
			attackRight1 = setup("/player/hitright1",gp.tileSize,gp.tileSize);
			attackRight2 = setup("/player/hitright2",gp.tileSize,gp.tileSize);
		}
		
		if(currentWeapon.type == type_WonderWeapon) {
			
			attackUp1 = setup("/player/hitupwrench1",gp.tileSize,gp.tileSize);
			attackUp2 = setup("/player/hitupwrench2",gp.tileSize,gp.tileSize);
			attackDown1 = setup("/player/hitdownwrench1",gp.tileSize,gp.tileSize);
			attackDown2 = setup("/player/hitdownwrench2",gp.tileSize,gp.tileSize);
			attackLeft1 = setup("/player/hitleftwrench1",gp.tileSize,gp.tileSize);
			attackLeft2 = setup("/player/hitleftwrench2",gp.tileSize,gp.tileSize);
			attackRight1 = setup("/player/hitrightwrench1",gp.tileSize,gp.tileSize);
			attackRight2 = setup("/player/hitrightwrench2",gp.tileSize,gp.tileSize);
		}
		
	}
	
	public void getGuardImage() {
	
		guardUp = setup ("/player/guardup",gp.tileSize,gp.tileSize);
		guardDown = setup ("/player/guarddown",gp.tileSize,gp.tileSize);
		guardLeft = setup ("/player/guardleft",gp.tileSize,gp.tileSize);
		guardRight = setup ("/player/guardright",gp.tileSize,gp.tileSize);
		
		
	}
	
	public void update() {
		
		if (knockBack == true) {

		    collisionOn = false;
		    gp.cChecker.checkTile(this);
		    gp.cChecker.checkObject(this, true);
		    gp.cChecker.checkEntity(this, gp.npc);
		    gp.cChecker.checkEntity(this, gp.enemies);
		    gp.cChecker.checkEntity(this, gp.iTile);
			
			if (collisionOn == true) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			} 
			else if (collisionOn == false){
				switch(knockBackDirection) {
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			}
			
			knockBackCounter++;
			if (knockBackCounter == 10) {
				knockBackCounter = 0;
				knockBack = true;
				speed = defaultSpeed;
			}
		}
		else if(attacking == true) {
			attacking();
		}
		else if(keyH.guardPressed == true){
			guarding = true;
			guardCounter++;
		}
		else if(keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true || 
				keyH.interactPressed == true || keyH.shotKeyPressed == true) {
			
		    if (keyH.upPressed == true) {
		        direction = "up";
		    } else if (keyH.downPressed == true) {
		        direction = "down";
		    } else if (keyH.leftPressed == true) {
		        direction = "left";
		    } else if (keyH.rightPressed == true) {
		        direction = "right";
		    }
		    
		    //Check Tile Collision
		    collisionOn = false;
		    gp.cChecker.checkTile(this);
		    
		    //Check Object Collission
		    int objIndex = gp.cChecker.checkObject(this, true);
		    pickUpObject(objIndex);
		    
		    //Check NPC Collision
		    int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		    interactNPC(npcIndex);
		    
		    //Check Enemy Collision
		    int enemiesIndex = gp.cChecker.checkEntity(this, gp.enemies);
		    contactEnemies(enemiesIndex);
		    
		    //Cehck Interactive Tile Collision
		    gp.cChecker.checkEntity(this, gp.iTile);
		    
		    //Check Event
		    gp.eHandler.checkEvent();
		    
		    //If Collision is false, player can move
		    if(collisionOn == false && keyH.interactPressed == false) {
		    	
		    	switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
		    }
		    	
		}
		    
		    if(keyH.interactPressed == true && attackCanceled == false) {
		    	gp.playSE(7);
		    	attacking = true;
		    	spriteCounter =0;
		    	
		    	//Decrease Durability
		    	currentWeapon.durability--;
		    }
		  
		  attackCanceled = false;
		  gp.keyH.interactPressed = false;
		  guarding = false;
		  guardCounter = 0;
		    
		  spriteCounter++;
		    if (spriteCounter > 12) {
		    	if(spriteNum == 1) {
		    		spriteNum = 2;
		    	}
		    	else if(spriteNum ==2) {
		    		spriteNum = 1;
		    	}
		    	spriteCounter = 0;
		    }
			
		} else {			
			standCounter++;			
			if (standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
			guarding = false;
			guardCounter = 0;
		}
		
		if(gp.keyH.shotKeyPressed == true && projectile.alive == false 
				&& shotAvailableCounter == 30 && projectile.haveResource(this) == true) {
			
			//Set Default Coordinates, direction and user
			projectile.set(worldX, worldY, direction, true, this);
			
			//Substract the cost ammo
			projectile.substractResource(this);
			
			//Add it to the list
			gp.projectileList.add(projectile);
			shotAvailableCounter = 0;
			gp.playSE(10);
		}
		//This needs to be outside of key if statement
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;

				invincibleCounter = 0;
			}
		}
		if(shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
		if(life > maxLife) {
			life = maxLife;
		}
		if(ammo > maxAmmo) {
			ammo = maxAmmo;
		}
		if(life <=0) {
			gp.gameState = gp.gameOverState;
			gp.ui.commandNum = -1;
			gp.stopMusic();
			gp.playSE(12);
		}
	}

	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			//Pick up only items
			if(gp.obj[gp.currentMap][i].type == type_pickupOnly) {
				
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
			}
			//Obstacle
			else if (gp.obj[gp.currentMap][i].type == type_obstacle) {
				if(keyH.enterPressed == true) {
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}
			}
			
			//Intentory Items
			else {
				String text;
				
				if(canObtainItem(gp.obj[gp.currentMap][i]) == true) {
					gp.playSE(3);
					text = "Got a " + gp.obj[gp.currentMap][i].name;
				}
				else {
					text = "You cannot carry any more";
				}
				gp.ui.addMessage(text);
				gp.obj[gp.currentMap][i] = null;
			}
		}
	}		
	
	public void interactNPC(int i) {
			
			if(i != 999) {		
				
				if(gp.keyH.interactPressed == true) {
				attackCanceled = true;
				gp.npc[gp.currentMap][i].speak();
			}
		}	
	}
		
	public void contactEnemies(int i) {
		
		if(i != 999) {
			
			if(invincible == false && gp.enemies[gp.currentMap][i].dying == false) {
				gp.playSE(6);
				
				int damage = gp.enemies[gp.currentMap][i].attack - defense;
				if(damage < 0) {
					damage = 0;
				}
				life -= damage;
				invincible = true;
			}
		}
	}
	
	public void damageEnemy(int i,Entity attacker, int attack, int knockBackPower) {
		
		if(i != 999) {
			if(gp.enemies[gp.currentMap][i].invincible == false) {
				
				gp.playSE(5);
				
				if(knockBackPower > 0) {
					setKnockBack(gp.enemies[gp.currentMap][i],attacker, knockBackPower);
				}
				
				if(gp.enemies[gp.currentMap][i].offBalance == true) {
					attack *= 5;
				}
				
				int damage = attack - gp.enemies[gp.currentMap][i].defense;
				if(damage < 0) {
					damage = 0;
				}
				gp.enemies[gp.currentMap][i].life -= damage;
				gp.ui.addMessage(damage + " Total Damage");
				
				gp.enemies[gp.currentMap][i].invincible = true;
				gp.enemies[gp.currentMap][i].damageReaction();
				
				if(gp.enemies[gp.currentMap][i].life <= 0) {
					gp.enemies[gp.currentMap][i].dying = true;
					gp.ui.addMessage("You have kill " + gp.enemies[gp.currentMap][i].name);
					gp.ui.addMessage(gp.enemies[gp.currentMap][i].exp + " XP Earn ");
					exp += gp.enemies[gp.currentMap][i].exp;
					checkLevelUp();
				}
			}
		}
	}
	
	public void damageInteractiveTile(int i) {
		
		if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true 
				&& gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false) {
			
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;
			
			//Generate particle
			generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i]);
			
			if(gp.iTile[gp.currentMap][i].life == 0) {
				gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
			}
		}
	}
	
	public void checkLevelUp() {
		
		if (exp >= nextLevelExp) {
			
			level++;
			nextLevelExp = nextLevelExp*2;
			maxLife += 2;
			maxAmmo += 1;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
			gp.playSE(14);
			gp.gameState = gp.dialogueState;
			//dialogues[0][0] = "LEVEL UP " + level + ".LVL";
			setDialogue();
			startDialogue(this,0);
		}
	}
	
	public void selectItem() {
		
		int intemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol,gp.ui.playerSlotRow);
		
		if(intemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(intemIndex);
			
			if(selectedItem.type == type_weapon || selectedItem.type == type_WonderWeapon) {
				
				currentWeapon = selectedItem;
				attack = getAttack();
				getAttackImage();
			}
			
			if(selectedItem.type == type_shield ) {
				
				currentShield = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.type == type_light) {
				
				if(currentLight == selectedItem) {
					currentLight = null;
				}
				else {
					currentLight = selectedItem;
				}
				lightUpdated = true;
				
			}
			if(selectedItem.type == type_consumable ) {
				
				if(selectedItem.use(this) == true);{
					if(selectedItem.amount > 1) {
						selectedItem.amount --;
					}
					else {
						inventory.remove(intemIndex);
					}
				}
				
			}
		}
	}
	
	public int searchItemInInventory(String itemName) {
		
		int itemIndex = 999;
		
		for(int i = 0; i < inventory.size(); i ++) {
			if(inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	
	public boolean canObtainItem(Entity item) {
		
		boolean canObtain = false;
		
		Entity newItem = gp.eGenerator.getObject(item.name);
		
		//Stackable
		if(newItem.stackable == true) {
			
			int index = searchItemInInventory(item.name);
			
			if(index != 999) {
				inventory.get(index).amount++;
				canObtain = true;
			}
			else { //New Item
				if(inventory.size() != maxInventorySize) {
					inventory.add(newItem);
					canObtain = true;
				}
			}
		}
		else { //Not Stacable
			if(inventory.size() != maxInventorySize) {
				inventory.add(newItem);
				canObtain = true;
			}
		}
		return canObtain;
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = attackUp1;}
				if(spriteNum == 2) {image = attackUp2;}
			}
			if(guarding == true) {
				image = guardUp;
			}
			break;
		case "down":
			if(attacking == false) {
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = attackDown1;}
				if(spriteNum == 2) {image = attackDown2;}
			}
			if(guarding == true) {
				image = guardDown;
			}
			break;
		case "left":
			if(attacking == false) {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = attackLeft1;}
				if(spriteNum == 2) {image = attackLeft2;}
			}
			if(guarding == true) {
				image = guardLeft;
			}
			break;
		case "right":
			if(attacking == false) {
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = attackRight1;}
				if(spriteNum == 2) {image = attackRight2;}
			}
			if(guarding == true) {
				image = guardRight;
			}
			break;
		
		}
		
		if(transparent == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		
		
		//Reset Alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
	}
	
}
