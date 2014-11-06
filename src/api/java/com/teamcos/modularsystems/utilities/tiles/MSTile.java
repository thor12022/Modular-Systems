package com.teamcos.modularsystems.utilities.tiles;

import com.teamcos.modularsystems.interfaces.Locatable;
import net.minecraft.block.Block;

public interface MSTile extends Locatable {
    MSCoreTile getCore();
    void setCore(FueledRecipeTile tile);
    Block getBlockType();
}
