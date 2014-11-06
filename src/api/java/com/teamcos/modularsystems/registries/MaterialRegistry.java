package com.teamcos.modularsystems.registries;

import net.minecraft.block.material.Material;

import java.util.LinkedHashMap;
import java.util.Map;

public class MaterialRegistry {

    private Map<String, Material> materials = new LinkedHashMap<String, Material>();

    public MaterialRegistry() {}

    public void registerMaterial(String name, Material material) {
        materials.put(name, material);
    }

    public Material retrieveMaterial(String name) {
        return materials.get(name);
    }
}
