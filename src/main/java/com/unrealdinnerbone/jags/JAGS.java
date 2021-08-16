package com.unrealdinnerbone.jags;

import com.unrealdinnerbone.jags.item.GrassSeed;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(JAGS.MOD_ID)
public class JAGS {
    public static final String MOD_ID = "jags";

    private static final Logger LOGGER = LogManager.getLogger();

    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<GrassSeed> ITEM = ITEMS.register(MOD_ID, GrassSeed::new);

    public JAGS() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
