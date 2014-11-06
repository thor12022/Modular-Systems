package com.teamcos.modularsystems.renderers;

import com.teamcos.modularsystems.manager.ApiBlockManager;
import com.teamcos.modularsystems.utilities.tiles.DummyTile;
import com.teamcos.modularsystems.utilities.tiles.MSCoreTile;
import com.teamcos.modularsystems.utilities.tiles.MSTile;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class ApiBlockRenderer implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID,
                                     RenderBlocks renderer) {

        render3DInventory(block, metadata, modelID, renderer);
    }

    public static void render3DInventory(Block block, int metadata, int modelID, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;

        block.setBlockBoundsForItemRender();
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, getOveryLay(block, renderer));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, getOveryLay(block, renderer));

        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, getOveryLay(block, renderer));

        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, getOveryLay(block, renderer));

        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(-1F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, getOveryLay(block, renderer));

        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, 5));
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, getOveryLay(block, renderer));

        tessellator.draw();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    private static IIcon getOveryLay(Block block, RenderBlocks renderer) {
        IIcon output;

        if(block == ApiBlockManager.dummyIOBlock || block == ApiBlockManager.dummyBlock) {
            output = renderer.getBlockIconFromSideAndMetadata(ApiBlockManager.furnaceOverlay, 0, 0);
        } else {
            output = renderer.getBlockIconFromSideAndMetadata(block, 0, 0);
        }

        return output;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
                                    Block block, int modelId, RenderBlocks renderer) {
        TileEntity te = world.getTileEntity(x, y, z);

        if (te instanceof MSTile) {
            MSTile mst = (MSTile) te;
            MSCoreTile core = mst.getCore();
            if (core != null) {
                if (ApiRenderers.renderPass == 0) {
                    return doStandardRender(world, block, x, y, z, renderer);
                } else {
                    return renderer.renderStandardBlock(core.getOverlay(), x, y, z);
                }
            } else {
                return doStandardRender(world, block, x, y, z, renderer);
            }
        } else {
            return renderer.renderStandardBlock(block, x, y, z);
        }
    }

    private boolean doStandardRender(IBlockAccess world1, Block block, int x, int y, int z, RenderBlocks renderer) {
        if (block == ApiBlockManager.dummyIOBlock) {
            renderer.renderBlockUsingTexture(Blocks.dispenser, x, y, z, Blocks.dispenser.getIcon(1, 1));
            return true;
        } else if (block == ApiBlockManager.dummyBlock) {
            DummyTile dummy = (DummyTile) world1.getTileEntity(x, y, z);
            renderer.renderBlockUsingTexture(dummy.getBlock(), x, y, z, dummy.getBlock().getIcon(0, dummy.getMetadata()));
            return true;
        } else {
            int i = 0;
            return false;
        }
    }

    @Override
    public int getRenderId() {
        return ApiRenderers.apiDummyRenderType;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

}
