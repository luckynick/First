
public class Device
{
    final Object lock = new Object();
    Light state;
    boolean work;
    int timeout;
    Light arr[];

    Device(Light arr[])
    {
        this.arr = arr;
        state = new Light(Color.GREEN);
        timeout = state.color.time;
        arr[0].next = true;
    }


    public void start()
    {
        work = true;
        System.out.println("Starting...");
        sequence();
    }
    public void stop()
    {
        work = false;
        System.out.println("Stopping...");
    }
    public void sequence()
    {
        while(work)
        {
            for (int i = 0; i < 3; i++)
            {
                if(arr[i].next && i < 2)
                {
                    arr[i].next = false;
                    arr[i + 1].next = true;
                    changeState(arr[i]);
                }
                if(i == 2 && arr[2].next)
                {
                    arr[i].next = false;
                    arr[0].next = true;
                    changeState(arr[i]);
                }
            }
        }
    }
    public void changeState(Light l)
    {
        if(!work) return;
        synchronized (lock)
        {
            state = l;
            timeout = state.color.time;
            System.out.println(state.color);
            try
            {
                lock.wait(timeout);
            } catch (InterruptedException e)
            {
                System.out.println("Stopped before timeout end.");
            }
        }
    }
}
