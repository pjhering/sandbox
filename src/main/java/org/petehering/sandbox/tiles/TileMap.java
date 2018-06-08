package org.petehering.sandbox.tiles;

import static java.util.Objects.requireNonNull;
import static org.petehering.sandbox.Utility.requireGreaterThan;

public class TileMap
{
    public final String tilesetFile;
    public final int rows;
    public final int columns;
    public final int tileWidth;
    public final int tileHeight;
    public final int width;
    public final int height;
    private final Tile[][] tiles;
    
    
    public TileMap (String tilesetFile, int rows, int columns, int tileWidth, int tileHeight)
    {
        this.tilesetFile = requireNonNull (tilesetFile);
        this.rows = requireGreaterThan (0, rows);
        this.columns = requireGreaterThan (0, columns);
        this.tiles = new Tile[rows][columns];
        this.tileWidth = requireGreaterThan (0, tileWidth);
        this.tileHeight = requireGreaterThan (0, tileHeight);
        this.width = tileWidth * columns;
        this.height = tileHeight * rows;
    }
    
    public Tile getTile (int row, int column)
    {
        return tiles[row][column];
    }
    
    public void setTile (int row, int column, Tile tile)
    {
        tiles[row][column] = tile;
    }
}
