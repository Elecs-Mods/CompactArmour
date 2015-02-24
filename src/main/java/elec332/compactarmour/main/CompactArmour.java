package elec332.compactarmour.main;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import elec332.compactarmour.ArmourStorageItem;
import elec332.compactarmour.SwitchSetsPacket;
import elec332.compactarmour.proxies.CommonProxy;
import elec332.core.helper.FileHelper;
import elec332.core.helper.MCModInfo;
import elec332.core.helper.ModInfoHelper;
import elec332.core.modBaseUtils.ModBase;
import elec332.core.modBaseUtils.modInfo;
import elec332.core.network.NetworkHandler;

import java.io.File;

/**
 * Created by Elec332 on 24-2-2015.
 */
@Mod(modid = CompactArmour.ModID, name = CompactArmour.ModName, dependencies = modInfo.DEPENDENCIES+"@[#ELECCORE_VER#,)",
        acceptedMinecraftVersions = modInfo.ACCEPTEDMCVERSIONS, useMetadata = true, canBeDeactivated = true)
public class CompactArmour extends ModBase {

    public static final String ModName = "CompactArmour"; //Human readable name
    @Deprecated
    public static final String ModID = ModName;  //modid (usually lowercase)

    @SidedProxy(clientSide = "elec332.compactarmour.proxies.ClientProxy", serverSide = "elec332.compactarmour.proxies.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(ModID)
    public static CompactArmour instance;
    public static NetworkHandler networkHandler;
    public static ArmourStorageItem armourStorageItem;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        this.cfg = FileHelper.getConfigFileElec(event);
        loadConfiguration();
        networkHandler = new NetworkHandler(ModInfoHelper.getModID(event));
        networkHandler.registerServerPacket(SwitchSetsPacket.class);
        proxy.registerKeyStuff();

        loadConfiguration();
        MCModInfo.CreateMCModInfoElec(event, "Compact Armour Mod", "Loading URL...", "assets/elec332/logo.png", new String[]{"Elec332"});
        notifyEvent(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        loadConfiguration();
        armourStorageItem = new ArmourStorageItem();

        notifyEvent(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        loadConfiguration();
        //Nope

        notifyEvent(event);
    }

    File cfg;

    @Override
    public File configFile() {
        return cfg;
    }

    @Override
    public String modID(){
        return ModID;
    }
}
