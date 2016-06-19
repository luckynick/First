package Sandbox;

public class Writter1 implements Runnable
{
    Thread thread;
    Queue queue;
    static int row = 1;

    Writter1(Queue q)
    {
        thread = new Thread(this, "Writ" + (row++) + " thread");
        queue = q;
        thread.setPriority(5);
        thread.start();
    }

    @Override
    public void run()
    {
        for(int i = 0; i < 500_000; i++)
        {
            queue.add(i);
        }
    }
}
