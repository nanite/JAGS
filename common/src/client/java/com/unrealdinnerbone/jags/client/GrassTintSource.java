package com.unrealdinnerbone.jags.client;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.world.level.block.state.BlockState;

public record GrassTintSource(int hex) implements BlockTintSource {

    @Override
    public int color(BlockState state) {
        return hex;
    }

}