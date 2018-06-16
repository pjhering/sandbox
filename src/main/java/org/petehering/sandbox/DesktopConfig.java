package org.petehering.sandbox;

public class DesktopConfig
{
    public String title = "Sandbox Game";
    public int width = 800;
    public int height = 480;
    public DesktopInput input = null;
    public Game game;
    public String icon = "/sandbox.png";
    
    public DesktopConfig (){}
    
    public DesktopConfig (Game game)
    {
        this.game = game;
        this.title = game.getClass ().getSimpleName ();
    }
    
    public DesktopConfig (Game game, int width, int height)
    {
        this.game = game;
        this.title = game.getClass ().getSimpleName ();
        this.width = width;
        this.height = height;
    }
    
    public DesktopConfig (Game game, DesktopInput input)
    {
        this (game);
        this.input = input;
    }
    
    public DesktopConfig (Game game, DesktopInput input, int width, int height)
    {
        this (game, width, height);
        this.input = input;
    }
}
