package com.unrealdinnerbone.jags.events;

import com.unrealdinnerbone.jags.JAGSRegistry;
import com.unrealdinnerbone.trenzalore.content.TrenzaloreConfig;
import net.fabricmc.fabric.api.loot.v2.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.List;

public class LootEvents {

    private static final List<ResourceLocation> GRASS_BLOCKS = List.of(
            new ResourceLocation("minecraft", "blocks/grass"),
            new ResourceLocation("minecraft", "blocks/tall_grass"));
    public static void init() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, builder, setter) -> {
            if(GRASS_BLOCKS.contains(id)) {
                LootPool poolBuilder = LootPool.lootPool()
                        .when(LootItemRandomChanceCondition.randomChance(0.1f))
                        .with(LootItem.lootTableItem(JAGSRegistry.GRASS_SEED.get()).build())
                        .build();
                builder.pool(poolBuilder);
            }
        });
    }
}
