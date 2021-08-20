package com.unrealdinnerbone.jags.data;

import com.unrealdinnerbone.jags.JAGS;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

import java.util.Collections;

public class LootModifierGenerator extends GlobalLootModifierProvider {

    public LootModifierGenerator(DataGenerator gen) {
        super(gen, JAGS.MOD_ID);
    }

    @Override
    protected void start() {

        add("grass_seed", JAGS.SIMPLE_LOOT_MODIFIER.get(), new SimpleItemModifier(
                createChanceCondition(0.1f, new ResourceLocation("minecraft", "blocks/grass")), new ItemStack(JAGS.ITEM.get())));
    }

    public static ILootCondition[] createChanceCondition(float chance, ResourceLocation table) {
        return new ILootCondition[] { RandomChance.randomChance(chance).build(), LootTableIdCondition.builder(table).build() };
    }
}
