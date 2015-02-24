package elec332.compactarmour.proxies;

import cpw.mods.fml.common.FMLCommonHandler;
import elec332.compactarmour.KeyHandler;

/**
 * Created by Elec332 on 24-2-2015.
 */
public class ClientProxy extends CommonProxy{
    @Override
    public void registerKeyStuff() {
        this.keyHandler = new KeyHandler();
        FMLCommonHandler.instance().bus().register(keyHandler);
    }
}
