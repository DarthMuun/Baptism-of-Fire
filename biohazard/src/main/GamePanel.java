package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable, MouseWheelListener {

    private JFrame window;
    private double zoomLevel = 1.0; 

//Screen Settings
	
	final int originalTileSize = 64; 
	final int scale = 3;
	public int tileSize = originalTileSize;
	public int maxScreenCol = 14;
	public int maxScreenRow = 10;
	public int screenWidth = tileSize * maxScreenCol;
	public int screenHeight = tileSize * maxScreenRow;
	
	//World Settings
	
	public final int maxWorldCol = 99;
	public final int maxWorldRow = 99;
	
	//FPS
	
	int FPS = 60;
	
	//System
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound ();
	Sound se = new Sound ();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread;
	
	//Entity and Objects
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[99];
	public Entity npc[] = new Entity[4];
	
	//GameState
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	
	
    public GamePanel(JFrame window) {
        this.window = window;
        
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		this.addMouseWheelListener(this);
		
	}
    
   public void setupGame() {
	   
	   aSetter.setObject();
	   
	   aSetter.setNPC();
	   
	   playMusic(0);
	   
	   
	   gameState = playState;
	   
   }
	
    public void zoomInOut(MouseWheelEvent e) {
        int notches = e.getWheelRotation();

        if (notches < 0) {
            // Scroll Up (Zoom In)
            double newZoomLevel = zoomLevel + 0.1;

            // Limitar el zoom in al 200%
            if (newZoomLevel <= 2.0) {
                zoomLevel = newZoomLevel;
                int oldTileSize = tileSize;
                tileSize = (int) (originalTileSize * zoomLevel);

                // Ajuste de la posición del jugador para compensar el cambio en el tamaño del mosaico
                player.worldX = (int) (player.worldX / (double) oldTileSize * tileSize);
                player.worldY = (int) (player.worldY / (double) oldTileSize * tileSize);

                // Asegúrate de volver a empaquetar y actualizar la ventana después de cambiar el tamaño del tile
                this.setPreferredSize(new Dimension(screenWidth, screenHeight));
                this.setSize(screenWidth, screenHeight);
                this.window.pack();
                this.repaint();
            }
        } else {
            // Scroll Down (Zoom Out)
            double newZoomLevel = zoomLevel - 0.1;

            // Limitar el zoom out al 10%
            if (newZoomLevel >= 1) {
                zoomLevel = newZoomLevel;
                int oldTileSize = tileSize;
                tileSize = (int) (originalTileSize * zoomLevel);

                // Ajuste de la posición del jugador para compensar el cambio en el tamaño del mosaico
                player.worldX = (int) (player.worldX / (double) oldTileSize * tileSize);
                player.worldY = (int) (player.worldY / (double) oldTileSize * tileSize);

                // Asegúrate de volver a empaquetar y actualizar la ventana después de cambiar el tamaño del tile
                this.setPreferredSize(new Dimension(screenWidth, screenHeight));
                this.setSize(screenWidth, screenHeight);
                this.window.pack();
                this.repaint();
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoomInOut(e);
    }
    
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			update();
			
			repaint();
			
			try {
				
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;				
				}
				
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void update() {
		
		if(gameState == playState) {
			player.update();
		}
		if(gameState == pauseState) {
			//nothing
		}
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		
		// Debug
		long drawStart = 0;
		if (keyH.checkDrawTime) {
		    drawStart = System.nanoTime();
		}
		
		//Tile
		tileM.draw(g2);
				
		//Object
		for(int i = 0; i < obj.length; i++) {
			
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
			
		}
		//NPC
		for(int i = 0; i < npc.length; i ++) {
			if (npc[i] != null) {
				npc[i].draw(g2);
			}
		}
		
		//Player
		player.draw(g2);
		
		//UI
		ui.draw(g2);
			
		g2.dispose();
		// Debug
		if (keyH.checkDrawTime) {
		    long drawEnd = System.nanoTime();
		    long passed = drawEnd - drawStart;
		    g2.setColor(Color.white);
		    g2.drawString("Tiempo de Dibujo: " + passed, 10, 400);
		    System.out.println("Tiempo de Dibujo: " + passed);
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