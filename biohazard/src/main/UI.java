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

import object.OBJ_Heart;
import entity.Entity;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font mp16reg;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;// 0 = 1st Screen, 1 = 2nd Screen

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
        
        //Times
    }

    public void showMessage(String text) {
    	
        message = text;
        messageOn = true;
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

        }
        // Pause State
        if (gp.gameState == gp.pauseState) {
        	drawPlayerLife();
            drawPauseScreen();
        }
        // Dialogue State
        if (gp.gameState == gp.dialogueState) {
        	drawPlayerLife();
            drawDialogueScreen();
        }
        //Character State
        if(gp.gameState == gp.characterState) {
        	drawCharacterScreen();
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
    }
    
    public void drawTitleScreen() {
    	
    	if(titleScreenState == 0) {
    		
    		//Background
        	g2.setColor(new Color(0, 0, 0));
        	g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        	
        	//Title Name
        	g2.setFont(g2.getFont().deriveFont(Font.BOLD,150F));
        	String text = "Jaeger Demo";
        	int x = getXforCenteredText(text);
        	int y = gp.tileSize*4;    	
        	
        	//Shadow
        	//g2.setColor(Color.red);
        	//g2.drawString(text, x+2, y+2);
        	
        	//Main Color
        	g2.setColor(Color.white);
        	g2.drawString(text, x, y);
        	
        	//ProtoType UI
        	//x = gp.screenWidth/2 - (gp.tileSize*2/2);
        	//y = gp.tileSize*4;
        	//g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
        	
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
        int x = gp.tileSize*7;
        int y = gp.tileSize/2;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2; 
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
    	final int frameWidth = gp.tileSize*5;
    	final int frameHeight = gp.tileSize*9;
    	drawSubWindow(frameX,frameY,frameWidth,frameHeight);
    	
    	//Text
    	g2.setColor(Color.white);
    	g2.setFont(g2.getFont().deriveFont(30F));
    	
    	int textX = frameX + 20;
    	int textY = frameY + gp.tileSize;
    	final int lineHeight = 35;
    	
    	//Stats
    	g2.drawString("Nivel Actual", textX, textY);
    	textY += lineHeight;
    	g2.drawString("Salud", textX, textY);
    	textY += lineHeight;
    	g2.drawString("Fortaleza", textX, textY);
    	textY += lineHeight;
    	g2.drawString("Destreza", textX, textY);
    	textY += lineHeight;
    	g2.drawString("Ataque", textX, textY);
    	textY += lineHeight;
    	g2.drawString("Defensa", textX, textY);
    	textY += lineHeight;
    	g2.drawString("Experiencia", textX, textY);
    	textY += lineHeight;
    	g2.drawString("Siguiente Nivel", textX, textY);
    	textY += lineHeight;
    	g2.drawString("Partes", textX, textY);
    	textY += lineHeight + 35;
    	g2.drawString("Arma Actual", textX, textY);
    	textY += lineHeight + 40;
    	g2.drawString("Escudo Actual", textX, textY);
    	textY += lineHeight;
    	
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

    public void drawSubWindow(int x, int y, int width, int height) {
    	
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
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