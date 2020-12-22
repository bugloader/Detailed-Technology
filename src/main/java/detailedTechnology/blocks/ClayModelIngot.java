package detailedTechnology.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ClayModelIngot extends HorizontalFacingBlock {
    private static final VoxelShape SHAPE1 =
            Block.createCuboidShape(0, 0, 4, 16, 4, 12),
    SHAPE2 = Block.createCuboidShape(4, 0, 0, 12, 4, 16);
    public ClayModelIngot()
    {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.STONE)
                .strength(2f,3f)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        switch(dir) {
            case NORTH:
                return SHAPE2;
            case SOUTH:
                return SHAPE2;
            default:
                return SHAPE1;
        }
    }


    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }
}
