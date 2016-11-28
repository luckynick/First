import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Player implements LineListener {
    static Mixer mixer;
    static Clip clip;
    static boolean wait = true;

    public static void main(String args[])
    {
        Mixer.Info infos[] = AudioSystem.getMixerInfo();
        mixer = AudioSystem.getMixer(infos[0]);
        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
        try
        {
            clip = (Clip) mixer.getLine(dataInfo);
        }
        catch (LineUnavailableException e)
        {
            e.printStackTrace();
            return;
        }
        AudioInputStream ais;
        try
        {
            URL clipURL = Player.class.getResource("audio.wav");
            ais = AudioSystem.getAudioInputStream(clipURL);
            clip.open(ais);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
            return;
        }
        clip.addLineListener(new Player());
        clip.start();
        while(wait)
        {
            try
            {
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                return;
            }
        }
    }

    @Override
    public void update(LineEvent event)
    {
        if(event.getType() == LineEvent.Type.STOP)
            wait = false;
    }
}
