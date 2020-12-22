package detailedTechnology.blocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;

public class CopperOreBlock extends FacingBlock{
    public CopperOreBlock()
    {
        super(FabricBlockSettings.of(Material.METAL, MaterialColor.STONE)
                .strength(1.5f,2f)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
    }

}
