package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Helmet extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_Helmet(GamePanel gp) {
		name = "Helmet";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/res/objects/helmet.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
