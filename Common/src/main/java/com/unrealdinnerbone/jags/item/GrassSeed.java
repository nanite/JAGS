package com.unrealdinnerbone.jags.item;

import com.unrealdinnerbone.jags.JAGSRegistry;
import com.unrealdinnerbone.jags.advancements.SeedTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class GrassSeed extends Item {

    public GrassSeed() {
        super(new Properties());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());
        if(blockState.is(JAGSRegistry.DIRT) && blockState.getBlock() != Blocks.GRASS_BLOCK) {
            context.getLevel().setBlockAndUpdate(context.getClickedPos(), Blocks.GRASS_BLOCK.defaultBlockState());
            context.getPlayer().playSound(SoundEvents.GRASS_PLACE, 1, 1);
            if (!context.getPlayer().getAbilities().instabuild) {
                context.getItemInHand().shrink(1);
            }
            if(context.getPlayer() instanceof ServerPlayer serverPlayer) {
                JAGSRegistry.INSTANCE.trigger(serverPlayer);
            }

            return InteractionResult.sidedSuccess(context.getLevel().isClientSide());
        }else {
            return super.useOn(context);
        }
    }

}
