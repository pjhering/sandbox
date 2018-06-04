package org.petehering.sandbox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

public interface View
{
    public Dimension getViewSize ();

    public Graphics2D getViewGraphics ();

    public void present ();

    public void clear (Color color);
}
