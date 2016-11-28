import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Recorder
{
    public static void main(String args[])
    {
        AudioFormat af = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, af);
        if(!AudioSystem.isLineSupported(info))
        {
            System.err.println("Line is not supported!");
            return;
        }
        try
        {
            final TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(info);
            targetLine.open();
            System.out.println("Starting recording.");
            targetLine.start();
            AudioInputStream ais = new AudioInputStream(targetLine);
            new Thread()
            {
                @Override
                public void run()
                {
                    File f = new File("audio1.wav");
                    try
                    {
                        AudioSystem.write(ais, AudioFileFormat.Type.WAVE, f);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }.start();
            Thread.sleep(5000);
            System.out.println("Stopping recording.");
            targetLine.stop();
            targetLine.close();

        }
        catch (LineUnavailableException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
