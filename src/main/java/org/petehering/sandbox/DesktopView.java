package org.petehering.sandbox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB_PRE;
import javax.swing.JComponent;

public class DesktopView extends JComponent implements View
{
    private final Dimension size;
    private final BufferedImage page1;
    private final BufferedImage page2;
    private BufferedImage buffer;
    private DesktopInput input;

    public DesktopView (int width, int height)
    {
        this.size = new Dimension (width, height);
        this.page1 = new BufferedImage (width, height, TYPE_INT_ARGB_PRE);
        this.page2 = new BufferedImage (width, height, TYPE_INT_ARGB_PRE);
        this.buffer = page1;

        this.setSize (size);
        this.setPreferredSize (size);
        this.setMaximumSize (size);
        this.setMinimumSize (size);
    }

    public void setDesktopInput (DesktopInput input)
    {
        if (this.input != null)
        {
            this.removeKeyListener (this.input);
            this.removeMouseListener (this.input);
            this.removeMouseMotionListener (this.input);
        }

        this.input = input;

        if (this.input != null)
        {
            this.addKeyListener (this.input);
            this.addMouseListener (this.input);
            this.addMouseMotionListener (this.input);
        }
    }

    @Override
    public void clear (Color color)
    {
        Graphics2D g = this.getViewGraphics ();
        g.setColor (color);
        g.fillRect (0, 0, size.width, size.height);
    }

    @Override
    public Dimension getViewSize ()
    {
        return size;
    }

    @Override
    public Graphics2D getViewGraphics ()
    {
        return (buffer == page1)
            ? page2.createGraphics ()
            : page1.createGraphics ();
    }

    @Override
    public void present ()
    {
        buffer = (buffer == page1)
            ? page2
            : page1;
        repaint ();
        getToolkit ().sync ();
    }

    @Override
    public void paintComponent (Graphics g)
    {
        g.drawImage (buffer, 0, 0, this);
    }
}
