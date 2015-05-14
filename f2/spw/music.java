package f2.spw;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundTest {
  public static void main(String[] args) {
        File fileIn = new File("/f2/music/sound.wav");
          try {
                AudioInputStream audioInputStream =
                AudioSystem.getAudioInputStream(fileIn);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
          } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
          }
   }
}