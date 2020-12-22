package detailedTechnology.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;

public class CopperBlock extends FacingBlock {
    public CopperBlock()
    {
        super(FabricBlockSettings.of(Material.METAL, MaterialColor.STONE)
                .strength(3f,5f)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    }

}
