package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	boolean checkDrawTime = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {

		
	}

	@Override
	public void keyPressed(KeyEvent e) {

	    int code = e.getKeyCode();

	    if (code == KeyEvent.VK_W) {
	        upPressed = true;
	    }

	    if (code == KeyEvent.VK_S) {
	        downPressed = true;
	    } 

	    if (code == KeyEvent.VK_A) {
	        leftPressed = true;
	    }

	    if (code == KeyEvent.VK_D) {
	        rightPressed = true;
	    }
	    
	    if (code == KeyEvent.VK_P) {
	        if (gp.gameState == gp.playState) {
	            // Si el juego está en estado de reproducción, cambia a pausa
	            gp.gameState = gp.pauseState;
	        } else if (gp.gameState == gp.pauseState) {
	            // Si el juego está en estado de pausa, cambia a reproducción
	            gp.gameState = gp.playState;
	        }
	    }


	    //DEBUG
	    
	    if (code == KeyEvent.VK_T) {
	    	if(checkDrawTime == false) {
	    		checkDrawTime = true;
	    	}
	    	else if(checkDrawTime == true) {
	    		checkDrawTime = false;
	    	}
	    }

	    
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		} 
		
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
	}

}