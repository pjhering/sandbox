package org.petehering.sandbox;

public interface Screen
{
    public void activate ();

    public int update (long elapsed);

    public void render (View view);

    public void deactivate ();

    public void dispose ();
}
