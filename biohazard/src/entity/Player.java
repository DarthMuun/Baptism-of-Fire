package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	int standCounter =0;

	public Player(GamePanel gp, KeyHandler keyH) {
				
		super(gp);
		this.keyH = keyH;
		this.gp = gp;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
	
		//Spawn
		worldX = gp.tileSize * 49;
		worldY = gp.tileSize * 77;
		speed = 4;
		direction = "up";
		
		//Status
		maxLife = 8;
		life = maxLife;
		
	}	
	
	public void getPlayerImage() {
		
		
		up1 = setup ("/player/boy_up_1");
		up2 = setup ("/player/boy_up_2");
		down1 = setup ("/player/boy_down_1");
		down2 = setup ("/player/boy_down_2");
		left1 = setup("/player/boy_left_1");
		left2 = setup ("/player/boy_left_2");
		left3 = setup ("/player/boy_left_3");
		right1 = setup("/player/boy_right_1");
		right2 = setup ("/player/boy_right_2");
		right3 = setup ("/player/boy_right_3");
		
	}

	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true || 
				keyH.enterPressed == true || keyH.healPressed == true) {
			
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
		    
		    //Check Event
		    gp.eHandler.checkEvent();
		    
		    //If Collision is false, player can move
		    if(collisionOn == false && keyH.enterPressed == false) {
		    	
		    	switch(direction) {
		    	
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
		    }
		    	
		}
		    
		  gp.keyH.enterPressed = false;
		    
		  spiteCounter++;
		    if (spiteCounter > 12) {
		        spiteCounter = 0; 
		        spriteNum = (spriteNum == 1) ? 2 : 1; 
		    }
			
		} else {			
			standCounter++;			
			if (standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
			
		}
		
		//This needs to be outside of key if statement
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
	}
	
	public void pickUpObject(int i) {
		
		if(i != 999) {
	
		}
		
	}
	
	public void interactNPC(int i) {
		
		if(i != 999) {
			
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		gp.keyH.enterPressed = false;
	}
	
	public void contactEnemies(int i) {
		
		if(i != 999) {
			
			if(invincible == false) {
				life -= 1;
				invincible = true;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			if(spriteNum == 3) {
				image = left3;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			if(spriteNum == 3) {
				image = right3;
			}
			break;
		
		}
		
		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		g2.drawImage(image, screenX, screenY, null);
		
		//Reset Alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		//Debug (Invincible Checker)
		//g2.setFont(new Font("Arial", Font.PLAIN, 26));
		//g2.setColor(Color.white);
		//g2.drawString("Invincible:"+invincibleCounter, 10, 400);
		
	}
	
}
