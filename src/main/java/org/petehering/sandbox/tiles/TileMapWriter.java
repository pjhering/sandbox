package org.petehering.sandbox.tiles;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class TileMapWriter
{
    private final TileMap tileMap;
    
    public TileMapWriter (TileMap map)
    {
        this.tileMap = map;
    }
    
    public void write (PrintWriter writer) throws IOException
    {
        writer.printf ("%s %d %d\n", tileMap.tilesetFile, tileMap.rows, tileMap.columns);
        
        for (int row = 0; row < tileMap.rows; row++)
        {
            for (int col = 0; col < tileMap.columns; col++)
            {
                writer.printf ("%-4d", tileMap.getTile (row, col));
            }
            writer.println ();
        }
    }
    
    public void write (File file) throws IOException
    {
        write (new PrintWriter (file));
    }
    
    public void write (String mapFile) throws IOException
    {
        write (new File (mapFile));
    }
}
