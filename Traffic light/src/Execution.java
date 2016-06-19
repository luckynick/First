
public class Execution extends Thread
{
    static Thread thread;
    static Device device;

    Execution(Device dev)
    {
        device = dev;
        thread = new Thread(this, "Exe thread");
        thread.start();
    }

    void rerun()
    {
        thread = new Thread(this, "Exe thread");
        thread.start();
    }

    @Override
    public void run()
    {
        device.start();
    }
}
