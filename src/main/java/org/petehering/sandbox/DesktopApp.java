package org.petehering.sandbox;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class DesktopApp
{
    private DesktopConfig config;

    public DesktopApp (DesktopConfig config)
    {
        this.config = config;
    }

    public void start ()
    {
        final DesktopView desktopView = new DesktopView (config.width, config.height);
        desktopView.setDesktopInput (config.input);

        final Loop loop = new Loop (config.game, desktopView);

        final JFrame frame = new JFrame (config.title);
        frame.setContentPane (desktopView);
        frame.pack ();
        frame.setResizable (false);
        frame.setLocationRelativeTo (null);
        frame.setDefaultCloseOperation (EXIT_ON_CLOSE);
        frame.addWindowListener (new WindowAdapter ()
        {
            @Override
            public void windowOpened (WindowEvent e)
            {
                frame.requestFocus ();
                desktopView.requestFocusInWindow ();
                loop.start ();
            }

            @Override
            public void windowClosing (WindowEvent e)
            {
                loop.stop ();
            }
        });
        frame.setVisible (true);
    }
}
