package org.petehering.sandbox.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class SpriteSheet
{
    private final BufferedImage image;

    public SpriteSheet (BufferedImage image)
    {
        this.image = image;
    }

    public SpriteSheet (String path) throws IOException
    {
        URL url = getClass ().getResource (path);
        image = ImageIO.read (url);
    }

    public ImageSprite imageSprite (int x, int y, int width, int height)
    {
        return new ImageSprite (image.getSubimage (x, y, width, height), 0, 0, width, height);
    }

    public ImageSprite[] imageSpriteArray (int x, int y, int width, int height, int cols, int rows)
    {
        ImageSprite[] sprites = new ImageSprite[cols * rows];
        int w = width / cols;
        int h = height / rows;
        int i = 0;

        for (int r = 0; r < rows; r++)
        {
            int sy = y + (r * w);

            for (int c = 0; c < cols; c++)
            {
                int sx = x + (c * h);
                sprites[i] = imageSprite (sx, sy, w, h);
                i++;
            }
        }

        return sprites;
    }

    public AnimatedSprite animatedSprite (int x, int y, int width, int height, int cols, int rows, long mpf)
    {
        ImageSprite[] frames = imageSpriteArray (x, y, width, height, cols, rows);
        return new AnimatedSprite (frames, mpf);
    }
}
