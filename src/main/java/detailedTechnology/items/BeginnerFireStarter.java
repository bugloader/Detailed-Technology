package detailedTechnology.items;

import detailedTechnology.DetailedTechnology;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.MutableText;
import net.minecraft.util.ActionResult;

public class BeginnerFireStarter extends Item {


    public BeginnerFireStarter(String liquidType) {
        super(new FabricItemSettings().group(DetailedTechnology.ITEM_GROUP).maxCount(1));

    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getPlayer()!=null)
        {
            MutableText blockName = context.getWorld().getBlockState(context.getBlockPos().up()).getBlock().getName();
            Inventory inventory = context.getPlayer().inventory;
            int slot = context.getPlayer().inventory.selectedSlot;
            int number = inventory.getStack(slot).getCount();
            if(blockName.equals(Blocks.OAK_WOOD))
            inventory.getStack(slot).setCount(number);
        }
        return ActionResult.PASS;
    }
}