package org.petehering.sandbox.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import static java.util.Objects.requireNonNull;

public class TextSprite implements Drawable
{
    private String text;
    private final Font font;
    private final Color color;

    public TextSprite (String text, Color color, Font font)
    {
        this.text = requireNonNull (text);
        this.color = requireNonNull (color);
        this.font = requireNonNull (font);
    }

    @Override
    public void draw (Graphics2D g, int x1, int y1, int x2, int y2)
    {
        g.setColor (color);
        g.setFont (font);
        g.drawString (text, x1, y2);
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = requireNonNull (text);
    }

    public Font getFont ()
    {
        return font;
    }

    public Color getColor ()
    {
        return color;
    }
}
