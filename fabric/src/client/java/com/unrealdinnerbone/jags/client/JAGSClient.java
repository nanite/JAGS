package com.unrealdinnerbone.jags.client;

import com.unrealdinnerbone.jags.JAGSRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.GrassColor;

public class JAGSClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        JAGSRegistry.FAKE_GRASS_BLOCKS.forEach((type, entry) -> {
            ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
                if (tintIndex == 0) {
                    return type.getHex();
                }
                return -1; // Default color
            }, entry.get());
        });

        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
            if (tintIndex != 0) {
                return view != null && pos != null ? BiomeColors.getAverageGrassColor(view, pos) : GrassColor.getDefaultColor();
            } else {
                return -1;
            }
        }, JAGSRegistry.FAKE_GRASS_BLOCK.get());

        JAGSRegistry.FAKE_GRASS_BLOCK_ITEMS.forEach((type, entry) -> {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                if (tintIndex == 0) {
                    return type.getHex();
                }
                return -1; // Default color
            }, entry.get());
        });
    }
}
