package com.teamcos.modularsystems.helpers;

import com.teamcos.modularsystems.utilities.tiles.DummyTile;
import com.teamcos.modularsystems.utilities.tiles.TankLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class Test extends TankLogic {
    public void doStuff(int x, int y, int z) {
        if (containsFluid()) {
            for (ForgeDirection dir : LocalBlockCollections.getNotUpDirections()) {
                TileEntity tile = getTileInDirection(dir);
                if (tile instanceof TankLogic) {
                    TankLogic logic = (TankLogic) tile;
                    if (logic.canFill(dir, tank.getFluid().getFluid()) && logic.tank.getFluidAmount() < logic.tank.getCapacity() && logic.tank.getFluidAmount() + drainRate < tank.getFluidAmount()) {
                        FluidStack drained = tank.getFluid().amount < drainRate ? tank.getFluid() : new FluidStack(tank.getFluid().getFluid(), drainRate);
                        logic.fill(dir, drained, true);
                        drain(dir, drained, true);
                        return;
                    }
                }
            }
        }
    }

    public void doOtherStuff() {

        if (getTileInDirection(ForgeDirection.DOWN) instanceof TankLogic) {
            TankLogic otherTank = (TankLogic) getTileInDirection(ForgeDirection.DOWN);
            int capacity = getCapacity(otherTank);
            int drainAmount = drainAmount();
            if (canFill(otherTank) && capacity > 0) {
                FluidStack drained = new FluidStack(tank.getFluid().getFluid(), drainAmount);
                drain(ForgeDirection.UNKNOWN, drained, true);
                otherTank.fill(ForgeDirection.UP, drained, true);
                return;
            }
        }
        for (ForgeDirection dir : LocalBlockCollections.getNeighborDirections()) {
            TileEntity tile = getTileInDirection(dir);
            if (tile instanceof TankLogic) {
                TankLogic otherTank = (TankLogic) tile;
                int capacity = getCapacity(otherTank);
                int drainAmount = drainAmount(otherTank);
                if (canFill(otherTank) && capacity > 0 && drainAmount > 0) {
                    FluidStack drained = new FluidStack(tank.getFluid().getFluid(), drainAmount);
                    otherTank.fill(ForgeDirection.NORTH, drained, true);
                    drain(ForgeDirection.UNKNOWN, drained, true);
                    return;
                }
            }
        }
    }

    public boolean canFill(TankLogic otherTank) {
        return otherTank.canFill(ForgeDirection.UNKNOWN, tank.getFluid().getFluid());
    }

    public static boolean hasCapacity(TankLogic otherTank) {
        return otherTank.tank.getFluidAmount() < otherTank.tank.getCapacity();
    }

    public int drainRate() {
        return Math.max(10000 / tank.getFluid().getFluid().getViscosity(), 5);
    }

    private int drainAmount() {
        return Math.min(drainRate(), tank.getFluidAmount());
    }

    public static int getCapacity(TankLogic otherTank) {
        return otherTank.tank.getCapacity() - otherTank.tank.getFluidAmount();
    }

    public int drainAmount(TankLogic otherTank) {
        return Math.min(drainAmount(), this.tank.getFluidAmount() - otherTank.tank.getFluidAmount());
    }
}
