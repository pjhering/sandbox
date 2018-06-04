package org.petehering.sandbox;

public interface Game
{
    public void update (long elapsed);

    public void render (View view);

    public void dispose ();
}
