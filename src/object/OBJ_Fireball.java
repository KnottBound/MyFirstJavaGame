package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile {

	GamePanel gp;
	
	public OBJ_Fireball(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Fireball";
		speed = 5;
		maxLife = 80;
		attack = 2;
		useCost = 1;
		alive = false;
		getImage();
	}

	public void getImage() {
		up1 = setup("/res/projectile/fireballU1", gp.tileSize, gp.tileSize);
		up2 = setup("/res/projectile/fireballU2", gp.tileSize, gp.tileSize);
		down1 = setup("/res/projectile/fireballD1", gp.tileSize, gp.tileSize);
		down2 = setup("/res/projectile/fireballD2", gp.tileSize, gp.tileSize);
		left1 = setup("/res/projectile/fireballL1", gp.tileSize, gp.tileSize);
		left2 = setup("/res/projectile/fireballL2", gp.tileSize, gp.tileSize);
		right1 = setup("/res/projectile/fireballR1", gp.tileSize, gp.tileSize);
		right2 = setup("/res/projectile/fireballR2", gp.tileSize, gp.tileSize);
	}
}
