import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;


public class Scale
{

    public int outWidth, outHeight;
    public final int WLIM = 59, HLIM = 36; //59 36
    public int pix[][];
    public BufferedImage imgOut;



    public void act() throws Throwable
    {
        File f_img = new File(select("C:\\Programming\\Java\\SomethingCool\\src\\img\\"));
        BufferedImage img = ImageIO.read(f_img);
        outWidth = img.getWidth();
        outHeight = img.getHeight();
        if(outWidth > WLIM || outHeight > HLIM) reduce(img);
    }

    BufferedImage reduce(BufferedImage img) throws Exception
    {
        double relat_hor, relat_ver;
        relat_hor = (double)outWidth / WLIM;
        relat_ver = (double)outHeight / HLIM;
        int hor = outWidth / WLIM;
        int ver = outHeight / HLIM;
        System.out.println("hor: " + hor);
        System.out.println("ver: " + ver);
        int sizeW, sizeH;
        int small;
        if(outWidth > outHeight)
        {
            sizeW = WLIM;
            sizeH = (int)(outHeight/relat_hor);
            small = sizeW;
            ver = hor;
            System.out.println("sizeH: " + sizeH);
            System.out.println("sizeW: " + sizeW);
        }
        else if(outWidth < outHeight)
        {
            sizeH = HLIM;
            sizeW = (int)(outWidth/relat_ver);
            small = sizeW;
            hor = ver;
        }
        else
        {
            sizeH = HLIM;
            sizeW = HLIM;
            small = sizeW;
            hor++;
        }
        pix = new int[sizeW][sizeH];
        ver++;
        hor++;
        for(int i = 0; i < outHeight; i += ver)
        {
            for(int j = 0; j < outWidth; j += hor)
            {
                try {
                    pix[j/hor][i/ver] = img.getRGB(j, i);
                }
                catch(ArrayIndexOutOfBoundsException ex){
                    System.out.println("Got (" + j + ", " + i + ')');
                }
            }
        }
        int sub[] = new int[sizeH*sizeW];
        int c = 0;
        for(int j = 0; j < sizeH; j++) {
            for (int i = 0; i < sizeW; i++) {
                sub[c] = pix[i][j];
                c++;
            }
        }
        int m = 0, n = 0;
        //int m = -1*(int)(outWidth-((outWidth/sizeW)+1)*sizeW), n = -1*(int)(outHeight-(outHeight/sizeH+1)*sizeH);
        //int m = sizeW/hor-1, n = sizeH/ver-1;
        System.out.println(m + " "  + n + " " + small);
        imgOut = new BufferedImage(sizeW-m, sizeH-n, BufferedImage.TYPE_INT_RGB);
        imgOut.setRGB(0, 0, sizeW-m, sizeH-n, sub, 0, small);
        ImageIO.write(imgOut, "jpg",  new File("C:\\Programming\\Java\\SomethingCool\\src\\imgOut.jpg"));
        return null;
    }


    String select(String path)
    {
        boolean fast = true;
        File f = new File(path);
        String files[] = f.list();
        if(fast) return path + files[8];
        System.out.println("*** List of pictures ***");
        for(int i = 0; i < files.length;){
            for(int j = 0; j < 4 && i < files.length; j++) {
                System.out.print(i + ". " + files[i] + "\t");
                i++;
            }
            System.out.println();
        }
        System.out.println("Choose file: ");
        Scanner scn = new Scanner(System.in);
        int choise = scn.nextInt();
        return path + files[choise];
    }

}
