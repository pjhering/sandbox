package org.petehering.sandbox;

import java.awt.Point;

public interface Input
{
    public boolean isKeyPressed (int key);

    public boolean isKeyDown (int key);

    public boolean isMousePressed (int button);

    public boolean isMouseDown (int button);

    public Point getMousePoint ();
}
