package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;



public class OBJ_Missile extends Projectile{
	
	GamePanel gp;

	public OBJ_Missile(GamePanel gp) {
		
		super(gp);
		this.gp = gp;
		
		name = "Misil";
		speed = 10;
		maxLife = 100;
		life = maxLife;
		attack = 5;
		knockBackPower = 10;
		useCost = 1;
		alive = false;
		getImage();
	}
	
	public void getImage() {
		
		up1 = setup ("/projectile/missileup1",gp.tileSize,gp.tileSize);
		up2 = setup ("/projectile/missileup2",gp.tileSize,gp.tileSize);
		down1 = setup ("/projectile/missiledown1",gp.tileSize,gp.tileSize);
		down2 = setup ("/projectile/missiledown2",gp.tileSize,gp.tileSize);
		left1 = setup ("/projectile/missileleft1",gp.tileSize,gp.tileSize);
		left2 = setup ("/projectile/missileleft2",gp.tileSize,gp.tileSize);
		right1 = setup ("/projectile/missileright1",gp.tileSize,gp.tileSize);
		right2 = setup ("/projectile/missileright2",gp.tileSize,gp.tileSize);
	}
	
	public boolean haveResource(Entity user) {
		
		boolean haveResource = false;
		if(user.ammo >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	public void substractResource (Entity user) {
		user.ammo -= useCost;
	}
	
	public Color getpParticleColor() {
		Color color = new Color (255, 165, 0);
		return color;
	}
	
	public int getParticleSize() {
		int size = 10;
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
