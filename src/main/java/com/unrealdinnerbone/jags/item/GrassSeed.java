package com.unrealdinnerbone.jags.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;

public class GrassSeed extends Item {

    public GrassSeed() {
        super(new Item.Properties().tab(ItemGroup.TAB_MISC));
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());
        if(blockState.is(Tags.Blocks.DIRT) && blockState.getBlock() != Blocks.GRASS_BLOCK) {
            context.getLevel().setBlockAndUpdate(context.getClickedPos(), Blocks.GRASS_BLOCK.defaultBlockState());
            context.getPlayer().playSound(SoundEvents.GRASS_PLACE, 1, 1);
            if (!context.getPlayer().abilities.instabuild) {
                context.getItemInHand().shrink(1);
            }
            return ActionResultType.sidedSuccess(context.getLevel().isClientSide());
        }else {
            return ActionResultType.PASS;
        }
    }

}
