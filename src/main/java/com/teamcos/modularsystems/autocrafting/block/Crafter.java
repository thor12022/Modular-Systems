package com.teamcos.modularsystems.autocrafting.block;

import com.teamcos.modularsystems.utilities.block.DummyBlock;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class Crafter extends DummyBlock {
    public Crafter(CreativeTabs tab, Material material, boolean inTab) {
        super(tab, material, inTab);
    }

    @Override
    public boolean isCrafter() {
        return true;
    }
}
