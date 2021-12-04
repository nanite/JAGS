package com.unrealdinnerbone.jags.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

public class GrassSeed extends Item {

    public GrassSeed() {
        super(new Item.Properties().tab(CreativeModeTab.TAB_MISC));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());
        if(blockState.is(Tags.Blocks.DIRT) && blockState.getBlock() != Blocks.GRASS_BLOCK) {
            context.getLevel().setBlockAndUpdate(context.getClickedPos(), Blocks.GRASS_BLOCK.defaultBlockState());
            context.getPlayer().playSound(SoundEvents.GRASS_PLACE, 1, 1);
            if (!context.getPlayer().getAbilities().instabuild) {
                context.getItemInHand().shrink(1);
            }

            return InteractionResult.sidedSuccess(context.getLevel().isClientSide());
        }else {
            return InteractionResult.PASS;
        }
    }

}
