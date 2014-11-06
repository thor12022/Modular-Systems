package com.teamcos.modularsystems.utilities.tiles;

import com.teamcos.modularsystems.collections.Coord;
import com.teamcos.modularsystems.helpers.LocalBlockCollections;
import com.teamcos.modularsystems.collections.SimpleLocatable;
import com.teamcos.modularsystems.inventory.InventoryUtil;
import com.teamcos.modularsystems.utilities.WorldUtil;
import com.teamcos.modularsystems.utilities.block.DummyIOBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class DummyTile extends ModularTileEntity implements ISidedInventory, MSTile {

    private Coord coreLoc = new Coord(-100, -100, -100);
    private int icon = 1;
    private int coolDown = 80;
    private int slot = 4;
    private int metadata = 0;

    public DummyTile() {}

    public MSCoreTile getCore() {
        TileEntity te;
        if (coreLoc == null) {
            return null;
        } else if ((te = worldObj.getTileEntity(coreLoc.x, coreLoc.y, coreLoc.z)) instanceof FueledRecipeTile) {
            return (FueledRecipeTile) te;
        } else {
            return null;
        }
    }

    public Block getBlock() {
        Block block = Block.getBlockById(this.icon);
        return block == null ? Blocks.cobblestone : block;
    }

    /******************************************************************************************************************
     **************************************************  Tile Methods  ************************************************
     ******************************************************************************************************************/

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);

        coreLoc = Coord.readFromNBT(tagCompound);

        slot = tagCompound.getInteger("Slot");
        icon = tagCompound.getInteger("Icon");
        metadata = tagCompound.getInteger("Meta");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);

        if (coreLoc != null) {
            coreLoc.writeToNBT(tagCompound);
        }

        tagCompound.setInteger("Slot", slot);
        tagCompound.setInteger("Icon", icon);
        tagCompound.setInteger("Meta", metadata);
    }

    @Override
    public void updateEntity() {
        if (getCore() == null) {
            worldObj.setBlock(xCoord, yCoord, zCoord, getBlock());
        } else if(coolDown < 0) {
            if (!worldObj.isRemote && slot == 2 && worldObj.getBlock(xCoord, yCoord, zCoord) instanceof DummyIOBlock) {
                MSCoreTile core = getCore();
                if (core != null) {
                    for (Coord dir : LocalBlockCollections.getAdjacentBlocks()) {
                        TileEntity te = worldObj.getTileEntity(xCoord + dir.x, yCoord + dir.y, zCoord + dir.z);
                        if (te instanceof IInventory) {
                            if (te instanceof DummyTile) {
                                DummyTile dummy = (DummyTile) te;
                                if (dummy.getCore() == null || WorldUtil.areTilesSame(dummy.getCore(), this.getCore())) {
                                    continue;
                                }
                            } else if (WorldUtil.areTilesSame(new SimpleLocatable(te), core)) {
                                continue;
                            }

                            if (core.getStackInSlot(2) != null) {
                                InventoryUtil.moveItemInto(getCore(), 2, te, -1, 64, ForgeDirection.UP, true, true);
                            }
                        }
                    }
                }
            }
            coolDown = 80;
        }
        coolDown--;
    }

    /*****************************************************************************************************************
     *********************************************** Inventory methods ***********************************************
     *****************************************************************************************************************/

    @Override
    public int getSizeInventory() {
        MSCoreTile core = getCore();
        return core == null ? 0 : core.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        MSCoreTile core = getCore();
        return core == null ? null : core.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        MSCoreTile core = getCore();
        return core == null ? null : core.decrStackSize(i, j);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        MSCoreTile core = getCore();
        return core == null ? null : core.getStackInSlotOnClosing(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        MSCoreTile core = getCore();
        if(core != null) core.setInventorySlotContents(i, itemstack);
    }

    @Override
    public int getInventoryStackLimit() {
        MSCoreTile core = getCore();
        return core == null ? 0 : core.getInventoryStackLimit();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return getCore() != null;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        MSCoreTile core = getCore();
        return core == null ? false : core.isItemValidForSlot(i, itemstack);
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        MSCoreTile core = getCore();
        return core == null ? false : core.canInsertItem(i, itemstack, j);
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        MSCoreTile core = getCore();
        return core == null
                ? false
                : slot <= 3
                    ? core.canExtractItem(i, itemstack, slot)
                    : core.canExtractItem(i, itemstack, j);
    }

    @Override
    public String getInventoryName() {
        MSCoreTile core = getCore();
        return core == null ? "" : core.getInventoryName();
    }

    @Override
    public boolean hasCustomInventoryName() {
        MSCoreTile core = getCore();
        return core == null ? false : core.hasCustomInventoryName();
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        MSCoreTile core = getCore();
        return core == null
                ? new int[]{0}
                : slot <= 3
                    ? core.getAccessibleSlotsFromSide(slot)
                    : core.getAccessibleSlotsFromSide(var1);
    }

    /******************************************************************************************************************
     *************************************************  Helper Methods  ***********************************************
     ******************************************************************************************************************/

    public void setBlock(int id) {
        this.icon = id;
    }

    public void setMetadata(int metadata) {
        this.metadata = metadata;
    }

    public int getMetadata() {
        return metadata;
    }

    public void setCore(FueledRecipeTile core) {
        this.coreLoc = new Coord(core);
    }

    public void unsetCore() {
        this.coreLoc = null;
    }

    public void nextSlot() {
        if (slot == 2) {
            slot = 0;
        } else if (slot == 4) {
            slot = 1;
        } else {
            slot += 1;
        }
    }

    public String getSlotName() {
        switch (slot) {
            case 0: return "Fuel";
            case 1: return "Input";
            case 2: return "Output";
            default: return "Fuel";
        }
    }

    public String getSlotNameForChat() {
        switch (slot) {
        case 0:
            return EnumChatFormatting.YELLOW + "Mode: Fuel";
        case 1:
            return EnumChatFormatting.GREEN + "Mode: Input";
        case 2:
            return EnumChatFormatting.RED + "Mode: Output";
        default:
            return EnumChatFormatting.YELLOW + "Mode: Fuel";
        }
    }

    @Override
    public World getWorld() {
        return worldObj;
    }

    @Override
    public int getX() {
        return xCoord;
    }

    @Override
    public int getY() {
        return yCoord;
    }

    @Override
    public int getZ() {
        return zCoord;
    }
}
