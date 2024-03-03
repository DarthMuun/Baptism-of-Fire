package enviroment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Lighting {

    GamePanel gp;
    BufferedImage darknessFilter;
    public float filterAlpha = 0f;
    
    public final int day = 0;
    public final int dusk = 1;
    public final int night = 2;
    public final int dawn = 3;
    public int dayState = dusk;
    public int dayCounter = 0;

    public Lighting(GamePanel gp) {
    	this.gp = gp;
    	setLightSource();
    }
    
    public void setLightSource() {
    	
        //Create Buffered Image
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();
        
        if(gp.player.currentLight == null) {
        	g2.setColor(new Color(0,0,0.1f,0.90f));
        }
        else {
            //Circle
            int centerX = gp.player.screenX + (gp.tileSize) / 2;
            int centerY = gp.player.screenY + (gp.tileSize) / 2;

            //Graduation Effect
            Color color[] = new Color[5];
            float fraction[] = new float[5];
            
            color[0] = new Color (0,0,0.1f,0f);
            color[1] = new Color (0,0,0.1f,0.25f);
            color[2] = new Color (0,0,0.1f,0.5f);
            color[3] = new Color (0,0,0.1f,0.75f);
            color[4] = new Color (0,0,0.1f,0.98f);
            
            fraction[0] = 0f;
            fraction[1] = 0.25f;
            fraction[2] = 0.5f;
            fraction[3] = 0.75f;
            fraction[4] = 1f;
            
            //Graduation Paint
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius, fraction, color);
            
            //Gradient data g2
            g2.setPaint(gPaint);
        }
        
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.dispose();
    }
    public void resetDay() {
    	dayState = dusk;
    	filterAlpha = 0f;
    }
    
    public void update() {
    	
    	if(gp.player.lightUpdated == true) {
    		setLightSource();
    		gp.player.lightUpdated = false;
    	}
    	
    	//Check the state of the day
    	if(dayState == day) {
    		
    		dayCounter++;
    		
    		if(dayCounter > 3600000) {
    			dayState = dusk;
    			dayCounter = 0;
    		}
    	}
    	if(dayState == dusk) {
    		
    		filterAlpha += 0.090f;
    		
    		if(filterAlpha > 1f) {
    			filterAlpha = 1f;
    			dayState = night;
    		}
    	}
    	if(dayState == night) {
    		
    		dayCounter++;
    		
    		if(dayCounter > 3600000) {
    			dayState = dawn; // Changed to dawn instead of dusk
    			dayCounter = 0;
    		}
    	}
    	if(dayState == dawn) {
    		
    		filterAlpha -= 0.001f;
    		
    		if(filterAlpha < 0f) {
    			filterAlpha = 0;
    			dayState = day;
    		}
    	}
    }

    public void draw(Graphics2D g2) {
    	
    	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        g2.drawImage(darknessFilter, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }
}
