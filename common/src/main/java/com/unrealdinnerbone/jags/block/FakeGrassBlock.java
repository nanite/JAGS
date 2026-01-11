package com.unrealdinnerbone.jags.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;

public class FakeGrassBlock extends Block {

    public FakeGrassBlock(Properties properties) {
        super(properties);
    }

    private static boolean canBeGrass(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos blockPos2 = blockPos.above();
        BlockState blockState2 = levelReader.getBlockState(blockPos2);
       if (blockState2.getFluidState().getAmount() == 8) {
            return false;
        } else {
           int i = LightEngine.getLightBlockInto(blockState, blockState2, Direction.UP, blockState2.getLightBlock());
           return i < LightEngine.MAX_LEVEL;
        }
    }

    private static boolean canPropagate(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos blockPos2 = blockPos.above();
        return canBeGrass(blockState, levelReader, blockPos) && !levelReader.getFluidState(blockPos2).is(FluidTags.WATER);
    }

    @Override
    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (!canBeGrass(blockState, serverLevel, blockPos)) {
            serverLevel.setBlockAndUpdate(blockPos, Blocks.DIRT.defaultBlockState());
        } else {
            if (serverLevel.getMaxLocalRawBrightness(blockPos.above()) >= 9) {
                BlockState blockState2 = this.defaultBlockState();

                for (int i = 0; i < 4; i++) {
                    BlockPos blockPos2 = blockPos.offset(randomSource.nextInt(3) - 1, randomSource.nextInt(5) - 3, randomSource.nextInt(3) - 1);
                    if (serverLevel.getBlockState(blockPos2).is(Blocks.DIRT) && canPropagate(blockState2, serverLevel, blockPos2)) {
                        serverLevel.setBlockAndUpdate(blockPos2, blockState2);
                    }
                }
            }
        }
    }
}
