package main;

import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font stepalange_40,stepalange_80;
	public boolean messageOn = false;
	public String message = " ";
	int messageCounter = 0;
	public boolean gameFinished;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		
		this.gp = gp;
		
		stepalange_40 = new Font ("stepalange", Font.PLAIN, 40);
		stepalange_80 = new Font ("stepalange", Font.PLAIN, 80);
		
	}
	
	public void showMessage (String text) {
		
		message = text;
		messageOn = true;
		
	}
	
	public void draw(Graphics2D g2) {
	    g2.setFont(stepalange_40);
	    g2.setColor(Color.white);

	    if (gp.gameState == gp.playState) {
	        // Lógica de dibujo para el estado de reproducción, si es necesario
	    }

	    if (gp.gameState == gp.pauseState) {
	        drawPauseScreen(g2);  // Pasa g2 como argumento
	    }
	}

	public void drawPauseScreen(Graphics2D g2) {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
	    String text = "PAUSED";
	    int x = geXforCenteredText(text);
	    int y = gp.screenHeight/2;
	    g2.drawString(text, x, y);
	}

	public int geXforCenteredText(String text) {
	    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();    
	    int x = gp.screenWidth/2 - length/2;
	    return x;
	}
	
}
