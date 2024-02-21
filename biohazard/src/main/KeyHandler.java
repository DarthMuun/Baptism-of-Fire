package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	boolean musicPlayed = false;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, healPressed;
	
	//Debug
	boolean checkDrawTime = false;
	
	//Pause Setings
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {

	    int code = e.getKeyCode();
	    
	    //Title State
	    
	    if (code == KeyEvent.VK_UP) {
	        gp.ui.commandNum--;
	        if(gp.ui.commandNum < 0) {
	        	gp.ui.commandNum = 2;	        
	        	}
	    }

	    if (code == KeyEvent.VK_DOWN) {
	    	gp.ui.commandNum++;
	        if(gp.ui.commandNum > 2) {
	        	gp.ui.commandNum = 0;	        
	        	}
	    } 
	    if (code == KeyEvent.VK_ENTER) {
	        if (gp.ui.commandNum == 0 && !musicPlayed) {
	            gp.gameState = gp.playState;
	            gp.playMusic(0);
	            musicPlayed = true;  // Marcar la música como reproducida
	        }
	    	if(gp.ui.commandNum == 1) {
	    		
	    	}
	    	if(gp.ui.commandNum == 2) {
	    		System.exit(0);
	    	}
	    }
	    
	    
	    //Play State
	    if(gp.gameState == gp.playState) {
	    	
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
		    
		    if (code == KeyEvent.VK_ESCAPE) {
		    	
		    	gp.gameState = gp.pauseState;
		        
		    }
		    
		    if (code == KeyEvent.VK_SPACE) {
		    	enterPressed = true;
		    }
		    
		    if (code == KeyEvent.VK_E) {
		    	healPressed = true;
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
	    
	    //Pause State
	    else if(gp.gameState == gp.pauseState) {
		    if(code == KeyEvent.VK_ESCAPE) {
		    	gp.gameState = gp.playState;
		    }
	    }
	    
	    //Dialogue State
	    if(gp.gameState == gp.dialogueState) {
	    	if(code == KeyEvent.VK_SPACE) {
	    		gp.gameState = gp.playState;
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