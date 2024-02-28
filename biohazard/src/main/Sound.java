package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL [30];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/sound0.wav");
		soundURL[1] = getClass().getResource("/sound/keysdrop.wav");
		soundURL[2] = getClass().getResource("/sound/door.wav");
		soundURL[3] = getClass().getResource("/sound/objecttaked.wav");
		soundURL[4] = getClass().getResource("/sound/newmap.wav");
		soundURL[5] = getClass().getResource("/sound/hitmonster.wav");
		soundURL[6] = getClass().getResource("/sound/receivedamage.wav");
		soundURL[7] = getClass().getResource("/sound/swingsword.wav");
		soundURL[8] = getClass().getResource("/sound/cursor.wav");
		soundURL[9] = getClass().getResource("/sound/ui.wav");
		soundURL[10] = getClass().getResource("/sound/burning.wav");
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
			
		}
		
	}
	
	public void play() {
		
		clip.start();
		
	}
	
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
	public void stop() {
		
		clip.stop();
		
	}
	

}
