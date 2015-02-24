package elec332.compactarmour;

import elec332.compactarmour.main.CompactArmour;
import elec332.core.main.ElecCTab;
import elec332.core.util.items.baseItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Elec332 on 22-2-2015.
 */
public class ArmourStorageItem extends baseItem {
    public ArmourStorageItem(){
        super("ArmourSwitcher", ElecCTab.ElecTab, CompactArmour.ModID);
    }

    String DATA_ID = "armourData";

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        return SwitchHandler.Switch(itemStack, player);
    }
}
