package com.teamcos.modularsystems.utilities.tiles;

import net.minecraft.block.Block;
import net.minecraft.inventory.ISidedInventory;

public interface MSCoreTile extends MSTile, ISidedInventory {
    Block getOverlay();

    Block getDummyBlock();

    boolean updateMultiblock();

    void setDirty();
}
