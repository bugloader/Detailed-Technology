package detailedTechnology.items;

import detailedTechnology.DetailedTechnology;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class ClaySmallCrucibleWithLiquid extends Item {
    private String liquidType;

    public ClaySmallCrucibleWithLiquid(String liquidType) {
        super(new FabricItemSettings().group(DetailedTechnology.ITEM_GROUP).maxCount(1));
        this.liquidType = liquidType;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().getBlockState(context.getBlockPos().up()).getBlock().getName().equals(Blocks.WATER.getName())
                && context.getPlayer()!=null)
        {
            Inventory inventory = context.getPlayer().inventory;
            int slot = context.getPlayer().inventory.selectedSlot;
            int number = inventory.getStack(slot).getCount();
            switch (liquidType)
            {
                case "copper":
                    inventory.setStack(slot,new ItemStack(DetailedTechnology.copperIngot));
                    break;
                case "tin":
                    inventory.setStack(slot,new ItemStack(DetailedTechnology.tinIngot));
                    break;
                case "bronze":
                    inventory.setStack(slot,new ItemStack(DetailedTechnology.bronzeIngot));
                    break;
            }
            inventory.getStack(slot).setCount(number);
        }
        return ActionResult.PASS;
    }
}