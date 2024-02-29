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

import entity.Entity;
import entity.Player;
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
	
	//Full Screen Settings
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;
	
	//Frame Per Second Setter
	int FPS = 60;
	
	//System
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	public Sound music = new Sound ();
	public Sound se = new Sound ();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Config config = new Config(this);
	Thread gameThread;
		
	//Entity and Objects
	public Player player = new Player(this,keyH);
	public Entity obj[] = new Entity[100];
	public Entity npc[] = new Entity[100];
	public Entity enemies[] = new Entity[100];
	public InteractiveTile iTile[] = new InteractiveTile [50];
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
	        // Actualizar jugador
	        player.update();
	        
	        // Actualizar NPC
	        for (int i = 0; i < npc.length; i++) {
	            if (npc[i] != null) {
	                npc[i].update();
	            }
	        }
	        
	        // Actualizar enemigos
	        for (int i = 0; i < enemies.length; i++) {
	            if (enemies[i] != null) {
	                if (enemies[i].alive == true && enemies[i].dying == false) {
	                    enemies[i].update();
	                }
	                if (enemies[i].alive == false) {
	                	enemies[i].checkDrop();
	                    enemies[i] = null;
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
	        for(int i = 0; i < iTile.length; i ++) {
	        	if(iTile[i] != null) {
	        		iTile[i].update();
	        	}
	        }

	    }
	    
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
		//Otthers
		else {
			
			//Tile
			tileM.draw(g2);
			
			//Interactive Tile
			for(int i = 0; i < iTile.length; i++) {
				if(iTile[i] != null) {
					iTile[i].draw(g2);
				}
			}
			
			//Add Entities to the List
			entityList.add(player);
			
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			for(int i = 0; i < obj.length; i++) {
			    if(obj[i] != null) {
			        entityList.add(obj[i]);
			    }
			}
			for(int i = 0; i < enemies.length; i ++){
				if(enemies[i] != null) {
					entityList.add(enemies[i]);
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