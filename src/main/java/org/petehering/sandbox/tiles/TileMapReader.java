package org.petehering.sandbox.tiles;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import javax.imageio.ImageIO;

public class TileMapReader
{
    private static final String SPACES = "\\s+";
    private final TileMap tileMap;
    
    public TileMapReader (InputStream stream) throws IOException
    {
        this.tileMap = read (stream);
    }

    private TileMap read (InputStream in) throws IOException
    {
        InputStreamReader reader = new InputStreamReader (in);
        BufferedReader buffer = new BufferedReader (reader);
        String[] tokens = buffer.readLine ().trim ().split (SPACES);
        TileMap map = null;
        
        try (InputStream stream = getClass ().getResourceAsStream (tokens[0]))
        {
            BufferedImage tileset = ImageIO.read (stream);
            int rows = parseInt (tokens[1]);
            int cols = parseInt (tokens[2]);
            int tileWidth = tileset.getWidth () / cols;
            int tileHeight = tileset.getHeight () / rows;
            map = new TileMap (tokens[0], rows, cols, tileWidth, tileHeight);
            
            // create subimage array
            BufferedImage[] subimg = new BufferedImage[rows * cols];
            int i = 0;
            for (int y = 0; y < rows; y++)
            {
                for (int x = 0; x < cols; x++)
                {
                    subimg[i] = tileset.getSubimage (x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                    i += 1;
                }
            }
            
            // read tilemap data
            String line;
            int y = 0;
            while ((line = buffer.readLine ()) != null)
            {
                tokens = line.trim ().split (SPACES);
                
                for (int x = 0; x < tokens.length; x++)
                {
                    i = parseInt (tokens[x]);
                    map.setTile (
                        y, x,
                        new Tile (
                            i,
                            subimg[i],
                            x * tileWidth,
                            y * tileHeight,
                            tileWidth,
                            tileHeight));
                }
            }
        }
        
        return map;
    }
    
    public TileMap getTileMap ()
    {
        return tileMap;
    }
}
