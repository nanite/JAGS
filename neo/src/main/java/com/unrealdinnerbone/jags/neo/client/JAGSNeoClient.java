package com.unrealdinnerbone.jags.neo.client;

import com.unrealdinnerbone.jags.JAGS;
import com.unrealdinnerbone.jags.JAGSRegistry;
import com.unrealdinnerbone.jags.client.GrassTintSource;
import net.minecraft.client.color.block.BlockTintSources;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.List;

@EventBusSubscriber(modid = JAGS.MOD_ID, value = Dist.CLIENT)
public class JAGSNeoClient {

    @SubscribeEvent
    public static void onRegisterBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
        JAGSRegistry.FAKE_GRASS_BLOCKS.forEach((type, entry) -> {
            event.register(List.of(new GrassTintSource(type.getHex())), entry.get());
        });

        event.register(List.of(BlockTintSources.grassBlock()), JAGSRegistry.FAKE_GRASS_BLOCK.get());

    }

//    @SubscribeEvent
//    public static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
//        JAGSRegistry.FAKE_GRASS_BLOCK_ITEMS.forEach((type, entry) -> {
//            event.register((stack, tintIndex) -> type.getHex(), entry.get());
//        });
//
//    }
}
