
/**
 * Pixel with a red, blue, and green element.
 * 
 * @author Kenny Scharm
 * @version 9/2/14
 */
public class RGBPixel
{
    private int r, g, b;

    /**
     * Constructor for objects of class RGBPixel
     */
    public RGBPixel(int r, int g, int b)
    {
        this.r = r;
        this.g = g;
        this.b = g;
    }
    
    public int getRed()
    {
        return r;
    }
    
    public int getBlue()
    {
        return b;
    }
    
    public int getGreen()
    {
        return g;
    }
    
    public void setBlack()
    {
        r = 0;
        g = 0;
        b = 0;
    }
    
    public void setWhite()
    {
        r = 255;
        g= 255;
        b = 255;
    }
    
    public int getColorVal()
    {
        return (r + g + b) / 3;
    }
}
