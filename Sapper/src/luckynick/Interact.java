package luckynick;


import java.io.IOException;
import java.util.Scanner;

class Interact{
    static Hidden table;
    static boolean valid = true;

    public static void run(){
        Scanner scn = new Scanner(System.in);

        int x, y, n;
        System.out.print("Choose dimention.\nx:");
        x = scn.nextInt();
        System.out.print("y:");
        y = scn.nextInt();
        System.out.print("Number of bombs:");
        n = scn.nextInt();
        table = new Hidden(x, y, n);
        while (valid) {
            table.print();
//            System.out.println();
//            table.print_struct();
            interpret();
            if(Hidden.counter >= TableStructure.limit){
                win();
                return;
            }
        }
        lose();
    }
    public static void interpret(){
        Scanner scn = new Scanner(System.in);
        int x, y;
        String sub_x = "", sub_y = "";
        boolean p = false;
        System.out.println();
        System.out.println("Format: x.y");
        String in = scn.nextLine();
        for(int i = 0; i < in.length(); i++){
            if(in.charAt(i) == '.'){
                p = true;
                continue;
            }
            if(!p) sub_x += in.charAt(i);
            else sub_y += in.charAt(i);
        }
        x = Integer.parseInt(sub_x);
        y = Integer.parseInt(sub_y);
        valid = table.action(x, y);
    }
    public static void win(){
        table.print();
        System.out.println("\nYou have won!\nCongrats!!!");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void lose(){
        table.print();
        System.out.println("\nLOOSER :P");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
