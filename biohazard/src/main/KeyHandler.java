package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	boolean musicPlayed = false;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, interactPressed, shotKeyPressed;
	
	//Debug
	boolean showDebugText = false;
	
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
	    if(gp.gameState == gp.titleState) {
	    	titleState(code);
	    }
	    //Play State
	    else if(gp.gameState == gp.playState) {
	    	playState(code);
	    }
	    //Pause State
	    //else if(gp.gameState == gp.pauseState) {
	    //	pauseState(code);
	    //}
	    //Dialogue State
	    else if(gp.gameState == gp.dialogueState) {
	    	dialogueState(code);
	    }
	    //Character State
    	else if (gp.gameState == gp.characterState) {
    		characterState(code);
    	}
	    //Options State
    	else if (gp.gameState == gp.optionsState) {
    		optionsState(code);
    	}
	    //Death State
    	else if (gp.gameState == gp.gameOverState) {
    		gameOverState(code);
    	}
	    
	}
	
	public void titleState (int code) {
		
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
	}
	public void playState (int code) {
		
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
	    	gp.gameState = gp.optionsState;
	    	gp.playSE(9);
	    }
	    if (code == KeyEvent.VK_C) {
	    	gp.gameState = gp.characterState;
	    	gp.playSE(9);
	    }
	    if (code == KeyEvent.VK_ENTER) {
	    	enterPressed = true;
	    }
        if (code == KeyEvent.VK_SPACE) {
        	interactPressed = true;
        }
        if (code == KeyEvent.VK_E) {
        	shotKeyPressed = true;
        }
	    

	    //DEBUG
	    
	    if (code == KeyEvent.VK_T) {
	    	if(showDebugText == false) {
	    		showDebugText = true;
	    	}
	    	else if(showDebugText == true) {
	    		showDebugText = false;
	    	}
	    }
	    if(code == KeyEvent.VK_Y) {
	    	gp.tileM.loadMap("/maps/demo.txt");
	    }
		
	}
	public void pauseState (int code) {
		
	    if(code == KeyEvent.VK_ESCAPE) {
	    	gp.gameState = gp.playState;
	    }
	}
	public void dialogueState (int code) {
		
    	if(code == KeyEvent.VK_SPACE) {
    		gp.gameState = gp.playState;
    	}
	}
	public void characterState (int code) {
		
    	if(code == KeyEvent.VK_C) {
    		gp.gameState = gp.playState;
    		gp.playSE(9);
    	}
    	if(code == KeyEvent.VK_UP) {
    		if(gp.ui.slotRow != 0) {
        		gp.ui.slotRow--;
        		gp.playSE(8);
    		}
    	}
    	if(code == KeyEvent.VK_DOWN) {
    		if(gp.ui.slotRow != 4) {
        		gp.ui.slotRow++;
        		gp.playSE(8);
    		}
    	}
    	if(code == KeyEvent.VK_LEFT) {
    		if(gp.ui.slotCol != 0) {
        		gp.ui.slotCol--;
        		gp.playSE(8);
    		}
    	}
    	if(code == KeyEvent.VK_RIGHT) {
    		if(gp.ui.slotCol != 8) {
        		gp.ui.slotCol++;
        		gp.playSE(8);
    		}
    	}
    	if(code == KeyEvent.VK_ENTER) {
    		gp.playSE(17);
    		gp.player.selectItem();
    	}
	}
	public void optionsState(int code) {
	    
	    if (code == KeyEvent.VK_ESCAPE) {
	        gp.gameState = gp.playState;
	        gp.playSE(11);
	    }
	    if (code == KeyEvent.VK_ENTER) {
	        enterPressed = true;
	        gp.playSE(11);
	    }

	    int maxCommandNum = 0;
	    switch(gp.ui.subState) {
	        case 0: 
	            maxCommandNum = 5;
	            break; 
	        case 3: 
	            maxCommandNum = 1;
	            break; 
	    }

	    if (code == KeyEvent.VK_UP) {
	        gp.ui.commandNum--;
	        gp.playSE(11);
	        if (gp.ui.commandNum < 0) {
	            gp.ui.commandNum = maxCommandNum;
	        }
	    }
	    if (code == KeyEvent.VK_DOWN) {
	        gp.ui.commandNum++;
	        gp.playSE(11);
	        if (gp.ui.commandNum > maxCommandNum) {
	            gp.ui.commandNum = 0;
	        }
	    }
	    if (code == KeyEvent.VK_LEFT && gp.ui.subState == 0) {
	        if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
	            gp.music.volumeScale--;
	            gp.music.checkVolume();
	            gp.playSE(11);
	        } else if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
	            gp.se.volumeScale--;
	            gp.playSE(11);
	        }
	    }

	    if (code == KeyEvent.VK_RIGHT && gp.ui.subState == 0) {
	        if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
	            gp.music.volumeScale++;
	            gp.music.checkVolume();
	            gp.playSE(11);
	        } else if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
	            gp.se.volumeScale++;
	            gp.playSE(11);
	        }
	    }
	}
	public void gameOverState(int code) {
	    if (code == KeyEvent.VK_UP) {
	        gp.ui.commandNum--;
	        if(gp.ui.commandNum < 0) {
	        	gp.ui.commandNum = 1;	        
	        	}
	        gp.playSE(11);
	    }
	    if (code == KeyEvent.VK_DOWN) {
	        gp.ui.commandNum++;
	        if(gp.ui.commandNum > 1) {
	        	gp.ui.commandNum = 0;	        
	        	}
	        gp.playSE(11);
	    }
	    if(code == KeyEvent.VK_ENTER) {
	    	if(gp.ui.commandNum == 0) {
	    		gp.gameState = gp.playState;
	    		gp.playSE(16);
	    		gp.retry();
	    	}
	    	if(gp.ui.commandNum == 1) {
	    		gp.gameState = gp.titleState;
	    		gp.playSE(11);
	    		gp.restart();
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
		
		if (code == KeyEvent.VK_E) {
			shotKeyPressed = false;
		}
		
	}

}