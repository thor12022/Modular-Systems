package com.teamcos.modularsystems.fuelprovider;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemFuelProvider implements FuelProvider {

    private ItemStack fuel;

    public ItemFuelProvider (ItemStack fuel) {
        this.fuel = fuel;
    }

    @Override
    public boolean canProvide() {
        checkFuel();
        return fuel != null && fuel.stackSize > 0 && fuelProvided() > 0;
    }

    @Override
    public double fuelProvided() {
        return GameRegistry.getFuelValue(fuel);
    }

    @Override
    public double consume() {
        ForgeDirection[] dirs = ForgeDirection.values();
        for (ForgeDirection dir : dirs) {

        }
        if (canProvide()) {
            fuel.stackSize--;
            double retVal = fuel == null ? 0 : fuelProvided();
            checkFuel();
            return retVal;
        } else {
            return 0;
        }
    }

    private void checkFuel() {
        if (fuel != null && fuel.stackSize <= 0) {
            fuel = null;
        }
    }

    @Override
    public FuelProviderType type() {
        return null;
    }
}
