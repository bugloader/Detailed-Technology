package detailedTechnology.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;

public class TinBlock extends FacingBlock {
    public TinBlock()
    {
        super(FabricBlockSettings.of(Material.METAL, MaterialColor.STONE)
                .strength(3f,5f)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
    }

}
