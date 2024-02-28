package main;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	//Screen Settings
	final int originalTileSize = 64; 
	final int scale = 3;
	public int tileSize = originalTileSize;
	public int maxScreenCol = 20;
	public int maxScreenRow = 12;
	public int screenWidth = tileSize * maxScreenCol;
	public int screenHeight = tileSize * maxScreenRow;
	
	//World Settings
	public final int maxWorldCol = 98;
	public final int maxWorldRow = 98;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
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
	Thread gameThread;
		
	//Entity and Objects
	public Player player = new Player(this,keyH);
	public Entity obj[] = new Entity[100];
	public Entity npc[] = new Entity[100];
	public Entity enemies[] = new Entity[100];
	public ArrayList<Entity>projectileList = new ArrayList<>();
	ArrayList<Entity>entityList = new ArrayList<>();
	
	//GameState
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	
	
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
 	   
 	   gameState = titleState;
 	   
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
			
			if(delta >=1) {
				update();
				repaint();
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

	    }
	    
	    if (gameState == pauseState) {
	        // Nada que hacer en el estado de pausa
	    }
	}

	public void paintComponent(Graphics g) {		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		
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
		
		g2.dispose();
		
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