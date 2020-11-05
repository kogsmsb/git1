package version3;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicStuff {
	
	AudioInputStream audioInput;
	Clip clip;
	
	public void playMusic(String musicLocation,int flag)
	{
		try
		{
			File musicPath = new File(musicLocation);
			if(musicPath.exists())
			{
				if (flag==0) {
					clip.stop();
				}
				else {
					audioInput = AudioSystem.getAudioInputStream(musicPath);
					clip = AudioSystem.getClip();
					clip.open(audioInput);
					clip.start();
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
			}
		}
		catch(Exception ex)
		{
		}
	}
}