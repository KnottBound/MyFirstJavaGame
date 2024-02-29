package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_FireRock extends Projectile {

	GamePanel gp;
	
	public OBJ_FireRock(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Fire Rock";
		speed = 8;
		maxLife = 80;
		attack = 2;
		useCost = 1;
		alive = false;
		getImage();
	}

	public void getImage() {
		up1 = setup("/res/projectile/fireattack", gp.tileSize, gp.tileSize);
		up2 = setup("/res/projectile/fireattack", gp.tileSize, gp.tileSize);
		down1 = setup("/res/projectile/fireattack", gp.tileSize, gp.tileSize);
		down2 = setup("/res/projectile/fireattack", gp.tileSize, gp.tileSize);
		left1 = setup("/res/projectile/fireattack", gp.tileSize, gp.tileSize);
		left2 = setup("/res/projectile/fireattack", gp.tileSize, gp.tileSize);
		right1 = setup("/res/projectile/fireattack", gp.tileSize, gp.tileSize);
		right2 = setup("/res/projectile/fireattack", gp.tileSize, gp.tileSize);
	}
	
	public boolean haveResource(Entity user) {
		
		boolean haveResource = false;
		if(user.ammo >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	public void substractResource(Entity user) {
		
		user.ammo -= useCost;
	}
}
