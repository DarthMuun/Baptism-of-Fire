package tile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Map extends TileManager{
	
	GamePanel gp;
	BufferedImage worldMap[];
	public boolean miniMapOn = false;
	
	public Map(GamePanel gp) {
		
		super(gp);
		this.gp = gp;
		createWorldMap();
		
	}
	public void createWorldMap() {
		
		worldMap = new BufferedImage[gp.maxMap];
		int worldMapWidth = gp.tileSize * gp.maxWorldCol;
		int worldMapHeight = gp.tileSize * gp.maxWorldRow;
		
		for(int i = 0; i < gp.maxMap; i++) {
			
			worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				int tileNum = mapTileNum[i][col][row];
				int x = gp.tileSize * col;
				int y = gp.tileSize * row;
				g2.drawImage(tile[tileNum].image, x, y, null);
				
				col++;
				if(col == gp.maxWorldRow) {
					col = 0;
					row++;
				}
			}
			g2.dispose();
		}
	}
	public void drawFullMap(Graphics2D g2) {
	    // Background
	    g2.setColor(Color.black);
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

	    // Draw Map
	    int width = 500;
	    int height = 500;
	    int x = gp.screenWidth / 2 - width / 2;
	    int y = gp.screenHeight / 2 - height / 2;
	    g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

	    // Draw Player
	    double scale = (double) (gp.tileSize * gp.maxWorldCol) / width;
	    int playerX = (int) (x + gp.player.worldX / scale);
	    int playerY = (int) (y + gp.player.worldY / scale);
	    int playerSize = (int) (gp.tileSize / scale);
	    g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);

	    // Hint
	    g2.setFont(gp.ui.mp16reg.deriveFont(32f));
	    g2.setColor(Color.white);
	    g2.drawString("Press V to Close", 550, 700); // Added coordinates for the text
	}

	public void drawMiniMap(Graphics2D g2) {

	    // Draw Map
	   // int mapWidth = 200;
	   // int mapHeight = 200;
	   // int mapX = 50;
	   // int mapY = 525;
	   // g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
	   // g2.drawImage(worldMap[gp.currentMap], mapX, mapY, mapWidth, mapHeight, null);
	    
	    // Draw Player with Zoom Effect
	   // int playerSize = 15; // Tamaño de la imagen del jugador en el minimapa
	   // int playerX = mapX + (gp.player.worldX * mapWidth) / (gp.tileSize * gp.maxWorldCol) - (playerSize / 2);
	   // int playerY = mapY + (gp.player.worldY * mapHeight) / (gp.tileSize * gp.maxWorldRow) - (playerSize / 2);
	   // g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);
	    
	   // g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
