package elec332.compactarmour.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elec332.compactarmour.network.SwitchSetsPacket;
import elec332.compactarmour.main.CompactArmour;
import net.minecraft.client.settings.KeyBinding;

/**
 * Created by Elec332 on 22-2-2015.
 */
public class KeyHandler {
    public KeyBinding key = new KeyBinding("ExtraArmour Inventory", 49, "key.categories.inventory");

    public KeyHandler() {
        ClientRegistry.registerKeyBinding(this.key);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void keyEvent(InputEvent.KeyInputEvent event) {
        if (this.key.getIsKeyPressed() && FMLClientHandler.instance().getClient().inGameHasFocus){
            CompactArmour.networkHandler.getNetworkWrapper().sendToServer(new SwitchSetsPacket());
        }
    }
}
