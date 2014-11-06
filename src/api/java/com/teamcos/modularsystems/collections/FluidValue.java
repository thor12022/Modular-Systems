package com.teamcos.modularsystems.collections;

import net.minecraftforge.fluids.Fluid;

public class FluidValue {
    private final Fluid fluid;
    private final double efficiency;
    private final double speed;
    private final double smelting;
    private final boolean isFuel;
    private final int fuelValue;

    public FluidValue(Fluid fluid) {
        this.fluid = fluid;
        this.efficiency = 0;
        this.speed = 0;
        this.smelting = 0;
        isFuel = false;
        fuelValue = 0;
    }

    public FluidValue(Fluid fluid, double efficiency, double speed, double smelting) {
        this.fluid = fluid;
        this.efficiency = efficiency;
        this.speed = speed;
        this.smelting = smelting;
        isFuel = false;
        fuelValue = 0;
    }

    public FluidValue(Fluid fluid, double efficiency, double speed, double smelting, int fuelValue) {
        this.fluid = fluid;
        this.efficiency = efficiency;
        this.speed = speed;
        this.smelting = smelting;
        this.isFuel = fuelValue > 0;
        this.fuelValue = fuelValue;
    }

    public FluidValue(Fluid fluid, int fuelValue) {
        this.fluid = fluid;
        this.efficiency = 0;
        this.speed = 0;
        this.smelting = 0;
        this.isFuel = fuelValue > 0;
        this.fuelValue = fuelValue;
    }

    public Fluid getFluid() {
        return fluid;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isFuel() {
        return isFuel;
    }

    public int getFuelValue() {
        return fuelValue;
    }

    public double getSmelting() {
        return smelting;
    }
}
