package com.unrealdinnerbone.jags;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = JAGS.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class JAGSNeoClient {

    @SubscribeEvent
    public static void onRegisterBlockColors(RegisterColorHandlersEvent.Block event) {
        JAGSRegistry.FAKE_GRASS_BLOCKS.forEach((type, entry) -> {
            event.register((state, view, pos, tintIndex) -> {
                if (tintIndex == 1) {
                    return view != null && pos != null ? type.getHex() : GrassColor.getDefaultColor();
                } else {
                    return -1;
                }
            }, entry.get());
        });

        event.register((state, view, pos, tintIndex) -> {
            if (tintIndex != 0) {
                return view != null && pos != null ? BiomeColors.getAverageGrassColor(view, pos) : GrassColor.getDefaultColor();
            } else {
                return -1;
            }
        }, JAGSRegistry.FAKE_GRASS_BLOCK.get());

    }

    @SubscribeEvent
    public static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
        JAGSRegistry.FAKE_GRASS_BLOCK_ITEMS.forEach((type, entry) -> {
            event.register((stack, tintIndex) -> type.getHex(), entry.get());
        });

    }
}
