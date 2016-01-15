import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.util.*;
/**
 * Optical Character Recognition main class.
 * 
 * @author Kenny Scharm
 * @version 9/2/14
 */
public class Main
{
    public static void main(String [] args)
    {    

        Greyscale ga = new Greyscale("A.png");
        BufferedImage ba = ga.convertToGreyscale();
        ImageScalar a = new ImageScalar(ba);
        int xp = a.promptForXPInput();
        int yp = a.promptForYPInput();

        Greyscale gb = new Greyscale("B.png");
        BufferedImage bb = gb.convertToGreyscale();
        ImageScalar b = new ImageScalar(bb);

        Greyscale gc = new Greyscale("C.png");
        BufferedImage bc = gc.convertToGreyscale();
        ImageScalar c = new ImageScalar(bc);

        Greyscale gd = new Greyscale("D.png");
        BufferedImage bd = gd.convertToGreyscale();
        ImageScalar d = new ImageScalar(bd);

        a.initialize(xp, yp);
        b.initialize(xp, yp);
        c.initialize(xp, yp);
        d.initialize(xp, yp);
        //         boolean[][] STANDARD_A = a.downscale(xp, yp);
        //         boolean[][] STANDARD_B = b.downscale(xp, yp);
        //         boolean[][] STANDARD_C = c.downscale(xp, yp);
        //         boolean[][] STANDARD_D = d.downscale(xp, yp);

        a.printLetters();
        b.printLetters();
        c.printLetters();
        d.printLetters();
        
        boolean[][] img1 = a.getNewImage();
        boolean[][] img2 = b.getNewImage();
        boolean[][] img3 = c.getNewImage();
        System.out.println("\n\n\n\n");

        for (int i=0; i< img1.length; i++)
        {
            for (int j =0; j< img1[0].length; j++)
            {
                if (img1[i][j] == true)
                    System.out.print("w");
            }
            System.out.println();
        }
        for (int i=0; i< img2.length; i++)
        {
            for (int j =0; j< img2[0].length; j++)
            {
                if (img2[i][j] == true)
                    System.out.print("w");
            }
            System.out.println();
        }
        for (int i=0; i< img3.length; i++)
        {
            for (int j =0; j< img3[0].length; j++)
            {
                if (img3[i][j] == true)
                    System.out.print("w");
            }
            System.out.println();
        }
    }

}
