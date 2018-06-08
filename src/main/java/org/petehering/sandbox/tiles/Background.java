package org.petehering.sandbox.tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static java.util.Objects.requireNonNull;

public class Background
{
    public final BufferedImage image;
    private final int width;
    private final int height;
    
    public Background (BufferedImage image)
    {
        this.image = requireNonNull (image);
        this.width = image.getWidth ();
        this.height = image.getHeight ();
    }
    
    public void draw (Graphics2D g, int xOffset, int yOffset)
    {
        int x = xOffset % width;
        int y = yOffset % height;
        
        g.drawImage (image, x, y, null);
        
        if (x < 0)
        {
            g.drawImage (image, x + width, y, null);
        }
        else if (x > 0)
        {
            g.drawImage (image, x - width, y, null);
        }
        
        if (y < 0)
        {
            g.drawImage (image, x, y + height, null);
        }
        else if (y > 0)
        {
            g.drawImage (image, x, y - height, null);
        }
    }
}
