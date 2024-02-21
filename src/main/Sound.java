package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	
	URL soundURL[] = new URL[30]; // Store file path of sound files
	
	public Sound() {
		soundURL[0] = getClass().getResource("/res/sound/BackgroundSongOfficial.wav");
		soundURL[1] = getClass().getResource("/res/sound/Coin.wav");
		soundURL[2] = getClass().getResource("/res/sound/PowerUp.wav");
		soundURL[3] = getClass().getResource("/res/sound/Pickup.wav");
		soundURL[4] = getClass().getResource("/res/sound/FanFare.wav");
		soundURL[5] = getClass().getResource("/res/sound/damageSFXMon.wav");
		soundURL[6] = getClass().getResource("/res/sound/damageSFXHit.wav");
		soundURL[7] = getClass().getResource("/res/sound/swordSwing.wav");
		soundURL[8] = getClass().getResource("/res/sound/LevelUp.wav");
		soundURL[9] = getClass().getResource("/res/sound/cursormove.wav");
		soundURL[10] = getClass().getResource("/res/sound/Fireball.wav");
	}
	
	public void setFile(int i) {
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		} catch (Exception e) {
			
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
