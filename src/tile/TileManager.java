package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[50]; // create 10 types of tiles
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // put all numbers we made for map in mapTileNum
		
		getTileImage();
		loadMap("/res/maps/map01.txt");
	}
	
	public void getTileImage() {
			
			// USES SETUP() METHOD
		
			//Placeholder Tiles: Don't Use
			setup(0, "001", false); // Black Square
			setup(1, "000", false); // Grass Plain
			setup(2, "002", false); // Grass w/ Grass
			setup(3, "003", false); // Full Path
			setup(4, "004", false); // Path Left/Top
			setup(5, "005", false); // Path Top
			setup(6, "006", false); // Path Top/Right
			setup(7, "007", false); // Path Left
			setup(8, "008", false); // Path Right
			setup(9, "009", false); // Path Left/Bottom
			
			
			//Use the below
			setup(10, "000", true); // Black Square
			setup(11, "001", false); // Grass Plain
			setup(12, "002", false); // Grass w/ Grass
			setup(13, "003", false); // Full Path
			setup(14, "004", false); // Path Left/Top
			setup(15, "005", false); // Path Top
			setup(16, "006", false); // Path Top/Right
			setup(17, "007", false); // Path Left
			setup(18, "008", false); // Path Right
			setup(19, "009", false); // Path Left/Bottom
			setup(20, "010", false); // Path Bottom
			setup(21, "011", false); // Path Bottom/Right
			setup(22, "012", false); // Path Corner Bottom/Right
			setup(23, "013", false); // Path Corner Bottom/Left
			setup(24, "014", false); // Path Corner Top/Right
			setup(25, "015", false); // Path Corner Top/Let
			setup(26, "016", true); // Tree
			setup(27, "017", false); // Dirt
			setup(28, "018", true); // Water Square
			setup(29, "019", true); // Water w/ Wave
			setup(30, "020", true); // Water Left/Top
			setup(31, "021", true); // Water Top
			setup(32, "022", true); // Water Top/Right
			setup(33, "023", true); // Water Left
			setup(34, "024", true); // Water Right
			setup(35, "025", true); // Water Left/Bottom
			setup(36, "026", true); // Water Bottom
			setup(37, "027", true); // Water Bottom/Right
			setup(38, "028", true); // Water Corner Bottom/Right
			setup(39, "029", true); // Water Corner Bottom/Left
			setup(40, "030", true); // Water Corner Top/Right
			setup(41, "031", true); // Water Corner Top/Left
			setup(42, "032", true); // Wall
			setup(43, "033", false); // House
			setup(44, "034", false); // Floor
			setup(45, "035", true); // Table
			setup(46, "036", false); // Stairs Down
			setup(47, "037", false); // Stairs Up4
			setup(48, "038", true); // Wall Right
			setup(49, "039", true); // Wall Left
	}
	
	// TAKES THE TILE INFORMATION
	public void setup(int index, String imageName, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) { // This whole thing is created to read the map01.txt
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath); // Use input stream to import text file
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); // format to read text file
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine(); // Read line and put in the String line
				
				while (col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" "); // Get numbers and put in numbers array
					
					int num = Integer.parseInt(numbers[col]); // Changing from string to integer
					
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			
			br.close();
			
		} catch(Exception e) {

		}
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow]; // Everything is stored in this map tile
			
			// Check tiles of world X & Y to get 
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			
			// Where to draw on screen : Basically take worldX & worldY + player location + off-set to be center of screen.
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && // Created that only tiles in boundary are drawn.
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
			worldCol++;

			if(worldCol == gp.maxWorldCol) {
				
				worldCol = 0;
				worldRow++;

			}
		}
	}
}
