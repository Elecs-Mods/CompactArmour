package elec332.compactarmour;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;

/**
 * Created by Elec332 on 25-2-2015.
 */
public class InfoEnchant extends Enchantment {
    public InfoEnchant(int ID) {
        super(ID, 2, EnumEnchantmentType.all);
        this.setName("Information");
    }

    public int getMinEnchantability(int par1) {
        return 1 + (par1 - 1) * 15;
    }

    public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 50;
    }

    public int getMaxLevel() {
        return 2;
    }

    public boolean canApply(ItemStack stack) {
        return stack.getItem() instanceof ArmourStorageItem || stack.getItem() instanceof ItemBook;
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return this.canApply(stack);
    }
}
