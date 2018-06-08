package org.petehering.sandbox.tiles;

import static org.petehering.sandbox.Utility.requireGreaterThan;

public class Entity
{
    private float x;
    private float y;
    private float width;
    private float height;
    
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

    float getX ()
    {
        return x;
    }

    void setX (float x)
    {
        this.x = x;
    }

    float getY ()
    {
        return y;
    }

    void setY (float y)
    {
        this.y = y;
    }

    float getWidth ()
    {
        return width;
    }

    void setWidth (float width)
    {
        this.width = requireGreaterThan (0f, width);
    }

    float getHeight ()
    {
        return height;
    }

    void setHeight (float height)
    {
        this.height = requireGreaterThan (0f, height);
    }
}
