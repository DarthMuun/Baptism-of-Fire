package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL [30];
	FloatControl fc;
	
	int volumeScale = 3;
	float volume;
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/sound0.wav");
		soundURL[1] = getClass().getResource("/sound/keysdrop.wav");
		soundURL[2] = getClass().getResource("/sound/door.wav");
		soundURL[3] = getClass().getResource("/sound/object.wav");
		soundURL[4] = getClass().getResource("/sound/newmap.wav");
		soundURL[5] = getClass().getResource("/sound/hitglop.wav");
		soundURL[6] = getClass().getResource("/sound/receivedamage.wav");
		soundURL[7] = getClass().getResource("/sound/swingsword.wav");
		soundURL[8] = getClass().getResource("/sound/cursor.wav");
		soundURL[9] = getClass().getResource("/sound/ui.wav");
		soundURL[10] = getClass().getResource("/sound/burning.wav");
		soundURL[11] = getClass().getResource("/sound/menu.wav");
		soundURL[12] = getClass().getResource("/sound/deadsound.wav");
		soundURL[13] = getClass().getResource("/sound/destroymetal.wav");
		soundURL[14] = getClass().getResource("/sound/levelup.wav");
		soundURL[15] = getClass().getResource("/sound/health.wav");
		soundURL[16] = getClass().getResource("/sound/continue.wav");
		soundURL[17] = getClass().getResource("/sound/objecttaked.wav");
		soundURL[18] = getClass().getResource("/sound/metaldoor1.wav");
		soundURL[19] = getClass().getResource("/sound/metaldoor2.wav");
		soundURL[20] = getClass().getResource("/sound/parts.wav");
		soundURL[21] = getClass().getResource("/sound/welcome.wav");
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
			
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
	
	public void checkVolume() {
		
		switch(volumeScale) {
		case 0: volume = -80f; break;
		case 1: volume = -20f; break;
		case 2: volume = -5f; break;
		case 3: volume = 1f; break;
		case 4: volume = 6f; break;
		}
		fc.setValue(volume);
}
	

}
