package org.petehering.sandbox.sprites;

import java.awt.Graphics2D;
import java.awt.Image;
import static java.util.Objects.requireNonNull;

public class ImageSprite implements Drawable
{
    private final Image image;
    private final int x1;
    private final int x2;
    private final int y1;
    private final int y2;

    public ImageSprite (Image image, int x, int y, int width, int height)
    {
        this.image = requireNonNull (image);
        this.x1 = x;
        this.x2 = x + width;
        this.y1 = y;
        this.y2 = y + height;
    }

    @Override
    public void draw (Graphics2D g, int dx1, int dy1, int dx2, int dy2)
    {
        g.drawImage (image, dx1, dy1, dx2, dy2, x1, y1, x2, y2, null);
    }

    public Image getImage ()
    {
        return image;
    }

    public int getX1 ()
    {
        return x1;
    }

    public int getX2 ()
    {
        return x2;
    }

    public int getY1 ()
    {
        return y1;
    }

    public int getY2 ()
    {
        return y2;
    }
    
    public int getWidth ()
    {
        return x2 - x1;
    }
    
    public int getHeight ()
    {
        return y2 - y1;
    }
}
