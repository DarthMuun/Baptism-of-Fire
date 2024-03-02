package main;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import enviroment.EnviromentManager;
import tile.Map;
import tile.TileManager;
import tiles_interactive.InteractiveTile;

public class GamePanel extends JPanel implements Runnable {

	//Screen Settings
	final int originalTileSize = 64; 
	final int scale = 3;
	public int tileSize = originalTileSize;
	public int maxScreenCol = 20;
	public int maxScreenRow = 12;
	public int screenWidth = tileSize * maxScreenCol;
	public int screenHeight = tileSize * maxScreenRow;
	public int screenWidth2 = tileSize * maxScreenCol;
	public int screenHeight2 = tileSize * maxScreenRow;
	
	//World Settings
	public final int maxWorldCol = 98;
	public final int maxWorldRow = 98;
	public final int maxMap = 10;
	public int currentMap = 0;
	
	//Full Screen Settings
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;
	
	//Frame Per Second Setter
	int FPS = 60;
	
	//System
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	public Sound music = new Sound ();
	public Sound se = new Sound ();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Config config = new Config(this);
	public PathFinder pFinder = new PathFinder(this);
	EnviromentManager eManager = new EnviromentManager (this);
	Map map = new Map (this);
	Thread gameThread;
	
		
	//Entity and Objects
	public Player player = new Player(this,keyH);
	public Entity obj[][] = new Entity[maxMap][100];
	public Entity npc[][] = new Entity[maxMap][100];
	public Entity enemies[][] = new Entity[maxMap][100];
	public InteractiveTile iTile[][] = new InteractiveTile [maxMap][50];
	public ArrayList<Entity>projectileList = new ArrayList<>();
	public ArrayList<Entity>particleList = new ArrayList<>();
	ArrayList<Entity>entityList = new ArrayList<>();
	
	//GameState
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	//public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionsState = 5;
	public final int gameOverState = 6;
	public final int transitionState = 7;
	public final int tradeState = 8;
	public final int mapState = 10;
	
    public GamePanel(JFrame window) {
    	
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
    
    public void setupGame() {
 	   
 	   aSetter.setObject();
 	   
 	   aSetter.setNPC();
 	   
 	   aSetter.setEnemies();
 	   
 	   aSetter.setInteractiveTile();
 	   
 	   eManager.setup();
 	   
 	   gameState = titleState;
 	   
 	   tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
 	   g2 = (Graphics2D)tempScreen.getGraphics();
 	   
 	   if(fullScreenOn == true) {
 		  setFullScreen();
 	   }
    }
    
    public void retry() {
    	
    	player.setDefaultPositions();
    	player.restoreLifeAndAmmo();
  	    aSetter.setNPC();
  	    aSetter.setEnemies();
    	
    }
    
    public void restart() {
    	
    	player.setDefaultValues();
    	player.setDefaultPositions();
    	player.restoreLifeAndAmmo();
    	player.selectItem();
  	    aSetter.setObject();
  	    aSetter.setNPC();
  	    aSetter.setEnemies();
  	    aSetter.setInteractiveTile();
    	
    }
    
    public void setFullScreen() {
    	
    	//Get Local Sceen Device
    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	GraphicsDevice gd = ge.getDefaultScreenDevice();
    	gd.setFullScreenWindow(Main.window);
    	
    	screenWidth2 = Main.window.getWidth();
    	screenHeight2 = Main.window.getHeight();
    }
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}

