package com.teamcos.modularsystems.utilities.block;

public interface MSUpgradeBlock {
    double getEfficiency(int blockCount);
    double getSpeed(int blockCount);
    int getMultiplier(int blockCount);
    boolean isCrafter();
}
