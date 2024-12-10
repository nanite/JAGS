package com.unrealdinnerbone.jags;

import com.unrealdinnerbone.jags.data.LootModifierGenerator;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(JAGS.MOD_ID)
public class JAGSNeo {
    
    public JAGSNeo(IEventBus eventBus) {
        JAGS.init();
        eventBus.addListener(JAGSNeo::onData);
    }

    private static void onData(GatherDataEvent.Server event) {
        event.getGenerator().addProvider(true, new LootModifierGenerator(event.getGenerator().getPackOutput(), event.getLookupProvider()));
    }
}