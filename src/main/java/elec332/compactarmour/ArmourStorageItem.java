package elec332.compactarmour;

import elec332.compactarmour.main.CompactArmour;
import elec332.core.main.ElecCTab;
import elec332.core.util.items.baseItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Elec332 on 22-2-2015.
 */
public class ArmourStorageItem extends baseItem {
    public ArmourStorageItem(){
        super("ArmourSwitcher", ElecCTab.ElecTab, CompactArmour.ModName);
        setMaxStackSize(1);
    }

    String DATA_ID = "armourData";

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.swingItem();
        return SwitchHandler.Switch(itemStack, player);
    }

    public int getItemEnchantability() {
        return 18;
    }

    @Override
    public boolean isItemTool(ItemStack itemStack) {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.hasTagCompound()){
            if (EnchantmentHelper.getEnchantmentLevel(CompactArmour.InfoEnchantID, stack) == 1){
                String DATA_ID = ((ArmourStorageItem) stack.getItem()).DATA_ID;
                NBTTagList armour = stack.getTagCompound().getTagList(DATA_ID, 10);
                if (armour.tagCount() != 0) {
                    list.add("");
                    for (int i = (armour.tagCount() - 1); i >= 0; --i) {
                        NBTTagCompound nbt = armour.getCompoundTagAt(i);
                        ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbt);
                        list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocal(itemstack.getUnlocalizedName() + ".name"));
                    }
                    list.add("");
                }
            } else if(EnchantmentHelper.getEnchantmentLevel(CompactArmour.InfoEnchantID, stack) > 1){
                String DATA_ID = ((ArmourStorageItem) stack.getItem()).DATA_ID;
                NBTTagList armour = stack.getTagCompound().getTagList(DATA_ID, 10);
                if (armour.tagCount() != 0) {
                    list.add("");
                    for (int i = (armour.tagCount() - 1); i >= 0; --i) {
                        NBTTagCompound nbt = armour.getCompoundTagAt(i);
                        ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbt);
                        list.add(EnumChatFormatting.YELLOW + itemstack.getDisplayName());
                        if (itemstack.isItemEnchanted()) {
                            NBTTagList ench = itemstack.getEnchantmentTagList();
                            for (int q = 0; q < ench.tagCount(); ++q) {
                                short short1 = ench.getCompoundTagAt(q).getShort("id");
                                short short2 = ench.getCompoundTagAt(q).getShort("lvl");
                                if (Enchantment.enchantmentsList[short1] != null) {
                                    list.add(EnumChatFormatting.LIGHT_PURPLE + "   " + Enchantment.enchantmentsList[short1].getTranslatedName(short2));
                                }
                            }
                        }
                    }
                    list.add("");
                }
            }
        }
    }
}
