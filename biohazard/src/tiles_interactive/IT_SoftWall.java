package tiles_interactive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class IT_SoftWall extends InteractiveTile{
	
	GamePanel gp;
	
	public IT_SoftWall(GamePanel gp, int col, int row){
		super(gp,col,row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		life = 3;
		
		down1 = setup("/tiles_interactive/piso",gp.tileSize,gp.tileSize);
		destructible = true;
	}
	public boolean isCorrectItem(Entity entity) {
		
		boolean isCorrectItem = false;
		if(entity.currentWeapon.type == type_WonderWeapon) {
			isCorrectItem = true;
		}
		return isCorrectItem;
	}
	
	public void playSE() {
		gp.playSE(13);
	}
	
	public InteractiveTile getDestroyedForm() {
		
		InteractiveTile tile = new IT_BrokenWall(gp, worldX/gp.tileSize, worldY/gp.tileSize);
		return tile;
	}

	public Color getpParticleColor() {
		Color color = new Color (35, 35, 35);
		return color;
	}
	
	public int getParticleSize() {
		int size = 6;
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
}
