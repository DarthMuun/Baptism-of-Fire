package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import object.OBJ_Ammo;
import object.OBJ_Heart;
import object.OBJ_Parts;
import entity.Entity;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    public Font mp16reg;
    BufferedImage heart_full, heart_half, heart_blank, full, empty, part;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;// 0 = 1st Screen, 1 = 2nd Screen
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int subState = 0;
    int counter = 0;
    public Entity npc;

    public UI(GamePanel gp) {
        this.gp = gp;
        
        try {
        	InputStream is = getClass().getResourceAsStream("/font/MP16REG.ttf");
        	mp16reg = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch (FontFormatException e) {
        	e.printStackTrace();        	
        }catch (IOException e) {
        	e.printStackTrace();
        }
        
        //Objects
        
        //Life
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity ammo = new OBJ_Ammo(gp);
        full = ammo.image;
        empty = ammo.image2;
        Entity parts = new OBJ_Parts(gp);
        part = parts.down1;
        
        //Times
    }

    public void addMessage(String text) {
    	
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2) {
    	
    	this.g2 = g2;
    	
        g2.setFont(mp16reg);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
        
        //Title State
        if(gp.gameState == gp.titleState) {
        	drawTitleScreen();
        }

        // Play State
        if (gp.gameState == gp.playState) {
        	drawPlayerLife();
        	drawMessage();

        }
        // Dialogue State
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        //Character State
        if(gp.gameState == gp.characterState) {
        	drawCharacterScreen();
        	drawInventory(gp.player,true);
        }
        //Options State
        if(gp.gameState == gp.optionsState) {
        	drawOptionsScreen();
        }
        //Death State
        if(gp.gameState == gp.gameOverState) {
        	drawGameOverScreen();
        }
        //Transition State
        if(gp.gameState == gp.transitionState) {
        	drawTransition();
        }
        //Trade State
        if(gp.gameState == gp.tradeState) {
        	drawtTradeScreen();
        }
    }
    
    public void drawPlayerLife() {
    	
    	
    	int x = gp.tileSize/2;
    	int y = gp.tileSize/2;
    	int i = 0;
    	
    	//Blank Heart
    	while(i < gp.player.maxLife/2) {
    		g2.drawImage(heart_blank, x, y, null);
    		i++;
    		x += gp.tileSize;
    	}
    	
    	//Reset
    	x = gp.tileSize/2;
    	y = gp.tileSize/2;
    	i = 0;
    	
    	//Current Life
    	while(i < gp.player.life) {
    		g2.drawImage(heart_half, x, y, null);
    		i ++;
    		if(i < gp.player.life) {
    			g2.drawImage(heart_full, x, y, null);
    		}
    		i++;
    		x += gp.tileSize;
    	}
    	
    	//Draw Max Ammo
    	x = gp.tileSize*17;
    	y = gp.tileSize/2;
    	i = 0;
    	
    	while(i < gp.player.maxAmmo) {
    		g2.drawImage(empty, x, y, null);
    		i++;
    		x += 35;
    	}
    	
    	//Draw Ammo
    	x = gp.tileSize*17;
    	y = gp.tileSize/2;
    	i = 0;
    	
    	while(i < gp.player.ammo) {
    		g2.drawImage(full, x, y, null);
    		i++;
    		x += 35;
    	}

    	
    }
    
    public void drawMessage() {
    	
    	int messageX = gp.tileSize;
    	int messageY = gp.tileSize*5;
    	g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
    	
    	for(int i = 0; i < message.size(); i ++) {
    		
    		if(message.get(i) != null) {
    			
    			g2.setColor(Color.white);
    			g2.drawString(message.get(i), messageX, messageY);
    			
    			int counter = messageCounter.get(i) + 1; //messageCounter++
    			messageCounter.set(i, counter); //Set the Counter to the array
    			messageY += 50;
    			
    			if(messageCounter.get(i) > 180) {
    				message.remove(i);
    				messageCounter.remove(i);
    			}
    		}
    	}
    }
    
    public void drawTitleScreen() {
    	
    	if(titleScreenState == 0) {
    		
    		//Background
        	g2.setColor(new Color(0, 0, 0));
        	g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        	
        	//Title Name
        	g2.setFont(g2.getFont().deriveFont(Font.BOLD,150F));
        	String text = "Jaeger";
        	int x = getXforCenteredText(text);
        	int y = gp.tileSize*4;    	
        	
        	//Main Color
        	g2.setColor(Color.white);
        	g2.drawString(text, x, y);
        	
        	//Menu
        	g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        	
        	text = "Nuevo Juego";
        	x = getXforCenteredText(text);
        	y += gp.tileSize*2;
        	g2.drawString(text, x, y);
        	if(commandNum == 0) {
        		g2.drawString("-", x-gp.tileSize, y);
        	}
        	
        	text = "Cargar Juego";
        	x = getXforCenteredText(text);
        	y += gp.tileSize;
        	g2.drawString(text, x, y);
        	if(commandNum == 1) {
        		g2.drawString("-", x-gp.tileSize, y);
        	}
        	
        	text = "Salir";
        	x = getXforCenteredText(text);
        	y += gp.tileSize;
        	g2.drawString(text, x, y);
        	if(commandNum == 2) {
        		g2.drawString("-", x-gp.tileSize, y);
        	}
    	}
    	
    	if(titleScreenState == 1) {
    		
    	}
    	
    }

    public void drawPauseScreen() {
    	
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "P A U S A";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
    	
        // Windows Prototype
        int x = gp.tileSize*6;
        int y = gp.tileSize/2;
        int width = gp.tileSize*8;
        int height = gp.tileSize*3; 
        drawSubWindow(x, y, width, height);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
        x += gp.tileSize;
        y += gp.tileSize;
        
        for(String line: currentDialogue.split("\n")) {
        	g2.drawString(line, x, y);
            y += 40;
        }
    }
    
    public void drawCharacterScreen() {
    	
    	//Create a Frame
    	final int frameX = gp.tileSize *1;
    	final int frameY = gp.tileSize;
    	final int frameWidth = gp.tileSize*7;
    	final int frameHeight = gp.tileSize*9;
    	drawSubWindow(frameX,frameY,frameWidth,frameHeight);
    	
    	//Text
    	g2.setColor(Color.white);
    	g2.setFont(g2.getFont().deriveFont(28F));
    	
    	int textX = frameX + 20;
    	int textY = frameY + gp.tileSize;
    	final int lineHeight = 35;
    	
    	//Stats
    	g2.drawString("Nivel Actual", textX, textY); textY += lineHeight;
    	g2.drawString("Salud", textX, textY); textY += lineHeight;
    	g2.drawString("Misiles", textX, textY); textY += lineHeight;
    	g2.drawString("Fortaleza", textX, textY); textY += lineHeight;
    	g2.drawString("Destreza", textX, textY); textY += lineHeight;
    	g2.drawString("Ataque", textX, textY); textY += lineHeight;
    	g2.drawString("Defensa", textX, textY); textY += lineHeight;
    	g2.drawString("Experiencia", textX, textY); textY += lineHeight;
    	g2.drawString("Siguiente Nivel", textX, textY); textY += lineHeight;
    	g2.drawString("Partes", textX, textY); textY += lineHeight + 35;
    	g2.drawString("Arma Actual", textX, textY); textY += lineHeight + 40;
    	g2.drawString("Escudo Actual", textX, textY); textY += lineHeight;
    	
    	//Values
    	int tailX = (frameX + frameWidth) - 30;
    	//Reset textY
    	textY = frameY + gp.tileSize;
    	String value;
    	
    	value = String.valueOf(gp.player.level);
    	textX = getXforAlignToRightText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    	
    	value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
    	textX = getXforAlignToRightText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    	
    	value = String.valueOf(gp.player.ammo + "/" + gp.player.maxAmmo);
    	textX = getXforAlignToRightText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    	
    	value = String.valueOf(gp.player.strength);
    	textX = getXforAlignToRightText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    	
    	value = String.valueOf(gp.player.dexterity);
    	textX = getXforAlignToRightText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    	
    	value = String.valueOf(gp.player.attack);
    	textX = getXforAlignToRightText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    	
    	value = String.valueOf(gp.player.defense);
    	textX = getXforAlignToRightText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    	
    	value = String.valueOf(gp.player.exp);
    	textX = getXforAlignToRightText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    	
    	value = String.valueOf(gp.player.nextLevelExp);
    	textX = getXforAlignToRightText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    	
    	value = String.valueOf(gp.player.parts);
    	textX = getXforAlignToRightText(value,tailX);
    	g2.drawString(value, textX, textY);
    	textY += lineHeight;
    	
    	g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-11, null);
    	textY += gp.tileSize;
    	g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY, null);
    }

    public void drawInventory(Entity entity, boolean drawCursor) {
    	
    	int frameX = 0;
    	int frameY = 0;
    	int frameWidth = 0;
    	int frameHeight = 0;
    	int slotCol = 0;
    	int slotRow = 0;
    	
    	if(entity == gp.player) {
        	frameX = gp.tileSize*12;
        	frameY = gp.tileSize;
        	frameWidth = gp.tileSize*7;
        	frameHeight = gp.tileSize*6;
        	slotCol = playerSlotCol;
        	slotRow = playerSlotRow;
    	}
    	else {
        	frameX = gp.tileSize*3;
        	frameY = gp.tileSize;
        	frameWidth = gp.tileSize*7;
        	frameHeight = gp.tileSize*6;
        	slotCol = npcSlotCol;
        	slotRow = npcSlotRow;
    	}
    	//Frame
    	drawSubWindow(frameX,frameY, frameWidth, frameHeight);
    	
    	//Slot
    	final int slotXstart = frameX + 30;
    	final int slotYstart = frameY + 30;
    	int slotX = slotXstart;
    	int slotY = slotYstart;
    	int slotSize = gp.tileSize;

        int totalXItems = 6; // Total de ítems X
        int totalYItems = 5; // Total de ítems Y

    	//Draw Player Items
    	for(int i = 0; i < entity.inventory.size(); i++) {
    		
    		//Equip Cursor
    		if(entity.inventory.get(i) == entity.currentWeapon ||
    				entity.inventory.get(i) == entity.currentShield ||
    				entity.inventory.get(i) == entity.currentLight) {
    			
    			g2.setColor(new Color(240,190,90));
    			g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
    		}
    		
    		g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
    		
    		//Amount
    		if(entity == gp.player && entity.inventory.get(i).amount > 1) {
    			
    			g2.setFont(g2.getFont().deriveFont(32f));
    			int amountX;
    			int amountY;
    			
    			String s = "" + entity.inventory.get(i).amount;
    			amountX = getXforAlignToRightText(s, slotX + 44);
    			amountY = slotY + gp.tileSize;
    			
    			g2.setColor(Color.white);
    			g2.drawString(s, amountX-3, amountY-3);
    		}
    		
    		slotX += gp.tileSize;
    		
    		if(i == totalXItems - 1 || i % totalXItems == totalXItems - 1) {
    			slotX = slotXstart;
    			slotY += slotSize;
    		}
    	}
    	
    	//Cursor
    	if(drawCursor == true) {
    		
        	int cursorX = slotXstart + (slotSize * slotCol);
        	int cursorY = slotYstart + (slotSize * slotRow);
        	int cursorHeight = gp.tileSize;
        	int cursorWidth = gp.tileSize;
        	
        	//Draw Cursor
        	g2.setColor(Color.white);
        	g2.setStroke(new BasicStroke(0));
        	g2.drawRoundRect(cursorX, cursorY, cursorHeight, cursorWidth, 12, 12);
        	
        	//Description Frame
        	int dFrameX = gp.tileSize*12;
        	int dFrameY = gp.tileSize*7;
        	int dFrameWidth = gp.tileSize*7;
        	int dFrameHeight = gp.tileSize*2;
        	
        	//Draw Description Text
        	int textX = dFrameX + 40;
        	int textY = dFrameY + gp.tileSize;
        	g2.setFont(g2.getFont().deriveFont(25F));
        	
        	int itemIndex = getItemIndexOnSlot(slotCol,slotRow);
        	
        	if(itemIndex < entity.inventory.size()){
        		
        		drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
        		
        		for(String line: entity.inventory.get(itemIndex).description.split("\n")) {
        			g2.drawString(line, textX, textY);
        			textY +=32;
        		}
        	}
    	}
    		
    }

    
    public void drawGameOverScreen() {
    	
    	g2.setColor(new Color(0,0,0,175));
    	g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    	
    	int x;
    	int y;
    	String text;
    	g2.setFont(g2.getFont().deriveFont(Font.BOLD, 85F));
    	
    	text = "YOU ARE DEAD";
    	
    	//Main
    	x = getXforCenteredText(text);
    	y = gp.tileSize*4;
    	g2.setColor(Color.red);
    	g2.drawString(text, x-4, y-4);
    	
    	//Continue
    	g2.setFont(g2.getFont().deriveFont(30F));
    	g2.setColor(Color.white);
    	text = "Continue";
    	x = getXforCenteredText(text);
    	y += gp.tileSize*4;
    	g2.drawString(text, x, y);
    	if(commandNum == 0) {
    		g2.drawString("-", x-40, y);
    	}
    	
    	//Quit Game
    	text = "Quit Game";
    	x = getXforCenteredText(text);
    	y += 55;
    	g2.drawString(text, x, y);
    	if(commandNum == 1) {
    		g2.drawString("-", x-40, y);
    	}
    }
    
    public void drawOptionsScreen(){
    	
    	g2.setColor(Color.white);
    	g2.setFont(g2.getFont().deriveFont(40F));
    	
    	//SubWWindow
    	int frameX = gp.tileSize*6;
    	int frameY = gp.tileSize;
    	int frameWidth = gp.tileSize*8;
    	int frameHeight = gp.tileSize*10;
    	drawSubWindow(frameX,frameY, frameWidth, frameHeight);
    	
    	switch(subState) {
    	case 0: options_top(frameX, frameY);break;
    	case 1: options_fullScreenNotification(frameX, frameY); break;
    	case 2: options_control(frameX, frameY); break;
    	case 3: options_endGameConfirmation(frameX, frameY); break;
    	}
    	
    	gp.keyH.enterPressed = false;
    }
    
    public void options_top(int frameX, int frameY) {
    	
    	int textX;
    	int textY;
    	
    	//Title
    	String text = "Options";
    	textX = getXforCenteredText(text);
    	textY = frameY + gp.tileSize;
    	g2.drawString(text, textX, textY);
    	
    	//Full Screen
    	textX = frameX + gp.tileSize;
    	textY += gp.tileSize*2;
    	g2.drawString("Full Screen", textX, textY);
    	if(commandNum == 0) {
    		g2.drawString("-", textX-25, textY);
    		if(gp.keyH.enterPressed == true) {
    			if(gp.fullScreenOn == false) {
    				gp.fullScreenOn = true;
    			}
    			else if(gp.fullScreenOn == true) {
    				gp.fullScreenOn = false;
    			}
        		
        		subState = 1;
    		}
    	}
    	
    	//Music
    	textY += gp.tileSize;
    	g2.drawString("Music", textX, textY);
    	if(commandNum == 1) {
    		g2.drawString("-", textX-25, textY);
    	}
    		
    	//SE
    	textY += gp.tileSize;
    	g2.drawString("Special Effects", textX, textY);
    	if(commandNum == 2) {
    		g2.drawString("-", textX-25, textY);
    	}
    	 	
    	//Control
    	textY += gp.tileSize;
    	g2.drawString("Controls", textX, textY);

    	if (commandNum == 3) {
    	    g2.drawString("-", textX - 25, textY);
    	    if(gp.keyH.enterPressed == true) {
        	    subState = 2;
        	    commandNum = 0;
    	    }
    	}
    	//End Game
    	textY += gp.tileSize;
    	g2.drawString("End Game", textX, textY);
    	if(commandNum == 4) {
    		g2.drawString("-", textX-25, textY);
    	    if(gp.keyH.enterPressed == true) {
        	    subState = 3;
        	    commandNum = 0;
    	    }
    	}
    	
    	//Back
    	textY += gp.tileSize*2;
    	g2.drawString("Back", textX, textY);
    	if(commandNum == 5) {
    		g2.drawString("-", textX-25, textY);
    		if(gp.keyH.enterPressed == true) {
    			gp.gameState = gp.playState;
    			commandNum = 0;
    		}
    	}
    	
    	//Full Screen CheckBox
    	textX = frameX + (int)(gp.tileSize*5.5);
    	textY = frameY + gp.tileSize*3 -25;
    	g2.drawRect(textX, textY, 24, 24);
    	if(gp.fullScreenOn == true) {
    		g2.fillRect(textX, textY, 24, 24);
    	}
    	
    	// Music Volume
    	textY += gp.tileSize;
    	g2.drawRect(textX, textY, 120, 24);
    	int musicVolumeWidth = 24 * gp.music.volumeScale;
    	g2.fillRect(textX, textY, musicVolumeWidth, 24);

    	// Special Effects
    	textY += gp.tileSize;
    	g2.drawRect(textX, textY, 120, 24);
    	int seVolumeWidth = 24 * gp.se.volumeScale; // Corregido a gp.se.volumeScale
    	g2.fillRect(textX, textY, seVolumeWidth, 24);
    	
    	gp.config.saveConfig();
    }
    
    public void options_control(int frameX, int frameY) {
        
        int textX;
        int textY;
        
        //Title String
        String text = "Controls";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Movement", textX, textY); textY+=gp.tileSize;
        g2.drawString("Attack", textX, textY); textY+=gp.tileSize;
        g2.drawString("Shoot Missile", textX, textY); textY+=gp.tileSize;
        g2.drawString("Statistics", textX, textY); textY+=gp.tileSize;
        g2.drawString("Inventory", textX, textY); textY+=gp.tileSize;
        
        textX = frameX + gp.tileSize*5;
        textY = frameY + gp.tileSize*2;
        g2.drawString("W/A/S/D", textX, textY); textY+=gp.tileSize;
        g2.drawString("SPACE", textX, textY); textY+=gp.tileSize;
        g2.drawString("E", textX, textY); textY+=gp.tileSize;
        g2.drawString("C", textX, textY); textY+=gp.tileSize;
        g2.drawString("C", textX, textY); textY+=gp.tileSize;
        
        //BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0) {
            g2.drawString("-", textX-25, textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
            }
        }
    }

    public void options_fullScreenNotification(int frameX, int frameY) {
    	
    	int textX = frameX = gp.tileSize*7;
    	int textY = frameY + (int)(gp.tileSize*1.5);
    	
    	currentDialogue = "The change will be \napplied after \nrestar the game";
    	
    	for(String line: currentDialogue.split("\n")) {
    		g2.drawString(line, textX, textY);
    		textY += 40;
    	}
    	
    	//Back
    	textY += gp.tileSize*6;
    	g2.drawString("Back", textX, textY);
    	if(commandNum == 0) {
    		g2.drawString("-", textX-25, textY);
    		if(gp.keyH.enterPressed == true) {
    			subState=0;
    			commandNum = 3;    		}
    	}
    }
    
    public void options_endGameConfirmation(int frameX, int frameY) {
        
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        String currentDialogue = "Do you want to return \nto the title screen?";

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // Yes
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString("-", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }

        // No
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString("-", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 4;
            }
        }
    }
    
    public void drawTransition() {
    	
    	counter++;
    	g2.setColor(new Color(0,0,0,counter*5));
    	g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
    	
    	if(counter == 50) {
    		counter = 0;
    		gp.gameState = gp.playState;
    		gp.currentMap = gp.eHandler.tempMap;
    		gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
    		gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
    		gp.eHandler.previousEventX = gp.player.worldX;
    		gp.eHandler.previousEventY = gp.player.worldY;
    	}
    }
    
    public void drawtTradeScreen() {
    	
    	switch(subState) {
    	case 0: trade_select(); break;
    	case 1: trade_buy(); break;
    	case 2: trade_sell(); break;
    	}
    	gp.keyH.enterPressed = false;
    }
    
    public void trade_select() {
    	
    	drawDialogueScreen();
    	
    	//Window 1
    	int x = gp.tileSize * 11;
    	int y = gp.tileSize * 4;
    	int width = gp.tileSize *3;
    	int height = (int)(gp.tileSize * 3.5);
    	drawSubWindow(x,y, width, height);
    	
    	//Options
    	x += gp.tileSize;
    	y += gp.tileSize;
    	g2.drawString("Create", x, y);
    	if(commandNum == 0) {
    		g2.drawString("-", x-24, y);
    		if(gp.keyH.enterPressed == true) {
    			subState = 1;
    		}
    	}
    	y += gp.tileSize;
    	g2.drawString("Destroy", x, y);
    	if(commandNum == 1) {
    		g2.drawString("-", x-24, y);
    		if(gp.keyH.enterPressed == true) {
    			subState = 2;
    		}
    	}
    	y += gp.tileSize;
    	g2.drawString("Leave", x, y);
    	if(commandNum == 2) {
    		g2.drawString("-", x-24, y);
    		if(gp.keyH.enterPressed == true) {
    			commandNum = 0;
    			gp.gameState = gp.dialogueState;
    			currentDialogue = "Dont die!";
    		}
    	}
    } 
    
    public void trade_buy() {
        // Código actual del método
        drawInventory(gp.player, false);
    	//Draw NPC Inventory
    	drawInventory(npc, true);
    	
    	//Hint Window
    	int x = gp.tileSize*3;
    	int y = gp.tileSize*9;
    	int width = gp.tileSize*5;
    	int height = gp.tileSize*2;
    	drawSubWindow(x,y,width,height);
    	g2.drawString("[ESQ] Back", x+24, y+60);
    	
    	//Parts Window
    	x = gp.tileSize*13;
    	y = gp.tileSize*9;
    	width = gp.tileSize*6;
    	height = gp.tileSize*2;
    	drawSubWindow(x,y,width,height);
    	g2.drawString("Actual Parts: "+ gp.player.parts, x+24, y+60);
    	
    	//Price Window
    	int itemIndex = getItemIndexOnSlot(npcSlotCol,npcSlotRow);
    	if(itemIndex < npc.inventory.size()) {
    		
    		x = gp.tileSize * 3;
    		y = gp.tileSize * 7;
    		width = gp.tileSize * 5;
    		height = gp.tileSize * 2;
    		drawSubWindow(x,y,width,height);
    		g2.drawImage(part, x+10, y+23, 64, 64, null);
    		
    		int price = npc.inventory.get(itemIndex).price;
    		String text = "" + price;
    		x = getXforAlignToRightText(text, gp.tileSize*5);
    		g2.drawString(text, x, y+60);
    		
    		//Buy Item
    		if(gp.keyH.enterPressed == true) {
    			if(npc.inventory.get(itemIndex).price>gp.player.parts) {
    				subState = 0;
    				gp.gameState = gp.dialogueState;
    				currentDialogue = "Nop, I need more parts \nfor that homie";
    				drawDialogueScreen();
    			}
    			else {
    				if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
    					gp.player.parts -= npc.inventory.get(itemIndex).price;
    				}
    				else {
    					subState = 0;
    					gp.gameState = gp.dialogueState;
    					currentDialogue = "You cannot carry any more";
    				}
    			}
    		}
    	}
    }
    
    public void trade_sell() {
        
        drawInventory(gp.player, true);
        
        int x;
        int y;
        int width;
        int height;
        
        //Hint Window
        x = gp.tileSize * 3;
        y = gp.tileSize * 9;
        width = gp.tileSize * 5;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESQ] Back", x + 24, y + 60);
        
        //Parts Window
        x = gp.tileSize * 13;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Actual Parts: " + gp.player.parts, x + 24, y + 60);
        
        //Price Window
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {
            
            x = gp.tileSize * 3;
            y = gp.tileSize * 7;
            width = gp.tileSize * 5;
            height = gp.tileSize * 2;
            drawSubWindow(x, y, width, height);
            g2.drawImage(part, x + 10, y + 23, 64, 64, null);
            
            int price = gp.player.inventory.get(itemIndex).price/2;
            String text = String.valueOf(price);
            x = getXforAlignToRightText(text, gp.tileSize * 5);
            g2.drawString(text, x, y + 60);
            
            //Sell Item
            if (gp.keyH.enterPressed) {

            	if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
            			gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
            		commandNum = 0;
            		subState = 0;
            		gp.gameState = gp.dialogueState;
            		currentDialogue = "Dont be an Asshole dude, How \nyou're going to survive?";
            	}
            	else {
            		if(gp.player.inventory.get(itemIndex).amount > 1) {
            			gp.player.inventory.get(itemIndex).amount--;
            		}
                	else {
                		gp.player.inventory.remove(itemIndex);
                	}
            		gp.player.parts += price;
            	}
            }
        }
    }

    
    public int getItemIndexOnSlot(int slotCol, int slotRow) {
    	int itemIndex = slotCol + (slotRow*5);
    	return itemIndex;
    }
    
    public void drawSubWindow(int x, int y, int width, int height) {
    	
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(0));
        g2.drawRoundRect(x, y, width, height - 10, 25, 25);
    }
    
    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
    
    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}