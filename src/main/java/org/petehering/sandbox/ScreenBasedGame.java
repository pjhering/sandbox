package org.petehering.sandbox;

import static java.util.Objects.requireNonNull;

public class ScreenBasedGame implements Game
{
    private final Screen[] screens;
    private int current;

    public ScreenBasedGame (Screen[] screens)
    {
        this.screens = requireNonNull (screens);
        this.current = 0;

        screens[current].activate ();
    }

    @Override
    public void update (long elapsed)
    {
        int next = screens[current].update (elapsed);

        if (next != current && 0 <= next && next < screens.length)
        {
            screens[current].deactivate ();
            current = next;
            screens[current].activate ();
        }
    }

    @Override
    public void render (View view)
    {
        screens[current].render (view);
    }

    @Override
    public void dispose ()
    {
        screens[current].deactivate ();

        for (Screen s : screens)
        {
            s.dispose ();
        }

        System.exit (0);
    }
}
