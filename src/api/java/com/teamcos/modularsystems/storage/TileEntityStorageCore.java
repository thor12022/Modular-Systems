package com.teamcos.modularsystems.storage;

import com.teamcos.modularsystems.interfaces.Locatable;
import net.minecraft.inventory.IInventory;

public interface TileEntityStorageCore extends IInventory, Locatable {
    void sortInventoryAlphabetically();

    void sortInventoryByIndex();

    boolean hasSpecificUpgrade(int upgradeId);

    int getInventoryRows();

    void setInventoryRows(int i);

    void setGuiDisplayName(String displayName);
}
