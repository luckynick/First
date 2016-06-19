
public class Stopwatch extends Thread
{
    Thread thread;
    String name;
    private long seconds = 0;
    private boolean count;

    Stopwatch(String name)
    {
        this.name = name;
        thread.currentThread();
        count = true;
        start();
    }

    @Override
    public void run(){
        while(count)
        {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Stopwatch " + name + " interrupted.");
                seconds--;
            }
            seconds++;
        }
    }

    void finish()
    {
        count = false;
    }

    long getSeconds() { return seconds; }
}
