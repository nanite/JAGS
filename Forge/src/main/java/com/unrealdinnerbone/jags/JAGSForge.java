package com.unrealdinnerbone.jags;

import com.unrealdinnerbone.jags.data.LootModifierGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JAGS.MOD_ID)
public class JAGSForge {
    
    public JAGSForge() {
        JAGS.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(JAGSForge::onData);

    }

    private static void onData(GatherDataEvent event) {
        event.getGenerator().addProvider(true, new LootModifierGenerator(event.getGenerator().getPackOutput()));
    }
}