package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	int standCounter =0;

	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 18;
		solidArea.y = 18;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 49;
		worldY = gp.tileSize * 77;
		speed = 4;
		direction = "down";
		
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
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			
		    if (keyH.upPressed) {
		        direction = "up";
		    } else if (keyH.downPressed) {
		        direction = "down";
		    } else if (keyH.leftPressed) {
		        direction = "left";
		    } else if (keyH.rightPressed) {
		        direction = "right";
		    }
		    
		    //Collision Tile

		    collisionOn = false;
		    gp.cChecker.checkTile(this);
		    
		    //Check Tile Colission
		    
		    int objIndex = gp.cChecker.checkObject(this, true);
		    pickUpObject(objIndex);
		    
		    //If Collision is false, player can move
		    
		    if(collisionOn == false) {
		    	
		    	switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
		    }
		    	
		    }
		    
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
		
	}
	
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			
			
			
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
		g2.drawImage(image, screenX, screenY, null);
		
	}
	
}
