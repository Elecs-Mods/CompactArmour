package elec332.compactarmour.handler;

import elec332.compactarmour.items.ArmourStorageItem;
import elec332.compactarmour.items.MultipleArmourStroageItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Created by Elec332 on 23-2-2015.
 */
public class SwitchHandler {
    public static ItemStack Switch(ItemStack itemStack, EntityPlayer player){
        if (itemStack.getItem() instanceof ArmourStorageItem) {
            String DATA_ID = ((ArmourStorageItem) itemStack.getItem()).DATA_ID;
            NBTTagList newArmour = new NBTTagList();
            ItemStack[] armourSlots = player.inventory.armorInventory;
            NBTTagCompound armourPiece;
            for (int i = 0; i < 4; ++i) {
                if (armourSlots[i] != null) {
                    armourPiece = new NBTTagCompound();
                    armourPiece.setByte("Slot", (byte) i);
                    armourSlots[i].writeToNBT(armourPiece);
                    newArmour.appendTag(armourPiece);
                }
                armourSlots[i] = null;
            }
            try {
                NBTTagList toReplace = itemStack.getTagCompound().getTagList(DATA_ID, 10);
                for (int i = 0; i < toReplace.tagCount(); ++i) {
                    NBTTagCompound nbt = toReplace.getCompoundTagAt(i);
                    int j = nbt.getByte("Slot") & 255;
                    ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbt);
                    if (itemstack != null) {
                        if (j >= 0 && j < armourSlots.length) {
                            armourSlots[j] = itemstack;
                        }
                    }
                }
            } catch (Exception e) {
                itemStack.setTagCompound(new NBTTagCompound());
            }
            itemStack.getTagCompound().setTag(DATA_ID, newArmour);
        } else if (itemStack.getItem() instanceof MultipleArmourStroageItem) {
            if (!itemStack.hasTagCompound()) {
                itemStack.setTagCompound(new NBTTagCompound());
                itemStack.getTagCompound().setInteger("nr_q", 0);
            }
            int stackIntNR = itemStack.getTagCompound().getInteger("nr_q");
            String DATA_ID = ((MultipleArmourStroageItem) itemStack.getItem()).DATA_ID;
            NBTTagList newArmour = new NBTTagList();
            ItemStack[] armourSlots = player.inventory.armorInventory;
            NBTTagCompound armourPiece;
            for (int i = 0; i < 4; ++i) {
                if (armourSlots[i] != null) {
                    armourPiece = new NBTTagCompound();
                    armourPiece.setByte("Slot", (byte) i);
                    armourSlots[i].writeToNBT(armourPiece);
                    newArmour.appendTag(armourPiece);
                }
                armourSlots[i] = null;
            }
            try {
                NBTTagList toReplace = itemStack.getTagCompound().getTagList(DATA_ID + stackIntNR, 10);
                for (int i = 0; i < toReplace.tagCount(); ++i) {
                    NBTTagCompound nbt = toReplace.getCompoundTagAt(i);
                    int j = nbt.getByte("Slot") & 255;
                    ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbt);
                    if (itemstack != null) {
                        if (j >= 0 && j < armourSlots.length) {
                            armourSlots[j] = itemstack;
                        }
                    }
                }
            } catch (Exception e) {
                itemStack.setTagCompound(new NBTTagCompound());
            }
            int newNR = getInt(stackIntNR);
            itemStack.getTagCompound().setInteger("nr_q", newNR);
            itemStack.getTagCompound().setTag(DATA_ID+newNR, newArmour);
        }
        return itemStack;
    }

    public static int getInt(int i){
        if (i == 3)
            return 0;
        else
            return i+1;
    }
}
