
public class Tock implements Runnable
{
    Thread thread;
    static boolean act = true;

    Tock()
    {
        thread = new Thread(this, "thr2");
    }

    @Override
    public void run()
    {
        while(act)
        {
            Mid.tock();
        }
    }
}
