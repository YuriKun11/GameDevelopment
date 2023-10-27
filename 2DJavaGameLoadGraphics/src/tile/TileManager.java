package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	Tile[] tile;
	
	int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[13];
		
		mapTileNum = new int [gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_top_1.png"));
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_top_2.png"));
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_top_3.png"));
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_mid_1.png"));
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_mid_2.png"));
			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_mid_3.png"));
			
			tile[10] = new Tile();
			tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_down_1.png"));
			tile[11] = new Tile();
			tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_down_2.png"));
			tile[12] = new Tile();
			tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/house_down_3.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filepath) {
		try{
			InputStream is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxScreenCol) {
				String line = br.readLine();
				while(col < gp.maxScreenCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
					
				}
				
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
			
			}br.close();
			
		}catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0; 
				x = 0;
				row++;
				y += gp.tileSize;
				
			}
		}
		
		
		
	
		
	}

}
