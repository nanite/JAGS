package com.unrealdinnerbone.jags;

import com.unrealdinnerbone.jags.events.LootEvents;
import net.fabricmc.api.ModInitializer;

public class JAGSFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        JAGS.init();
        LootEvents.init();
    }
}
