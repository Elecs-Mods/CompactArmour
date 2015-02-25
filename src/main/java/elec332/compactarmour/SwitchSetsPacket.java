package elec332.compactarmour;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import elec332.compactarmour.main.CompactArmour;
import elec332.core.network.AbstractPacket;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * Created by Elec332 on 21-2-2015.
 */
public class SwitchSetsPacket extends AbstractPacket {
    @Override
    public IMessage onMessage(AbstractPacket message, MessageContext ctx) {
        InventoryPlayer inv = ctx.getServerHandler().playerEntity.inventory;
        if(inv.hasItem(CompactArmour.armourStorageItem))
            for (int i = 0; i < inv.mainInventory.length; ++i)
                try {
                    if (inv.mainInventory[i] != null) {
                        SwitchHandler.Switch(inv.mainInventory[i], ctx.getServerHandler().playerEntity);
                    }
                } catch (Exception e){}
        return null;
    }
}
