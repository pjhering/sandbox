package org.petehering.sandbox.tiles;

import static java.lang.Math.round;
import static org.petehering.sandbox.Utility.clamp;
import static org.petehering.sandbox.Utility.requireGreaterThan;

public class Viewport
{
    private final float width;
    private final float height;
    private final int stageWidth;
    private final int stageHeight;
    private float x;
    private float y;
    
    public Viewport (float width, float height, int stageWidth, int stageHeight)
    {
        this.width = requireGreaterThan (0f, width);
        this.height = requireGreaterThan (0f, height);
        this.stageWidth = requireGreaterThan (0, stageWidth);
        this.stageHeight = requireGreaterThan (0, stageHeight);
        this.x = 0f;
        this.y = 0f;
    }
    
    public boolean contains (Entity e)
    {
        return this.x < e.getX () + e.getWidth ()
            && this.y < e.getY () + e.getHeight ()
            && e.getX () < this.x + this.width
            && e.getY () < this.y + this.height;
    }
    
    public void center (Entity e)
    {
        this.x = clamp (e.getX () - (e.getWidth () / 2f), 0f, stageWidth - width);
        this.y = clamp (e.getY () - (e.getHeight () / 2f), 0f, stageHeight - height);
    }
    
    public int getOffsetX ()
    {
        return round (x);
    }
    
    public int getOffsetY ()
    {
        return round (y);
    }

    public float getWidth ()
    {
        return width;
    }

    public float getHeight ()
    {
        return height;
    }

    public int getStageWidth ()
    {
        return stageWidth;
    }

    public int getStageHeight ()
    {
        return stageHeight;
    }

    public float getX ()
    {
        return x;
    }

    public void setX (float x)
    {
        this.x = x;
    }

    public float getY ()
    {
        return y;
    }

    public void setY (float y)
    {
        this.y = y;
    }
}
