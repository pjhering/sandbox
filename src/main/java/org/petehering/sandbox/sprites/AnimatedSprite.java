package org.petehering.sandbox.sprites;

import java.awt.Graphics2D;
import static java.lang.System.currentTimeMillis;
import static org.petehering.sandbox.Utility.requireGreaterThan;
import static org.petehering.sandbox.Utility.requireNonNull;

public class AnimatedSprite implements Drawable
{
    private final Drawable[] frames;
    private final long millisPerFrame;
    private int index;
    private long start;
    private long elapsed;
    private boolean playedOnce;

    public AnimatedSprite (Drawable[] frames, long mpf)
    {
        this.frames = requireNonNull (frames);
        this.millisPerFrame = requireGreaterThan (0L, mpf);
        playedOnce = false;
        index = 0;
        elapsed = 0;
        start = currentTimeMillis ();
    }

    public void reset ()
    {
        playedOnce = false;
        index = 0;
        elapsed = 0;
        start = currentTimeMillis ();
    }
    
    public boolean hasPlayedOnce ()
    {
        return playedOnce;
    }

    @Override
    public void draw (Graphics2D g, int x1, int y1, int x2, int y2)
    {
        long now = currentTimeMillis ();
        elapsed += now - start;
        start = now;

        while (elapsed > millisPerFrame)
        {
            index += 1;
            elapsed -= millisPerFrame;
            
            if (index >= frames.length)
            {
                playedOnce = true;
                index = 0;
            }
        }

        frames[index].draw (g, x1, y1, x2, y2);
    }
}
