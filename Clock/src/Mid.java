
public class Mid
{
    static final Object lock = new Object();

    static void tick()
    {
        synchronized (lock)
        {
            System.out.print("Tick - ");
            lock.notify();
            try {
                Thread.sleep(500);
                lock.wait(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void tock()
    {
        synchronized (lock)
        {
            System.out.println("Tock");
            lock.notify();
            try {
                Thread.sleep(500);
                lock.wait(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
