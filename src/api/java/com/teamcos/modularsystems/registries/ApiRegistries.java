package com.teamcos.modularsystems.registries;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.FluidRegistry;

public abstract class ApiRegistries {

    public static final LiquidRegistry liquidRegistry = new LiquidRegistry();
    public static final BannedBlockRegistry bannedFurnaceBlocks = new BannedBlockRegistry();
    public static final BannedBlockRegistry bannedProcessorBlocks = new BannedBlockRegistry();
    public static final MaterialRegistry materialRegistry = new MaterialRegistry();

    static {
        liquidRegistry.registerFluid(FluidRegistry.LAVA, 0.5, 0.5, 0.5, 16000);
        liquidRegistry.registerFluid(FluidRegistry.WATER, 0, 0, 0);
        liquidRegistry.registerFluid(FluidRegistry.getFluid("oil"), 0, 0, 0, 2000);
        liquidRegistry.registerFluid(FluidRegistry.getFluid("fuel"), 0, 0, 0, 22000);
        liquidRegistry.registerFluid(FluidRegistry.getFluid("rocket_fuel"), 0, 0, 0, 600);
        liquidRegistry.registerFluid(FluidRegistry.getFluid("fire_water"), 800);
        liquidRegistry.registerFluid(FluidRegistry.getFluid("bioethanol"), 20000);
        liquidRegistry.registerFluid(FluidRegistry.getFluid("biofuel"), 20000);
        liquidRegistry.registerFluid(FluidRegistry.getFluid("redstone"), 8000);
        liquidRegistry.registerFluid(FluidRegistry.getFluid("glowstone"), 10000);
        liquidRegistry.registerFluid(FluidRegistry.getFluid("ender"), 12000);
        liquidRegistry.registerFluid(FluidRegistry.getFluid("pyrotheum"), 12000);
        materialRegistry.registerMaterial("air", Material.air);
        materialRegistry.registerMaterial("grass", Material.grass);
        materialRegistry.registerMaterial("ground", Material.ground);
        materialRegistry.registerMaterial("wood", Material.wood);
        materialRegistry.registerMaterial("rock", Material.rock);
        materialRegistry.registerMaterial("iron", Material.iron);
        materialRegistry.registerMaterial("piston", Material.piston);
        materialRegistry.registerMaterial("web", Material.web);
        materialRegistry.registerMaterial("cake", Material.cake);
        materialRegistry.registerMaterial("portal", Material.portal);
        materialRegistry.registerMaterial("dragonEgg", Material.dragonEgg);
        materialRegistry.registerMaterial("gourd", Material.gourd);
        materialRegistry.registerMaterial("clay", Material.clay);
        materialRegistry.registerMaterial("cactus", Material.cactus);
        materialRegistry.registerMaterial("craftedSnow", Material.craftedSnow);
        materialRegistry.registerMaterial("anvil", Material.anvil);
        materialRegistry.registerMaterial("water", Material.water);
        materialRegistry.registerMaterial("lava", Material.lava);
        materialRegistry.registerMaterial("leaves", Material.leaves);
        materialRegistry.registerMaterial("plants", Material.plants);
        materialRegistry.registerMaterial("vine", Material.vine);
        materialRegistry.registerMaterial("sponge", Material.sponge);
        materialRegistry.registerMaterial("cloth", Material.cloth);
        materialRegistry.registerMaterial("fire", Material.fire);
        materialRegistry.registerMaterial("sand", Material.sand);
        materialRegistry.registerMaterial("circuits", Material.circuits);
        materialRegistry.registerMaterial("carpet", Material.carpet);
        materialRegistry.registerMaterial("glass", Material.glass);
        materialRegistry.registerMaterial("redstoneLight", Material.redstoneLight);
        materialRegistry.registerMaterial("tnt", Material.tnt);
        materialRegistry.registerMaterial("coral", Material.coral);
        materialRegistry.registerMaterial("ice", Material.ice);
        materialRegistry.registerMaterial("packedIce", Material.packedIce);
        materialRegistry.registerMaterial("snow", Material.snow);
    }
}
