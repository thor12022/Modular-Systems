package com.teamcos.modularsystems.interfaces;

import net.minecraft.world.World;

public interface Locatable {

    World getWorld();
    int getX();
    int getY();
    int getZ();
}
