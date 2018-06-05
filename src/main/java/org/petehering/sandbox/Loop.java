package org.petehering.sandbox;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import static java.lang.Math.round;
import static java.lang.System.currentTimeMillis;
import static java.util.Objects.requireNonNull;

public class Loop implements Runnable
{
    private final Game game;
    private final View view;
    private boolean running;
    private final long milliseconds;

    public Loop (Game game, View view)
    {
        this.game = requireNonNull (game);
        this.view = requireNonNull (view);
        
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment ();
        GraphicsDevice dev =env.getDefaultScreenDevice ();
        DisplayMode mode = dev.getDisplayMode ();
        double rate = mode.getRefreshRate ();

        if (rate == DisplayMode.REFRESH_RATE_UNKNOWN)
        {
            rate = 60.0;
        }
        
        milliseconds = round (1000.0 / rate);
    }

    public void start ()
    {
        this.running = true;
        new Thread (this, "loop").start ();
    }

    @Override
    public void run ()
    {
        long time = currentTimeMillis ();
        long accum = 0;

        LOOP:
        while (running)
        {
            long now = currentTimeMillis ();
            long elapsed = now - time;
            accum = accum + elapsed;
            time = now;

            if (elapsed < milliseconds)
            {
                long delay = milliseconds - elapsed;
                
                try
                {
                    Thread.sleep (delay);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace ();
                    break LOOP;
                }
            }
            
            game.update (milliseconds);
            game.render (view);
        }

        game.dispose ();
    }

    public void stop ()
    {
        synchronized (this)
        {
            this.running = false;
        }
    }
}
