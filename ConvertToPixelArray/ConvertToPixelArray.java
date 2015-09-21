import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.util.*;
/**
 * Converts an image to a pixel array of RGB values.
 * 
 * @author Kenny Scharm 
 * @version 9/2/14
 */
public class ConvertToPixelArray
{
    private static RGBPixel[][] pixels;
    private static int[][] ints;
    public static void main(String [] args) {
        Image image = null;
        try {
            File sourceImage = new File("A.png");
            image = ImageIO.read(sourceImage);
        } catch (IOException e) {}
        BufferedImage scaledImage = rescaleImage(image, 100, 162, true);
        ints = convertToIntegerArray(scaledImage);
        JFrame f = new JFrame();
        JLabel l = new JLabel(new ImageIcon(scaledImage));
        f.getContentPane().add(l, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }   

    public static BufferedImage rescaleImage(Image image, int width, int height, boolean preserveAlpha)
    {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(width, height, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if(preserveAlpha)
        {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return scaledBI;
    }

    public static RGBPixel[][] convertToPixelArray(BufferedImage image)
    {
        int r = 0, g = 0, b = 0;
        int width = image.getWidth();
        int height = image.getHeight();
        int totalPixels = (width * height);
        pixels = new RGBPixel[width][height];
        for(int row = 0; row < width; row++)
        {
            for(int col = 0; col < height; col++)
            {
                int rgb = image.getRGB(row, col);
                Color c = new Color(rgb);
                RGBPixel temp = new RGBPixel(c.getRed(), c.getGreen(), c.getBlue());
                int colorVal = temp.getColorVal();
                if(colorVal < 128)
                {
                    temp.setBlack();
                }
                else
                {
                    temp.setWhite();
                }
                pixels[row][col] = temp;
            }
        }

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++)
            {
                if(pixels[j][i].getColorVal() == 255) {
                    System.out.print(" ");
                } else {
                    System.out.print("W");
                }
            }
            System.out.println();
        }
        return pixels;
    }
    
    public static int[][] convertToIntegerArray(BufferedImage image)
    {
        int r = 0, g = 0, b = 0;
        int width = image.getWidth();
        int height = image.getHeight();
        int [][] img = new int[width][height];
        for(int row = 0; row < width; row++)
        {
            for(int col = 0; col < height; col++)
            {
                int rgb = image.getRGB(row, col);
                Color c = new Color(rgb);
                RGBPixel t = new  RGBPixel(c.getRed(), c.getGreen(), c.getBlue());
                int colorVal = t.getColorVal();
                if(colorVal < 128)
                {
                    img[row][col] = 0;
                }
                else
                {
                    img[row][col] = 255;
                }
              
            }
        }
        
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++)
            {
                if(img[j][i] == 255) {
                    System.out.printf("%-3d", img[j][i]);
                } else {
                    System.out.printf("%-3d", img[j][i]);
                }
            }
            System.out.println();
        }
        return img;
    }
}