	@Override
	public void run() {
		
		System.out.println("The Program is Running");
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				drawToTempScreen();
				drawToScreen();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("Current FrameRate:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}

	}
	
	public void update() {
	    if (gameState == playState) {
	        // Update Player
	        player.update();
	        
	        // Update NPC
	        for (int i = 0; i < npc[1].length; i++) {
	            if (npc[currentMap][i] != null) {
	                npc[currentMap][i].update();
	            }
	        }
	        
	        // Update Enemy
	        for (int i = 0; i < enemies[1].length; i++) {
	            if (enemies[currentMap][i] != null) {
	                if (enemies[currentMap][i].alive == true && enemies[currentMap][i].dying == false) {
	                    enemies[currentMap][i].update();
	                }
	                if (enemies[currentMap][i].alive == false) {
	                	enemies[currentMap][i].checkDrop();
	                    enemies[currentMap][i] = null;
	                }
	            }
	        }
	        for (int i = 0; i < projectileList.size(); i++) {
	            if (projectileList.get(i) != null) {
	                if (projectileList.get(i).alive == true) {
	                    projectileList.get(i).update();
	                }
	                if (projectileList.get(i).alive == false) {
	                    projectileList.remove(i);
	                }
	            }
	        }
	        for (int i = 0; i < particleList.size(); i++) {
	            if (particleList.get(i) != null) {
	                if (particleList.get(i).alive == true) {
	                	particleList.get(i).update();
	                }
	                if (particleList.get(i).alive == false) {
	                	particleList.remove(i);
	                }
	            }
	        }
	        for(int i = 0; i < iTile[1].length; i ++) {
	        	if(iTile[currentMap][i] != null) {
	        		iTile[currentMap][i].update();
	        	}
	        }

	    }
	    eManager.update();
	    
	    //if (gameState == pauseState) {
	        // Nada que hacer en el estado de pausa
	    //}
	}

	public void drawToTempScreen() {
		
		// Debug
		long drawStart = 0;
		if(keyH.showDebugText == true) {
			drawStart = System.nanoTime();		
			}

		//Title Screen
		if(gameState == titleState) {
			ui.draw(g2);
		}
		//Map Screen
		else if(gameState == mapState) {
			map.drawFullMap(g2);
		}
		
		//OtShers
		else {
			
			//Tile
			tileM.draw(g2);
			
			//Interactive Tile
			for(int i = 0; i < iTile[1].length; i++) {
				if(iTile[currentMap][i] != null) {
					iTile[currentMap][i].draw(g2);
				}
			}
			
			//Add Entities to the List
			entityList.add(player);
			
			for(int i = 0; i < npc[1].length; i++) {
				if(npc[currentMap][i] != null) {
					entityList.add(npc[currentMap][i]);
				}
			}
			for(int i = 0; i < obj[1].length; i++) {
			    if(obj[currentMap][i] != null) {
			        entityList.add(obj[currentMap][i]);
			    }
			}
			for(int i = 0; i < enemies[1].length; i ++){
				if(enemies[currentMap][i] != null) {
					entityList.add(enemies[currentMap][i]);
				}
			}
			for(int i = 0; i < projectileList.size(); i ++){
				if(projectileList.get(i) != null) {
					entityList.add(projectileList.get(i));
				}
			}
			for(int i = 0; i < particleList.size(); i ++){
				if(particleList.get(i) != null) {
					entityList.add(particleList.get(i));
				}
			}
			// Sort
			Collections.sort(entityList, new Comparator<Entity>() {
			    @Override
			    public int compare(Entity e1, Entity e2) {
			        int result = Integer.compare(e1.worldY, e2.worldY);
			        return result;
			    }
			});
			
			//Draw Entities
			for(int i = 0; i < entityList.size(); i ++) {
				entityList.get(i).draw(g2);
			}
			
			//Empty Entity List
			entityList.clear();
			
			//Enviroment
			eManager.draw(g2);
			
			//MiniMap
			map.drawMiniMap(g2);
			
			//UI
			ui.draw(g2);
		}
		
		// Debug
		if(keyH.showDebugText == true) {
		long drawEnd = System.nanoTime();
		long passed = drawEnd - drawStart;
		g2.setColor(Color.white);
		g2.drawString("Tiempo de Dibujo: " + passed, 16, 455);
		g2.drawString("Coordenada X: " + player.worldX, 16, 475);
		g2.drawString("Coordenada Y: " + player.worldY, 16, 495);
		g2.drawString("Columna " + (player.worldX + player.solidAreaDefaultX)/tileSize, 16, 515);
		g2.drawString("Fila " + (player.worldY + player.solidAreaDefaultY)/tileSize, 16, 535);
		System.out.println("Draw Time: " + passed);
		
		}
	}
	
	public void drawToScreen() {
		
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
		
	}
	
	public void stopMusic() {
		
		music.stop();
		
	}
	
	public void playSE(int i) {
		
		se.setFile(i);
		se.play();
		
	}

}