import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.DataBufferByte;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
/**
 * Write a description of class Main here.
 * 
 * @author Kenny Scharm
 * @version 1/31/2013
 */
public class Greyscale
{
    public String fileName;
    public Greyscale(String fileName)
    {
        this.fileName = fileName;
        BufferedImage img = null;
        try 
        {
            img = ImageIO.read(new File(fileName));            
        } 

        catch (IOException e) 
        {
            System.out.println("The file was not found.");
        }
    }

    public BufferedImage convertToGreyscale()
    {   
        BufferedImage editImg = null;
        try
        {
            editImg = ImageIO.read(new File(fileName)); 
        }
        catch(IOException e)
        {
            System.out.println("The file was not found.");
        }
        int max = -1;
        for (int i =0; i< editImg.getWidth(); i++)
        {
            for (int j =0; j < editImg.getHeight(); j++)
            {
                int a= editImg.getRGB(i, j); 
                String hexColor = String.format("#%06X", (0xFFFFFF & a));
                int r = Integer.valueOf(hexColor.substring(1,3),16);
                int g = Integer.valueOf(hexColor.substring(3,5),16);
                int b = Integer.valueOf(hexColor.substring(5,7),16);

                int x = (int)(0.299*(r) + 0.587*(g) + 0.114*(b));
                if (x> max)
                {
                    max = x;
                }
                String hex = String.format("#%02x%02x%02x", x, x, x);

                int val = Integer.valueOf(hex.substring(1, hex.length()), 16);
                editImg.setRGB(i, j, val);
            }
        }
        
        return editImg;
    }
}
