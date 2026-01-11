package com.unrealdinnerbone.jags;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = JAGS.MOD_ID, value = Dist.CLIENT)
public class JAGSNeoClient {

    @SubscribeEvent
    public static void onRegisterBlockColors(RegisterColorHandlersEvent.Block event) {
        JAGSRegistry.FAKE_GRASS_BLOCKS.forEach((type, entry) -> {
            event.register((state, view, pos, tintIndex) -> type.getHex(), entry.get());
        });

        event.register((state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getAverageGrassColor(view, pos) : GrassColor.getDefaultColor(), JAGSRegistry.FAKE_GRASS_BLOCK.get());

    }

//    @SubscribeEvent
//    public static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
//        JAGSRegistry.FAKE_GRASS_BLOCK_ITEMS.forEach((type, entry) -> {
//            event.register((stack, tintIndex) -> type.getHex(), entry.get());
//        });
//
//    }
}
