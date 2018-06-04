package org.petehering.sandbox.sprites;

import java.awt.Graphics2D;

public final class NullSprite implements Drawable
{
    public static final NullSprite INSTANCE = new NullSprite ();

    private NullSprite ()
    {
    }

    @Override
    public void draw (Graphics2D g, int x1, int y1, int x2, int y2)
    {
    }
}
