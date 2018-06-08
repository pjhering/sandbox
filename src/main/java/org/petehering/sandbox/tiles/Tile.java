package org.petehering.sandbox.tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static java.util.Objects.requireNonNull;
import static org.petehering.sandbox.Utility.requireGreaterThan;

public class Tile
{
    public final int id;
    public final BufferedImage image;
    public final int x;
    public final int y;
    public final int width;
    public final int height;
    
    public Tile (int id, BufferedImage image, int x, int y, int width, int height)
    {
        this.id = requireGreaterThan (0, id);
        this.image = requireNonNull (image);
        this.x = requireGreaterThan (0, x);
        this.y = requireGreaterThan (0, y);
        this.width = requireGreaterThan (0, width);
        this.height = requireGreaterThan (0, height);
    }
    
    public void draw (Graphics2D g, int xOffset, int yOffset)
    {
        g.drawImage (image, x - xOffset, y - yOffset, width, height, null);
    }
}
