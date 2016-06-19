import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Scanner;
import javax.imageio.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file
 */
public class ImageToText extends Component {
    static final String FILE = "imgOut.jpg";
    static Scale scale = new Scale();

    static BufferedImage img;
    static BufferedImage scaled;

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public ImageToText() {
        try {
            img = ImageIO.read(new File("C:\\Programming\\Java\\SomethingCool\\src\\" + FILE));
        } catch (IOException e) {
        }

    }

    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(100,100);
        } else {
            return new Dimension(img.getWidth(null), img.getHeight(null));
        }
    }

    public static void main(String[] args) throws Throwable{
        (new Scale()).act();
        baseText();
    }

    static void baseText() throws Exception{
        JFrame f = new JFrame("Load Image Sample");
        BufferedWriter bw;
        Scanner scn = new Scanner(System.in);
        bw = new BufferedWriter(new FileWriter("C:\\Programming\\Java\\SomethingCool\\src\\out.txt"));

        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.add(new ImageToText());
        f.pack();
        int pix;
        for(int i = 0; i < img.getHeight(); i++){
            for(int j = 0; j < img.getWidth(); j++){
                pix = img.getRGB(j, i);
                bw.write(black(pix));
            }
            bw.newLine();
        }
        System.out.println("Done!\n");
        bw.close();
        if(false) f.setVisible(true);
        else System.exit(0);
    }

    static String black(int pix){
        int a = -13000000;
        int b = -3000000;
        int c = -300000;
        //-8421504
        if(pix < a) return (char)9456 + " ";
        else if(pix > a && pix < b) return "* ";
        else if(pix > b && pix < c) return ". ";
        else return "  ";
    }
}
