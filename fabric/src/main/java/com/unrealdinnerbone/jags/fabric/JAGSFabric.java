package com.unrealdinnerbone.jags.fabric;

import com.unrealdinnerbone.jags.JAGS;
import com.unrealdinnerbone.jags.fabric.events.LootEvents;
import net.fabricmc.api.ModInitializer;

public class JAGSFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        JAGS.init();
        LootEvents.init();
    }
}
