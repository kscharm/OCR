import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.util.*;
/**
 * @author Kenny Scharm 
 * @version 9/12/14
 */
public class ImageScalar
{
    private boolean[][] original;
    private boolean[][] newImage;
    private int[] numbers;
    private int width;
    private int height;
    private ArrayList<Point> points = new ArrayList<Point>();
    public BufferedImage bi;
    public int xp, yp;

    //     ImageScalar a = new ImageScalar("A.png");
    //     ImageScalar b = new ImageScalar("B.png");
    //     ImageScalar c = new ImageScalar("C.png");
    //     ImageScalar d = new ImageScalar("D.png");
    //     
    //     public boolean[][] STANDARD_A = a.downscale(xp, yp);
    //     public boolean[][] STANDARD_B = b.downscale(xp, yp);
    //     public boolean[][] STANDARD_C = c.downscale(xp, yp);
    //     public boolean[][] STANDARD_D = d.downscale(xp, yp);
    public ImageScalar(BufferedImage bi)
    {
        // image is 100 x 162
        this.bi = bi;
        try {
            xp = -1;
            yp = -1;
        }
        //catch (IOException e) {System.out.print(e);}
        catch (InputMismatchException  i) {System.out.println("Invalid input"); }
    }

    public void initialize(int a, int b) 
    {
        xp = a;
        yp = b;
        original = convertToBooleanArray(bi);
        boolean[] single;
        if(xp > width || yp > height)
        {
            System.out.println("The values you entered are too large" + "\n" + "Please enter new dimensions");
            initialize(xp, yp);                
        }
        if(xp < 0 || yp < 0)
        {
            System.out.println("Invalid input");
            initialize(xp, yp);
        }

        try{
            newImage = downscale(xp, yp);
            single = convertToSingleArray(newImage);
            numbers = convertToIntArray(single);
        }catch(Exception e){System.out.println("Cannot read file");}
    }

    public int promptForXPInput()
    {
        Scanner reader = new Scanner(System.in);
        System.out.print("X dimension: ");
        xp = reader.nextInt();
        return xp;
    }

    public int promptForYPInput()
    {
        Scanner reader = new Scanner(System.in);
        System.out.print("Y dimension: ");
        yp = reader.nextInt();
        return yp;
    }

    public int getXP()
    {
        return this.xp;
    }

    public int getYP()
    {
        return this.yp;
    }

    public boolean [][] getImageArray()
    {
        return original;
    }

    private boolean [] convertToSingleArray(boolean [][] img)
    {
        boolean [] numbers = new boolean[img.length * img[0].length];
        int count = 0;
        for(int y = 0; y < img.length; y++)
        {
            for(int x = 0; x < img[0].length; x++)
            {
                numbers[count] = img[y][x];
                count++;
            }
        }
        return numbers;
    }

    private int [] convertToIntArray(boolean [] temp)
    {
        int [] numbers = new int[temp.length];
        for(int i = 0; i < temp.length; i++)
        {
            if(temp[i])
                numbers[i] = 1;
            else
                numbers[i] = 0;
        }
        return numbers;
    }

    private double percentBlack(int i, int j, int xp, int yp)
    {
        int windowWidth = width / xp; // 20 pixels wide
        int windowHeight = height / yp; // 23 pixels high
        int totalArea = windowWidth * windowHeight;
        int count = 0;
        int startX = windowWidth * j;
        int startY = windowHeight * i;
        for(int y = startY; y < startY + windowHeight - 1; y++)
        {
            for(int x = startX; x < startX + windowWidth - 1; x++)
            {
                if(original[y][x])
                {
                    count++;
                }
            }
        }
        return (double)((double)(count) / totalArea);
    }

    public boolean [][] downscale(int xp, int yp)
    {
        int h = yp;
        int w = xp;
        newImage = new boolean[h][w];
        for(int y = 0; y < h; y++)
        {
            for(int x = 0; x < w; x++)
            {
                double percentBlack = percentBlack(y, x, xp, yp);
                if(percentBlack > .200)
                {
                    newImage[y][x] = true;
                }
                else
                {
                    newImage[y][x] = false;
                } 
            }
        }
        return newImage;
    }

    private boolean[][] convertToBooleanArray(BufferedImage image)
    {
        if(image == null)
            return null;
        int r = 0, g = 0, b = 0;
        width = image.getWidth();
        height = image.getHeight();
        original = new boolean[height][width];
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                int rgb = image.getRGB(x, y);
                Color c = new Color(rgb);
                int colorVal = (int)(c.getRed() + c.getGreen() + c.getBlue()) / 3;
                if(colorVal < 128)
                {
                    original[y][x] = true;
                }
                else
                {
                    original[y][x] = false;
                }
            }
        }
        return original;
    }

    private Point getStart() {
        Point start = new Point(0,0);
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                if(original[y][x])
                {
                    start = new Point(y, x);
                    points.add(start);
                    System.out.print(start);
                    return start;
                }

            }
        }
        points.add(start);
        return start;
    }

    public void printLetters()
    {
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                if(original[y][x])
                {
                    System.out.print("W");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        for(int y = 0; y < yp; y++)
        {
            for(int x = 0; x < xp; x++)
            {
                if(newImage[y][x])
                {
                    System.out.print("W");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public boolean[][] getNewImage()
    {
        return newImage;
    }

    public class Point
    {
        private int y;
        private int x;
        /**
         * Constructor for objects of class Point
         */
        public Point(int y, int x)
        {
            // initialise instance variables
            this.x = x;
            this.y = y;
        }

        public int getX()
        {
            return x;
        }

        public int getY()
        {
            return y;
        }

        public Point up()
        {
            return new Point(y, x + 1);
        }

        public Point down()
        {
            return new Point(y, x - 1);
        }

        public Point left()
        {
            return new Point(y - 1, x);
        }

        public Point right()
        {
            return new Point(y + 1, x);
        }
    }    
}

