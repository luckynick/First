

public class Main
{

    public static void main(String args[])
    {
        Tick tick = new Tick();
        Tock tock = new Tock();
        tick.thread.start();
        tock.thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tick.act = false;
    }
}
