package com.unrealdinnerbone.jags.fabric.client;

import com.unrealdinnerbone.jags.JAGSRegistry;
import com.unrealdinnerbone.jags.client.GrassTintSource;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSources;

import java.util.Collections;

public class JAGSClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        JAGSRegistry.FAKE_GRASS_BLOCKS.forEach((type, entry) -> {
            System.out.println(type.getCapName() + " " + type.getHex());
            BlockColorRegistry.register(Collections.singletonList(new GrassTintSource(type.getHex())), entry.get());
        });

        BlockColorRegistry.register(Collections.singletonList(BlockTintSources.grass()), JAGSRegistry.FAKE_GRASS_BLOCK.get());

    }
}
