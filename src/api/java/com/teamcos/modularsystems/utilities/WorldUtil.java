package com.teamcos.modularsystems.utilities;

import com.teamcos.modularsystems.interfaces.Locatable;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class WorldUtil {

    public final static Random random = new Random();

    /**
     * Returns true if tiles are in same location
     * @param tile1 First Tile Entity
     * @param tile2 Second Tile Entity
     * @return
     */
    public static boolean areTilesSame(TileEntity tile1, TileEntity tile2)
    {
        return (tile1.xCoord == tile2.xCoord) && (tile1.yCoord == tile2.yCoord) && (tile1.zCoord == tile2.zCoord);
    }

    /**
     * Returns true if tiles are in same location
     * @param tile1 First Tile Entity
     * @param tile2 Second Tile Entity
     * @return
     */
    public static boolean areTilesSame(Locatable tile1, Locatable tile2)
    {
        return (tile1.getX() == tile2.getX()) && (tile1.getY() == tile2.getY()) && (tile1.getZ() == tile2.getZ());
    }
}
