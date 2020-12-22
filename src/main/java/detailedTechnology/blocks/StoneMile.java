package detailedTechnology.blocks;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.blockEntity.StoneMileEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class StoneMile extends HorizontalFacingBlock implements BlockEntityProvider {
    private static final VoxelShape SHAPE =
            Block.createCuboidShape(0, 0, 0, 16, 12, 16);
    private int workingTime = 0;
    public StoneMile()
    {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.STONE)
                .strength(2f,3f)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new StoneMileEntity();
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            //If your block class does not extend BlockWithEntity, it needs to implement create ScreenHandlerFactory.
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    //drop inside items when break
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof StoneMileEntity) {
                ItemScatterer.spawn(world, pos, (StoneMileEntity)blockEntity);
                // update comparators
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Nullable
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory)blockEntity : null;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos,
                                     boolean notify) {
        if(block.getClass().equals(StoneMileRunner.class))
        {
            System.out.println(workingTime);
            if(workingTime++>=2)
            {
                workingTime=0;
                mile((Inventory)world.getBlockEntity(pos));
            }
        }
    }

    private boolean isMileable(Inventory inventory)
    {
        ItemStack stack1 = inventory.getStack(0),
                stack2 = inventory.getStack(1);
        boolean slot2IsEmpty = stack2.getCount()==0;
        if(stack1.getCount()==0||stack2.getCount()>62)
        {
            return false;
        }
        System.out.println("a1");
        String itemName = stack1.getName().getString();
        System.out.println(itemName);
        System.out.println(stack2.getName().getString());
        switch (itemName) {
            case "block.dt.copper_ore":
                return slot2IsEmpty||stack2.getName().getString().equals("item.dt.copper_sharp");
            case "block.dt.tin_ore":
                return slot2IsEmpty||stack2.getName().getString().equals("item.dt.tin_sharp");
            case "Iron Ore":
                return slot2IsEmpty||stack2.getName().getString().equals("item.dt.iron_sharp");
        }
        return false;
    }

    public void mile(Inventory inventory)
    {
        if(isMileable(inventory))
        {
            System.out.println("a2");
            ItemStack itemStack1 = inventory.getStack(0);
            String itemName = itemStack1.getName().getString();
            itemStack1.setCount(itemStack1.getCount()-1);
           if(itemName.equals("block.dt.copper_ore"))
           {
               System.out.println("c");
               if(inventory.getStack(1).getCount()==0)
               {
                   inventory.setStack(1,new ItemStack(DetailedTechnology.copperSharp));
                   inventory.getStack(1).setCount(2);
               }
               else
               {
                   inventory.getStack(1).setCount(inventory.getStack(1).getCount()+2);
               }
           }else if(itemName.equals("block.dt.tin_ore"))
           {
               System.out.println("t");
               if(inventory.getStack(1).getCount()==0)
               {
                   inventory.setStack(1,new ItemStack(DetailedTechnology.tinSharp));
                   inventory.getStack(1).setCount(2);
               }
               else
               {
                   inventory.getStack(1).setCount(inventory.getStack(1).getCount()+2);
               }
           }else if(itemName.equals("Iron Ore"))
           {
               System.out.println("i");
               if(inventory.getStack(1).getCount()==0)
               {
                   inventory.setStack(1,new ItemStack(DetailedTechnology.ironSharp));
                   inventory.getStack(1).setCount(2);
               }
               else
               {
                   inventory.getStack(1).setCount(inventory.getStack(1).getCount()+2);
               }
           }
        }
    }
}