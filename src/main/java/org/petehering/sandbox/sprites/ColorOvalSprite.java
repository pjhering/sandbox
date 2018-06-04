package org.petehering.sandbox.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

public class ColorOvalSprite implements Drawable
{
    private final Color strokeColor;
    private final Color fillColor;

    public ColorOvalSprite (Color stroke, Color fill)
    {
        this.strokeColor = stroke;
        this.fillColor = fill;
    }

    @Override
    public void draw (Graphics2D g, int x1, int y1, int x2, int y2)
    {
        int width = x2 - x1;
        int height = y2 - y1;

        if (fillColor != null)
        {
            g.setColor (fillColor);
            g.fillOval (x1, y1, width, height);
        }

        if (strokeColor != null)
        {
            g.setColor (strokeColor);
            g.drawOval (x1, y1, width, height);
        }
    }
}
