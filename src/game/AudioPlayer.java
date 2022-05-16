package game;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioInputStream;

/*
 * This class controls the audio in the game.
 */
public class AudioPlayer {
	
	/*
	 * A small clip of an audio track.
	 */
	static Clip clip;
	
	/*
	 * Plays a looped sound clip.
	 * 
	 * @param fileName The name of the file thats audio is being used.
	 */
	public void playSoundLoop(String fileName)
	{
		//This gets the path to the project, but not into /src for eclipse
        String path = new File("").getAbsolutePath() + "\\Audios\\" + fileName;
        //Make a File object with a path to the audio file.
        File sound = new File(path);

        try 
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
            clip = AudioSystem.getClip();
            clip.open(ais); //Clip opens AudioInputStream
            clip.start(); //Start playing audio
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            Thread.sleep(1000);
        }
        catch (IOException ex){
            System.out.println("Sorry but there has been a problem reading your file.");
            ex.printStackTrace();
        }
        catch (UnsupportedAudioFileException ex1){
            System.out.println("Sorry but the audio file format you are using is not     supported.");
            ex1.printStackTrace();
        }
        catch (LineUnavailableException ex2){
            System.out.println("Sorry but there are audio line problems.");
            ex2.printStackTrace();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/*
	 * Plays a sound clip.
	 * 
	 * @param fileName The name of the file that is being played.
	 */
	public void playSoundOnce(String fileName)
	{
		//This gets the path to the project, but not into /src for eclipse
        String path = new File("").getAbsolutePath() + "\\Audios\\" + fileName;
        //Make a File object with a path to the audio file.
        File sound = new File(path);
       
        try 
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
            clip = AudioSystem.getClip();
            clip.open(ais); //Clip opens AudioInputStream
            clip.start(); //Start playing audio
        }
        catch (IOException ex){
            System.out.println("Sorry but there has been a problem reading your file.");
            ex.printStackTrace();
        }
        catch (UnsupportedAudioFileException ex1){
            System.out.println("Sorry but the audio file format you are using is not     supported.");
            ex1.printStackTrace();
        }
        catch (LineUnavailableException ex2){
            System.out.println("Sorry but there are audio line problems.");
            ex2.printStackTrace();
        } 
		
	}
	
	/*
	 * Stops sound being played.
	 */
	public void stopSound()
	{
		clip.stop();
	}
}