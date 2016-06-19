
public class Tick implements Runnable
{
    Thread thread;
    boolean act = true;

    Tick()
    {
        thread = new Thread(this, "thr1");
    }

    @Override
    public void run()
    {
        while(act)
        {
            Mid.tick();
            if(!act) Tock.act = false;
        }
    }
}
