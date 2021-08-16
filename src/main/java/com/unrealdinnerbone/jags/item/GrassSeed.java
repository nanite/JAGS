package com.unrealdinnerbone.jags.item;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.common.Tags;

public class GrassSeed extends Item {

    public GrassSeed() {
        super(new Item.Properties().tab(ItemGroup.TAB_FOOD));
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        if(context.getLevel().getBlockState(context.getClickedPos()).is(Tags.Blocks.DIRT)) {
            context.getLevel().setBlockAndUpdate(context.getClickedPos(), Blocks.GRASS_BLOCK.defaultBlockState());
            return ActionResultType.sidedSuccess(context.getLevel().isClientSide);
        }
        return ActionResultType.PASS;
    }

}
