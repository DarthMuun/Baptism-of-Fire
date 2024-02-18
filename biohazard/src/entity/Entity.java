package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	
	GamePanel gp;	
	public int worldX, worldY;
	public int speed;
	public BufferedImage up1, up2, down1, down2, left1, left2, left3, right1, right2, right3;
	public String direction;
	public int spiteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
		
	}
	
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
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
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
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

			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			
		}
	}
	
	public BufferedImage setup(String imagePath) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
		
	}
	
}