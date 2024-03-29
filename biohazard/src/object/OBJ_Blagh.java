package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Blagh extends Projectile {
	
	public static final String objName = "Blagh";
	
	GamePanel gp;

	public OBJ_Blagh(GamePanel gp) {
		
		super(gp);
		this.gp = gp;
		
		name = objName;
		speed = 10;
		maxLife = 100;
		life = maxLife;
		attack = 10;
		useCost = 1;
		alive = false;
		getImage();
	}
	
	public void getImage() {
		
		up1 = setup ("/projectile/blagh",gp.tileSize,gp.tileSize);
		up2 = setup ("/projectile/blagh",gp.tileSize,gp.tileSize);
		down1 = setup ("/projectile/blagh",gp.tileSize,gp.tileSize);
		down2 = setup ("/projectile/blagh",gp.tileSize,gp.tileSize);
		left1 = setup ("/projectile/blagh",gp.tileSize,gp.tileSize);
		left2 = setup ("/projectile/blagh",gp.tileSize,gp.tileSize);
		right1 = setup ("/projectile/blagh",gp.tileSize,gp.tileSize);
		right2 = setup ("/projectile/blagh",gp.tileSize,gp.tileSize);
	}
}
