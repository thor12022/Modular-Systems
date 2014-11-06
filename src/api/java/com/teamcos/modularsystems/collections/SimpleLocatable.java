package com.teamcos.modularsystems.collections;

import com.teamcos.modularsystems.interfaces.Locatable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SimpleLocatable implements Locatable {

    private final int x;
    private final int y;
    private final int z;
    private final World world;

    public SimpleLocatable(TileEntity tile) {
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;
        this.world = tile.getWorldObj();
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getZ() {
        return 0;
    }
}
