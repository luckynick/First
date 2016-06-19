package main.java;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;

/**
 * Created by luckynick on 15-Jun-16.
 */
public class Test
{
    public static void main(String args[])
    {
        JFrame fr = new JFrame();
        fr.setSize(500, 500);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel label = new JLabel();
        label.setFont(new Font("Serif", Font.PLAIN, 70));
        fr.setLayout(new GridBagLayout());
        for(int i = 9; i >=0; i--)
        {
            label.setText(Integer.toString(i));
            fr.add(label);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fr.remove(label);
        for(int i = 0; true; i++)
        {
            fr.getContentPane().setBackground(Color.red);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fr.getContentPane().setBackground(Color.green);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
