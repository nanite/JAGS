package com.unrealdinnerbone.jags.data;

import com.unrealdinnerbone.jags.JAGS;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class LootModifierGenerator extends GlobalLootModifierProvider {

    public LootModifierGenerator(PackOutput packOutput) {
        super(packOutput, JAGS.MOD_ID);
    }

    @Override
    protected void start() {
        add("grass_seed", new SimpleItemModifier(
                createChanceCondition(0.1f, new ResourceLocation("minecraft", "blocks/grass")), new ItemStack(JAGS.ITEM.get())));
    }

    public static LootItemCondition[] createChanceCondition(float chance, ResourceLocation table) {
        return new LootItemCondition[] { LootItemRandomChanceCondition.randomChance(chance).build(), LootTableIdCondition.builder(table).build() };
    }
}
