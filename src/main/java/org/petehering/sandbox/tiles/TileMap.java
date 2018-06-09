package org.petehering.sandbox.tiles;

import java.awt.Graphics2D;
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
    private int firstRow;
    private int lastRow;
    private int firstColumn;
    private int lastColumn;
    
    
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

    public void setRange (int firstRow, int firstColumn, int lastRow, int lastColumn)
    {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.firstColumn = firstColumn;
        this.lastColumn = lastColumn;
    }

    public void draw (Graphics2D g, int offsetX, int offsetY)
    {
        for (int row = firstRow; row <= lastRow; row++)
        {
            for (int col = firstColumn; col <= lastColumn; col++)
            {
                tiles[row][col].draw (g, offsetX, offsetY);
            }
        }
    }
}
