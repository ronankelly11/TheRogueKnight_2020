package util;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class Audio{
	static private AudioInputStream sound;
	static private AudioInputStream music;


	
	public Audio() {
	}
	
	
	public void playSound(String fileName) {
		try {
			sound = AudioSystem.getAudioInputStream(new File(fileName));
			Clip clip = AudioSystem.getClip();
			clip.open(sound);
			clip.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playLevelSound(String fileName) {  
		try { 
			// Change to 60 for second version 
			music = AudioSystem.getAudioInputStream(new File(fileName));	        
			Clip clip = AudioSystem.getClip();
	        clip.open(music);
	        clip.start();
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	       
	        // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY); 
	        // If you want to stop the sound, then use clip.stop();
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
		
	}
	
	public void stopMusic() {
		this.music = null;
	}
}