package com.unrealdinnerbone.jags.fabric.events;

import com.unrealdinnerbone.jags.JAGSRegistry;
import com.unrealdinnerbone.trenzalore.lib.IDUtils;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.List;

public class LootEvents {

    private static final List<Identifier> GRASS_BLOCKS = List.of(
            IDUtils.id("minecraft", "blocks/short_grass"),
            IDUtils.id("minecraft", "blocks/tall_grass"));
    public static void init() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, provider) -> {
            if(GRASS_BLOCKS.contains(key.identifier())) {
                LootPool poolBuilder = LootPool.lootPool()
                        .when(LootItemRandomChanceCondition.randomChance(0.1f))
                        .add(LootItem.lootTableItem(JAGSRegistry.GRASS_SEED.get()).build())
                        .build();
                tableBuilder.pool(poolBuilder);
            }
        });
    }
}
