package FightingGame.Model;
import java.io.File;
import javax.sound.sampled.*;
public class SoundHandler {
    @SuppressWarnings({ "CallToPrintStackTrace", "UseSpecificCatch" })
    public Clip soundControl(String strPath, boolean blnIsOn){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(strPath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            return clip;
        }catch (Exception e){
            System.err.println("Error with playing sound.");
            e.printStackTrace();
            return null;
        }
    }
    @SuppressWarnings("CallToPrintStackTrace")
    public void sounndOff(Clip clip){
        try{
            clip.close();
        }catch (Exception e){
            System.err.println("Error with playing sound.");
            e.printStackTrace();
        }
    }
}
