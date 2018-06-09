package org.petehering.sandbox.tiles;

import java.awt.Graphics2D;
import java.util.List;
import static java.util.Objects.requireNonNull;
import java.util.TreeMap;

public class Stage
{
    // stage dimensions may exceed viewport
    private int width;
    private int height;
    
    // background used to clear the screen
    private Background background;
    
    // allows screen to follow an entity
    private Viewport viewport;
    
    private TileMap tileMap;//TODO
    
    // non-mobile entities that may collide with actors
    private List<Entity> entities;//TODO
    
    // mobile actors that may collide and can be drawn
    private TreeMap<Integer, List<Actor>> layers;
    
    // the entity to focus on
    private Entity center;
    
    public Stage (int width, int height, float viewWidth, float viewHeight)
    {
        this (width, height, new Viewport (viewWidth, viewHeight, width, height));
    }
    
    public Stage (int width, int height, Viewport viewport)
    {
        this.width = width;
        this.height = height;
        this.viewport = requireNonNull (viewport);
        this.layers = new TreeMap<> ();
    }
    
    public void setBackground (Background bg)
    {
        this.background = bg;
    }
    
    public void setCenter (Entity e)
    {
        this.center = e;
    }
    
    public void draw (Graphics2D g)
    {
        if (background != null)
        {
            background.draw (
                g,
                viewport.getOffsetX (),
                viewport.getOffsetY ());
        }
        
        layers.values ()
            .forEach ((layer)
                -> layer.forEach ((actor) ->
                {
                    if (viewport.contains (actor))
                    {
                        actor.draw (
                            g,
                            viewport.getOffsetX (),
                            viewport.getOffsetY ());
                    }
                }));
    }
    
    public void update (long elapsed)
    {
        layers.values ()
            .forEach ((layer)
                -> layer.forEach ((Actor actor)
                    -> {
                    actor.getAnimation ().update (elapsed);
                    actor.act (elapsed);
                }));
        
        detectExitStage ();
        detectTileCollisons ();
        detectLayerCollisions ();
        
        if (center != null)
        {
            viewport.center (center);
        }
    }

    private void detectExitStage ()//TODO
    {
    }

    private void detectTileCollisons ()//TODO
    {
    }

    private void detectLayerCollisions ()//TODO
    {
        // first detect entity collisons
        // second detect actor collisons
    }
}
