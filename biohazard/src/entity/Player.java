package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Missile;
import object.OBJ_PorraOne;
import object.OBJ_ShieldOne;

public class Player extends Entity {
	
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	int standCounter =0;
	public boolean attackCanceled = false;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 45;
	

	public Player(GamePanel gp, KeyHandler keyH) {
				
		super(gp);
		this.keyH = keyH;
		this.gp = gp;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		//Solid Area
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		//Attack Area
		//attackArea.width = 40;
		//attackArea.height = 40;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
	}
	
	public void setDefaultValues() {
	
		//Spawn
		worldX = gp.tileSize * 24;
		worldY = gp.tileSize * 79;
		speed = 4;
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
		attack = getAttack(); // Total attack value decided by strength and weapon
		defense = getDefense(); // Total defense value decided by dexterity and shield
		
	}	
	
	public void setDefaultPositions() {
		
		worldX = gp.tileSize * 24;
		worldY = gp.tileSize * 79;
		direction = "up";
	}
	
	public void restoreLifeAndAmmo() {
		
		life = maxLife;
		ammo = maxAmmo;
		invincible = false;
	}
	
	public void setItems() {
		
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);

		
	}
	
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}
	
	public void getPlayerImage() {		
		
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

	public void getPlayerAttackImage() {
		
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
	
	public void update() {
		
		if(attacking == true) {
			attacking();
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
		    int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
		    
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
		    }
		  
		  attackCanceled = false;
		  gp.keyH.interactPressed = false;
		    
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
			gp.playSE(12);
		}
	}
	
	public void attacking() {
		
		spriteCounter++;
		
		if(spriteCounter <= 5) {
			spriteNum = 1;
		}
		if(spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
			
			//Save current worldX and worldY, solid area
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			//Adjust players worldX/Y for the attackArea
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.height; break;
			case "right": worldX += attackArea.height; break;
			}
			
			//atackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			//Check Monster collision with the updated worldX, worldY and solid area
			int enemiesIndex = gp.cChecker.checkEntity(this, gp.enemies);
			damageEnemy(enemiesIndex, attack);
			
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			damageInteractiveTile(iTileIndex);
			
			//After checking collision, restore the original data
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
			
		}
		if(spriteCounter > 12) {
			spriteNum = 1;
			spriteCounter = 5;
			attacking = false;
		}
	}
	
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			//Pick up only items
			if(gp.obj[i].type == type_pickupOnly) {
				
				gp.obj[i].use(this);
				gp.obj[i] = null;
			}
			//Intentory Items
			else {
				String text;
				
				if(inventory.size() != maxInventorySize) {
					
					inventory.add(gp.obj[i]);
					gp.playSE(3);
					text = "Has recogido " + gp.obj[i].name;
				}
				else {
					text = "No puedes llevar mas de 2";
				}
				gp.ui.addMessage(text);
				gp.obj[i] = null;
			}
		}
	}		
	
	public void interactNPC(int i) {
		
		if(gp.keyH.interactPressed == true) {
			
			if(i != 999) {			
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
			else {
				//gp.playSE(11);
				attacking= true;
			}
		}	
	}
		
	public void contactEnemies(int i) {
		
		if(i != 999) {
			
			if(invincible == false && gp.enemies[i].dying == false) {
				gp.playSE(6);
				
				int damage = gp.enemies[i].attack - defense;
				if(damage < 0) {
					damage = 0;
				}
				life -= damage;
				invincible = true;
			}
		}
	}
	
	public void damageEnemy(int i, int attack) {
		
		if(i != 999) {
			if(gp.enemies[i].invincible == false) {
				gp.playSE(5);
				
				int damage = attack - gp.enemies[i].defense;
				if(damage < 0) {
					damage = 0;
				}
				gp.enemies[i].life -= damage;
				gp.ui.addMessage(damage + " Daño Total");
				
				gp.enemies[i].invincible = true;
				gp.enemies[i].damageReaction();
				
				if(gp.enemies[i].life <= 0) {
					gp.enemies[i].dying = true;
					gp.ui.addMessage("Has matado a un " + gp.enemies[i].name);
					gp.ui.addMessage(gp.enemies[i].exp + " XP Obtenida ");
					exp += gp.enemies[i].exp;
					checkLevelUp();
				}
			}
		}
	}
	
	public void damageInteractiveTile(int i) {
		
		if(i != 999 && gp.iTile[i].destructible == true 
				&& gp.iTile[i].isCorrectItem(this) == true && gp.iTile[i].invincible == false) {
			
			gp.iTile[i].playSE();
			gp.iTile[i].life--;
			gp.iTile[i].invincible = true;
			
			//Generate particle
			generateParticle(gp.iTile[i],gp.iTile[i]);
			
			if(gp.iTile[i].life == 0) {
				gp.iTile[i] = gp.iTile[i].getDestroyedForm();
			}
		}
	}
	
	public void checkLevelUp() {
		
		if (exp >= nextLevelExp) {
			
			level++;
			nextLevelExp = nextLevelExp*2;
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
			gp.playSE(14);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "Has Subido de Nivel " + level + ".lvl";
		}
	}
	
	public void selectItem() {
		
		int intemIndex = gp.ui.getItemIndexOnSlot();
		
		if(intemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(intemIndex);
			
			if(selectedItem.type == type_weapon || selectedItem.type == type_WonderWeapon) {
				
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			
			if(selectedItem.type == type_shield ) {
				
				currentShield = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.type == type_consumable ) {
				
				selectedItem.use(this);
				inventory.remove(intemIndex);
			}
		}
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
			break;
		
		}
		
		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		
		
		//Reset Alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
	}
	
}
