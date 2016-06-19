
public class Main
{
    public static void main(String args[])
    {
        Device device = new Device(initLights());
        Execution exe = new Execution(device);
        try
        {
            Thread.sleep(6000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        device.stop();
        Execution.thread.interrupt();
        /*try
        {
            Thread.sleep(3000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }*/
        /*exe.rerun();
        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        device.stop();
        Execution.thread.interrupt();*/
    }

    public static Light[] initLights()
    {
        Light arr[] = {new Light(Color.RED), new Light(Color.YELLOW), new Light(Color.GREEN)};
        return arr;
    }
}
