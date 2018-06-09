package org.petehering.sandbox.tiles;

import static org.petehering.sandbox.Utility.requireGreaterThan;

public class Entity
{
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    
    public Entity (float width, float height)
    {
        this (0f, 0f, width, height);
    }
    
    public Entity (float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = requireGreaterThan (0f, width);
        this.height = requireGreaterThan (0f, height);
    }
    
    public boolean intersects (Entity that)
    {
        return this.x < that.x + that.width
            && this.y < that.y + that.height
            && that.x < this.x + this.width
            && that.y < this.y + this.height;
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

    public float getWidth ()
    {
        return width;
    }

    public void setWidth (float width)
    {
        this.width = requireGreaterThan (0f, width);
    }

    public float getHeight ()
    {
        return height;
    }

    public void setHeight (float height)
    {
        this.height = requireGreaterThan (0f, height);
    }
}
