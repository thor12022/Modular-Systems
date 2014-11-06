package com.teamcos.modularsystems.registries;

import com.teamcos.modularsystems.collections.FluidValue;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.LinkedHashMap;
import java.util.Map;

public class LiquidRegistry {

    private Map<Fluid, FluidValue> fluids = new LinkedHashMap();

    public LiquidRegistry() {}

    public void registerFluid(FluidValue value) {
        if (value.getFluid() != null) {
            fluids.put(value.getFluid(), value);
        }
    }

    public void registerFluid(Fluid fluid, double efficiency, double speed, double smelting, int fuelValue) {
        if (fluid != null) {
            registerFluid(new FluidValue(fluid, efficiency, speed, smelting, fuelValue));
        }
    }

    public void registerFluid(Fluid fluid, double efficiency, double speed, double smelting) {
        if (fluid != null) {
            registerFluid(new FluidValue(fluid, efficiency, speed, smelting));
        }
    }

    public void registerFluid(Fluid fluid, int fuelValue) {
        if (fluid != null) {
            registerFluid(new FluidValue(fluid, fuelValue));
        }
    }

    public void registerFluid(Fluid fluid) {
        if (fluid != null) {
            registerFluid(new FluidValue(fluid));
        }
    }

    public FluidValue getFluid(Fluid fluid) {
        return fluids.get(fluid);
    }

    public FluidValue getFluid(FluidStack fluid) {
        if (fluid != null) {
            Fluid f = fluid.getFluid();
            return fluids.get(f);
        } else {
            return null;
        }
    }

    public int getFuelValue(Fluid fluid) {
        FluidValue f = getFluid(fluid);
        return f == null ? 0 : f.getFuelValue();
    }

    public int getFuelValue(FluidStack fluid) {
        return fluid == null ? 0 : getFuelValue(fluid.getFluid());
    }
}
