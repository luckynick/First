package Sandbox;

import java.util.Scanner;

public class QueueAction
{
    public static void main(String[] args)
    {
        Queue q = new Queue();
        long st = System.currentTimeMillis();
        Writter1 wr1 = new Writter1(q);
        Writter1 wr2 = new Writter1(q);
        try
        {
            Thread.sleep(500);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        Reader1 r1 = new Reader1(q);
        try
        {
            wr1.thread.join();
            wr2.thread.join();
            r1.thread.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        long fi = System.currentTimeMillis();
        System.out.println("pos_add = : " + q.getPos_add());
        System.out.println("pos_get = : " + q.getPos_get());
        System.out.println("Executed in " + (fi - st) + " milliseconds.");

    }

    void sequence(Queue q)
    {
        Scanner scn = new Scanner(System.in);
        String in;
        do
        {
            System.out.print("Input something: ");
            in = scn.nextLine();
            if(in.equals("g")) q.get(true);
            else if(in.equals("finish"))
            {
                System.out.println("\n*** Finishing ***");
                return;
            }
            else
            {
                try
                {
                    q.add(Integer.parseInt(in));
                } catch (NumberFormatException e)
                {
                    System.out.println("Wrong input.");
                }
            }
        }
        while(!in.equals("finish"));
    }
}
class Queue
{
    final int BORDER = 1_000_000;
    private int pos_add = 0;
    private int pos_get = 0;
    private boolean first = true;
    private int arr[] = new int[BORDER];
    private boolean fill[] = new boolean[BORDER];
    Queue(){
        init_fill();
    }

    synchronized void add(int in)
    {
        System.out.print("Adding " + in + ": ");
        if(pos_add == BORDER && !fill[0]) pos_add = 0;
        else if((pos_add == BORDER && fill[0]) || fill[pos_add]) {
            System.out.println("queue is full.");
            return;
        }
        System.out.println("success.");
        arr[pos_add] = in;
        fill[pos_add] = true;
        pos_add++;
    }
    synchronized int get(boolean console)
    {
        System.out.print("Getting: ");
        if(!first)
        {
            if(pos_get == BORDER && fill[0]) pos_get = 0;
            else if(pos_get < BORDER && !fill[pos_get])
            {
                System.out.println("nothing to get.");
                return 0;
            }
        }
        int val = arr[pos_get];
        fill[pos_get] = false;
        pos_get++;
        first = false;
        if(console) System.out.println(val);
        if(pos_get == BORDER) pos_get = 0;
        return val;
    }
    private void init_fill()
    {
        for(int i = 0; i < BORDER; i++)
        {
            fill[i] = false;
        }
    }

    public int getPos_add()
    {
        return pos_add;
    }
    public int getPos_get()
    {
        return pos_get;
    }
}
