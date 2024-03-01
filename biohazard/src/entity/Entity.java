package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Entity {

	GamePanel gp;	
	
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public BufferedImage image, image2, image3;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	String dialogues[] = new String[20];
	
	//State
	public int worldX, worldY;
	public String direction = "down";
	public int spriteNum = 1;
	int dialogueIndex = 0;
	public boolean collision = false;
	public boolean invincible = false;
	boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	boolean hpBarOn = false;
	public boolean onPath = false;
	public boolean knockBack = false;

	//Counter
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int shotAvailableCounter = 0;
	int dyingCounter = 0;
	int hpBarCounter = 0;
	int knockBackCounter = 0;
	
	//Character Status
	public String name;
	public int defaultSpeed;
	public int speed;
	public int maxLife;
	public int life;
	public int maxAmmo;
	public int ammo;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int parts;
	public Entity currentWeapon;
	public Entity currentShield;
	public Projectile projectile;
	
	//Item Attributes
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 30;
	public int value;
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int useCost;
	public int price;
	public int knockBackPower = 0;
	
	//Type
	public int type; // 0 = player, 1 = npc, 2 = monster
	public final int type_player = 0; 
	public final int type_npc = 1;  
	public final int type_enemy = 2; 
	public final int type_weapon = 3;  
	public final int type_shield = 4;  
	public final int type_WonderWeapon = 5; 
	public final int type_consumable = 6;  
	public final int type_pickupOnly = 7;
	public final int type_obstacle = 8;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public int getLeftX() {
		return worldX + solidArea.x;
	}
	
	public int getRightX() {
		return worldX + solidArea.width;
	}
	public int getTopY() {
		return worldY + solidArea.y;
	}
	
	public int getBottomY() {
		return worldY + solidArea.y + solidArea.height;
	}
	
	public int getCol() {
		return (worldX + solidArea.x)/gp.tileSize;
	}
	
	public int getRow() {
		return (worldY + solidArea.y)/gp.tileSize;
	}
	
	public void setAction() {}
	public void damageReaction() {}
	public void speak() {
		
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
	    switch(gp.player.direction) {
	        case "up":
	            direction = "down";
	            break;
	        case "down":
	            direction = "up";
	            break;
	        case "left":
	            direction = "right";
	            break;
	        case "right":
	            direction = "left";
	            break;
	    }
	}
	
	public void interact() {
		
	}
	
	public boolean use (Entity entity) {return false;}
	
	public void checkDrop() {}
	
	public void dropItem(Entity droppedItem) {
		
		for(int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i] == null) {
				gp.obj[gp.currentMap][i] = droppedItem;
				gp.obj[gp.currentMap][i].worldX = worldX;
				gp.obj[gp.currentMap][i].worldY = worldY;
				break;
			}
		}
	}
	
	public Color getpParticleColor() {
		Color color = null;
		return color;
	}
	
	public int getParticleSize() {
		int size = 0;
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 0;
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 0;
		return maxLife;
	}
	
	public void generateParticle(Entity generator, Entity target) {
		
		Color color = generator.getpParticleColor();
		int size = generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getParticleMaxLife();
		
		Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -1, -1);
		Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 1, -1);
		Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -1, 1);
		Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 1, 1);
		Particle p5 = new Particle(gp, target, color, size, speed, maxLife, -4, -2);
		Particle p6 = new Particle(gp, target, color, size, speed, maxLife, 4, -2);
		Particle p7 = new Particle(gp, target, color, size, speed, maxLife, -4, 2);
		Particle p8 = new Particle(gp, target, color, size, speed, maxLife, 4, 2);
		gp.particleList.add(p1);
		gp.particleList.add(p2);
		gp.particleList.add(p3);
		gp.particleList.add(p4);
		gp.particleList.add(p5);
		gp.particleList.add(p6);
		gp.particleList.add(p7);
		gp.particleList.add(p8);
	}
	
	public void checkCollision(){
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.enemies);
		gp.cChecker.checkEntity(this, gp.iTile);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == type_enemy && contactPlayer == true) {
			damagePlayer(attack);
		}
	}
	
	public void update() {
		
		if (knockBack) {
			checkCollision();
			
			if (collisionOn) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			} else {
				switch(gp.player.direction) {
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			}
			
			knockBackCounter++;
			if (knockBackCounter == 5) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
		} else {
			setAction();
			checkCollision();
			
			if (!collisionOn) {
				switch(direction) {
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				} 	
			}
		}
	    
		spriteCounter++;
		if (spriteCounter > 24) {
			spriteCounter = 0; 
		    spriteNum = (spriteNum == 1) ? 2 : 1; 
		}
	    
		if (invincible) {
			invincibleCounter++;
			if (invincibleCounter > 30) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if (shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
	}

	
	public void damagePlayer(int attack){
		
		if(gp.player.invincible == false) {
			//We can give damange
			gp.playSE(6);
			
			int damage = attack - gp.player.defense;
			if(damage < 0) {
				damage = 0;
			}
			life -= damage;

			gp.player.invincible = true;
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
				if(spriteNum == 1) { image = up1;}
				if(spriteNum == 2) {image = up2;}
				if(spriteNum == 3) {image = up3;}
				break;
			case "down":
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				if(spriteNum == 3) {image = down3;}
				break;
			case "left":
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				break;
			case "right":
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				break;
			}
			
			//Enemy HP Bar
			if(type == 2 && hpBarOn == true) {
				
				double oneScale = (double)gp.tileSize/maxLife;
				double hpBarValue = oneScale*life;
				
				g2.setColor(new Color(0, 0, 0));
				g2.fillRect(screenX-1, screenY - 16, gp.tileSize+2, 12);
				
				g2.setColor(new Color(255,0,30));
				g2.fillRect(screenX, screenY - 15,(int)hpBarValue, 10);
				
				hpBarCounter++;
				
				if(hpBarCounter > 600) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}
			if(invincible == true) {
				hpBarOn = true;
				changeAlpha(g2,0.4F);
			}
			if(dying == true) {
				dyingAnimation(g2);
			}
			g2.drawImage(image, screenX, screenY, null);
			
			changeAlpha(g2,1F);
			
		}
	}
	
	public void dyingAnimation(Graphics2D g2) {
	    dyingCounter++;

	    int i = 5;

	    if (dyingCounter <= i) {
	        changeAlpha(g2, 0f);
	    } else if (dyingCounter <= i * 8) {
	        int alphaChange = (dyingCounter / i) % 2;
	        changeAlpha(g2, alphaChange);
	    } else {
	        dying = false;
	        alive = false;
	    }
	}

	public void changeAlpha(Graphics2D g2, float alphaValue) {
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	public BufferedImage setup(String imagePath, int width, int height) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
			image = uTool.scaleImage(image, width, height);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
		
	}
	
	public void searchPath(int goalCol, int goalRow) {
		
		int startCol = (worldX + solidArea.x)/gp.tileSize;
		int startRow = (worldX + solidArea.y)/gp.tileSize;
		
		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
		
		if(gp.pFinder.search() == true) {
			
			int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).col * gp.tileSize;
			
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;
			
			if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "up";
			}
			else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "down";
			}
			else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
				
				if(enLeftX > nextX) {
					direction = "left";
				}
				if(enLeftX < nextX) {
					direction = "right";
				}
			}
			else if(enTopY > nextY && enLeftX > nextX) {
				
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY < nextY && enLeftX < nextX) {
				
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			else if(enTopY < nextY && enLeftX < nextX) {
				
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			
			int nextCol = gp.pFinder.pathList.get(0).col;
			int nextRow = gp.pFinder.pathList.get(0).row;
			if(nextCol == goalCol && nextRow == goalRow) {
				onPath = false;
			}
		}
	}
	
	public int getDetected(Entity user, Entity target[][], String targetName) {
		
		int index = 999;
		
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();
		
		switch(user.direction) {
		case "up": nextWorldY = user.getTopY()-1; break;
		case "down": nextWorldY = user.getBottomY()+1; break;
		case "left": nextWorldX = user.getLeftX()-1; break;
		case "right": nextWorldX = user.getRightX()+1; break;
		}
		int col = nextWorldX/gp.tileSize;
		int row = nextWorldY/gp.tileSize;
		
		for(int i = 0; i < target[1].length; i++) {
			if(target[gp.currentMap][i] != null) {
				if(target[gp.currentMap][i].getCol() == col &&
						target[gp.currentMap][i].getRow() == row &&
						target[gp.currentMap][i].name.equals(targetName)){
							
					index = i;
					break;
					
				}
			}
		}
		return index;
	}
	
	
}