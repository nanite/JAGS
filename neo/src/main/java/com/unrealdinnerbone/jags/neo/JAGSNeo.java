package com.unrealdinnerbone.jags.neo;

import com.unrealdinnerbone.jags.JAGS;
import com.unrealdinnerbone.jags.JAGSRegistry;
import com.unrealdinnerbone.jags.neo.data.LootModifierGenerator;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

@Mod(JAGS.MOD_ID)
public class JAGSNeo {
    
    public JAGSNeo(IEventBus eventBus) {
        JAGS.init();
        eventBus.addListener(JAGSNeo::onData);
        NeoForge.EVENT_BUS.addListener(JAGSNeo::onHoe);
    }

    private static void onData(GatherDataEvent.Client event) {
        event.getGenerator().addProvider(true, new LootModifierGenerator(event.getGenerator().getPackOutput(), event.getLookupProvider()));
    }

    public static void onHoe(BlockEvent.BlockToolModificationEvent event) {
        if (event.getState().is(JAGSRegistry.FAKE_GRASS)) {
            if (event.getItemAbility() == ItemAbilities.HOE_TILL) {
                event.setFinalState(Blocks.FARMLAND.defaultBlockState());
            }
        }
    }
}