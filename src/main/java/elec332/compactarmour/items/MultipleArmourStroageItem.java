package elec332.compactarmour.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elec332.compactarmour.handler.SwitchHandler;
import elec332.compactarmour.main.CompactArmour;
import elec332.core.baseclasses.item.BaseItem;
import elec332.core.main.ElecCTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Elec332 on 21-3-2015.
 */
public class MultipleArmourStroageItem extends BaseItem {
    public MultipleArmourStroageItem(){
        super("ArmourSwitcherMultiple", ElecCTab.ElecTab, CompactArmour.ModName);
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
    }

    public String DATA_ID = "armourData";

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.swingItem();
        return SwitchHandler.Switch(itemStack, player);
    }

    @Override
    public int getItemEnchantability() {
        return 18;
    }

    @Override
    public boolean isItemTool(ItemStack stack) {
        return stack.getItem() instanceof MultipleArmourStroageItem;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.hasTagCompound()){
            if (EnchantmentHelper.getEnchantmentLevel(CompactArmour.InfoEnchantID, stack) == 1){
                String DATA_ID = ((MultipleArmourStroageItem) stack.getItem()).DATA_ID+stack.getTagCompound().getInteger("nr");
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
                String DATA_ID = ((MultipleArmourStroageItem) stack.getItem()).DATA_ID+stack.getTagCompound().getInteger("nr");
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

    @Override
    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list){
        for (int i = 0; i < 4; i++)
            list.add(new ItemStack(item, 1, i));
    }
}
