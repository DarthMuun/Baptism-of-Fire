package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	boolean musicPlayed = false;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, interactPressed, shotKeyPressed, guardPressed;
	
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
	    //Trade State
    	else if (gp.gameState == gp.tradeState) {
    		tradeState(code);
    	}
	    //Map State
    	else if (gp.gameState == gp.mapState) {
    		mapState(code);
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
	    	if(gp.ui.commandNum == 1 && !musicPlayed) {
	    		gp.saveLoad.load();
	            gp.gameState = gp.playState;
	            gp.playMusic(0);
	            musicPlayed = true;
	    	}
	    	if(gp.ui.commandNum == 2) {
	    		System.exit(0);
	    	}
        	//if(gp.map.miniMapOn == false) {
        	//	gp.map.miniMapOn = true;
        	//}
        	//else {
        	//	gp.map.miniMapOn = false;
        	//}
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
        if (code == KeyEvent.VK_V) {
        	gp.gameState = gp.mapState;
        }
        if (code == KeyEvent.VK_Q) {
        	guardPressed = true;
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
	    	
	    	switch(gp.currentMap) {
	    	case 0: gp.tileM.loadMap("/maps/demo.txt",0); break;
	    	case 1: gp.tileM.loadMap("/maps/marcaderdemo.txt",0); break;
	    	}
	    }
		
	}
	public void pauseState (int code) {
		
	    if(code == KeyEvent.VK_ESCAPE) {
	    	gp.gameState = gp.playState;
	    }
	}
	public void dialogueState (int code) {
		
    	if(code == KeyEvent.VK_SPACE) {
    		interactPressed = true;
    	}
	}
	public void characterState (int code) {
		
    	if(code == KeyEvent.VK_C) {
    		gp.gameState = gp.playState;
    		gp.playSE(9);
    	}
    	if(code == KeyEvent.VK_ENTER) {
    		gp.playSE(17);
    		gp.player.selectItem();
    	}
    	playerInventory(code);
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
	    		gp.resetGame(false);
	    		gp.playMusic(0);
	    	}
	    	if(gp.ui.commandNum == 1) {
	    		gp.gameState = gp.titleState;
	    		gp.playSE(11);
	    		gp.resetGame(true);
	    	}
	    }
	}
	public void tradeState (int code) {
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
			gp.playSE(11);
		}
		if(gp.ui.subState ==0) {
			if(code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
				gp.playSE(11);
			}
			if(code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
				gp.playSE(11);
			}
		}
		if(gp.ui.subState == 1) {
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
		if(gp.ui.subState == 2) {
			playerInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
	}
	
	public void mapState(int code) {
		
		if(code == KeyEvent.VK_V) {
			gp.gameState = gp.playState;
		}
		
	}
	public void playerInventory(int code) {
		
    	if(code == KeyEvent.VK_UP) {
    		if(gp.ui.playerSlotRow != 0) {
        		gp.ui.playerSlotRow--;
        		gp.playSE(8);
    		}
    	}
    	if(code == KeyEvent.VK_DOWN) {
    		if(gp.ui.playerSlotRow != 4) {
        		gp.ui.playerSlotRow++;
        		gp.playSE(8);
    		}
    	}
    	if(code == KeyEvent.VK_LEFT) {
    		if(gp.ui.playerSlotCol != 0) {
        		gp.ui.playerSlotCol--;
        		gp.playSE(8);
    		}
    	}
    	if(code == KeyEvent.VK_RIGHT) {
    		if(gp.ui.playerSlotCol != 8) {
        		gp.ui.playerSlotCol++;
        		gp.playSE(8);
    		}
    	}
	}
	public void npcInventory(int code) {
		
    	if(code == KeyEvent.VK_UP) {
    		if(gp.ui.npcSlotRow != 0) {
        		gp.ui.npcSlotRow--;
        		gp.playSE(8);
    		}
    	}
    	if(code == KeyEvent.VK_DOWN) {
    		if(gp.ui.npcSlotRow != 4) {
        		gp.ui.npcSlotRow++;
        		gp.playSE(8);
    		}
    	}
    	if(code == KeyEvent.VK_LEFT) {
    		if(gp.ui.npcSlotCol != 0) {
        		gp.ui.npcSlotCol--;
        		gp.playSE(8);
    		}
    	}
    	if(code == KeyEvent.VK_RIGHT) {
    		if(gp.ui.npcSlotCol != 8) {
        		gp.ui.npcSlotCol++;
        		gp.playSE(8);
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
		if (code == KeyEvent.VK_Q) {
			guardPressed = false;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
		if (code == KeyEvent.VK_SPACE) {
			interactPressed = false;
		}
	}

}