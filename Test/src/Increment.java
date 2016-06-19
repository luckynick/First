import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by luckynick on 01-May-16.
 */
public class Increment extends Thread
{

    static Contain contain = new Contain();
    Thread t;
    Increment(String name)
    {
        super(name);
        t = Thread.currentThread();
        start();
    }
    @Override
    public void run()
    {
        for(int i = 0; i < 50_000_000; i++)
        {
            contain.incr();
        }
    }
}

class Contain
{
    AtomicInteger i = new AtomicInteger(0);

    void incr()
    {
        i.incrementAndGet();
    }
}