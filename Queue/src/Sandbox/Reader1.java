package Sandbox;

/**
 * Created by luckynick on 17-Jun-16.
 */
public class Reader1 implements Runnable
{
    Thread thread;
    Queue queue;
    static int row = 1;

    Reader1(Queue q)
    {
        thread = new Thread(this, "Read" + (row++) + " thread");
        queue = q;
        thread.setPriority(6);
        thread.start();
    }

    @Override
    public void run()
    {
        for(int i = 0; i < 999_998; i++)
        {
            queue.get(true);
        }
    }
}
