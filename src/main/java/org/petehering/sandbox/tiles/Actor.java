package org.petehering.sandbox.tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static java.lang.Math.round;
import static org.petehering.sandbox.Utility.requireNonNull;

public abstract class Actor extends Entity
{
    protected Animation[] animations;
    protected int index;
    protected float deltaX;
    protected float deltaY;

    public Actor (Animation[] animations, float width, float height)
    {
        this (animations, 0f, 0f, width, height);
    }

    public Actor (Animation[] animations, float x, float y, float width, float height)
    {
        super (x, y, width, height);
        this.animations = requireNonNull (animations);
        this.index = 0;
        this.animations[0].reset ();
    }
    
    public abstract void act (long elapsed);
    
    public abstract void onExitStage ();
    
    public abstract void onEntityHit (Entity e);
    
    public abstract void onActorHit (Actor a);
    
    public void draw (Graphics2D g, int xOffset, int yOffset)
    {
        BufferedImage _img = this.animations[index].getImage ();
        int _x = round (this.x) - xOffset;
        int _y = round (this.y) - yOffset;
        g.drawImage (_img, _x, _y, null);
    }
    
    public void move (long elapsed)
    {
        this.x += this.deltaX * elapsed;
        this.y += this.deltaY * elapsed;
    }
    
    public Animation getAnimation ()
    {
        return this.animations[index];
    }
    
    public void setAnimation (int i)
    {
        if (0 <= i && i < animations.length)
        {
            this.index = i;
            this.width = this.animations[index].getWidth ();
            this.height = this.animations[index].getHeight ();
            this.animations[index].reset ();
        }
    }
}
