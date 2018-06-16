package org.petehering.sandbox;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class DesktopApp
{
    private final DesktopConfig config;

    public DesktopApp ()
    {
        this (new DesktopConfig ());
    }
    
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
        
        if (config.icon != null)
        {
            try (InputStream stream = getClass ().getResourceAsStream (config.icon))
            {
                BufferedImage icon = ImageIO.read (stream);
                frame.setIconImage (icon);
            }
            catch (Exception ex)
            {
                // ignore; use default icon
                ex.printStackTrace ();
            }
        }
        
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
