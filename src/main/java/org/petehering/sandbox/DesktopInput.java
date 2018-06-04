package org.petehering.sandbox;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public abstract class DesktopInput implements Input, KeyListener, MouseInputListener
{
    public static final int LEFT_BUTTON = 0;
    public static final int CENTER_BUTTON = 1;
    public static final int RIGHT_BUTTON = 2;
    public static final int BUTTON_COUNT = 3;
    private Point mousePoint = new Point ();
    private final boolean[] currentButtons = new boolean[BUTTON_COUNT];
    private final boolean[] previousButtons = new boolean[BUTTON_COUNT];
    protected final int KEY_COUNT;
    protected final boolean[] previousKeys;
    protected final boolean[] currentKeys;

    public DesktopInput (int keyCount)
    {
        this.KEY_COUNT = keyCount;
        this.previousKeys = new boolean[keyCount];
        this.currentKeys = new boolean[keyCount];
    }

    public abstract int keyToIndex (int keyCode);

    public void update ()
    {
        updateKeys ();
        updateButtons ();
    }

    private void updateKeys ()
    {
        synchronized (currentKeys)
        {
            for (int i = 0; i < KEY_COUNT; i += 1)
            {
                previousKeys[i] = currentKeys[i];
            }
        }
    }

    private void updateButtons ()
    {
        synchronized (currentButtons)
        {
            for (int i = 0; i < BUTTON_COUNT; i += 1)
            {
                previousButtons[i] = currentButtons[i];
            }
        }
    }

    @Override
    public Point getMousePoint ()
    {
        return mousePoint;
    }

    public boolean isMousePressed (int button)
    {
        return 0 <= button
            && button <= BUTTON_COUNT
            && currentButtons[button]
            && !previousButtons[button];
    }

    public boolean isMouseDown (int button)
    {
        return 0 <= button
            && button <= BUTTON_COUNT
            && currentButtons[button];
    }

    @Override
    public void mousePressed (MouseEvent e)
    {
        button (e.getButton (), true);
    }

    @Override
    public void mouseReleased (MouseEvent e)
    {
        button (e.getButton (), false);
    }

    private void button (int button, boolean value)
    {
        if (0 <= button && button < BUTTON_COUNT)
        {
            currentButtons[button] = value;
        }
    }

    @Override
    public void mouseDragged (MouseEvent e)
    {
        this.mousePoint = e.getPoint ();
    }

    @Override
    public void mouseMoved (MouseEvent e)
    {
        this.mousePoint = e.getPoint ();
    }

    @Override
    public void mouseClicked (MouseEvent e)
    {
    }

    @Override
    public void mouseEntered (MouseEvent e)
    {
    }

    @Override
    public void mouseExited (MouseEvent e)
    {
    }

    @Override
    public boolean isKeyPressed (int key)
    {
        return 0 <= key
            && key < KEY_COUNT
            && currentKeys[key]
            && !previousKeys[key];
    }

    @Override
    public boolean isKeyDown (int key)
    {
        return 0 <= key
            && key < KEY_COUNT
            && currentKeys[key]
            && !previousKeys[key];
    }
    
    private void setKey (int index, boolean value)
    {
        if (0 <= index && index < currentKeys.length)
        {
            currentKeys[index] = value;
        }
    }

    @Override
    public void keyPressed (KeyEvent e)
    {
        setKey (keyToIndex (e.getKeyCode ()), true);
    }

    @Override
    public void keyReleased (KeyEvent e)
    {
        setKey (keyToIndex (e.getKeyCode ()), false);
    }

    @Override
    public void keyTyped (KeyEvent e)
    {
    }
}
