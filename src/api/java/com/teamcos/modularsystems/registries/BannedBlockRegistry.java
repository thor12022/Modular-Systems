package com.teamcos.modularsystems.registries;

import java.util.LinkedHashSet;
import java.util.Set;

public class BannedBlockRegistry {
    private final Set<String> bannedBlocks = new LinkedHashSet<String>();

    public void banBlock(String blockName) {
        bannedBlocks.add(blockName);
    }

    public boolean isBanned(String blockName) {
        return bannedBlocks.contains(blockName);
    }
}
