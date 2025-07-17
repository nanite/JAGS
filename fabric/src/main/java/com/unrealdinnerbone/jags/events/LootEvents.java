package com.unrealdinnerbone.jags.events;

import com.unrealdinnerbone.jags.JAGSRegistry;
import com.unrealdinnerbone.trenzalore.lib.RLUtils;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.List;

public class LootEvents {

    private static final List<ResourceLocation> GRASS_BLOCKS = List.of(
            RLUtils.rl("minecraft", "blocks/short_grass"),
            RLUtils.rl("minecraft", "blocks/tall_grass"));
    public static void init() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if(GRASS_BLOCKS.contains(key.location())) {
                LootPool poolBuilder = LootPool.lootPool()
                        .when(LootItemRandomChanceCondition.randomChance(0.1f))
                        .with(LootItem.lootTableItem(JAGSRegistry.GRASS_SEED.get()).build())
                        .build();
                tableBuilder.pool(poolBuilder);
            }
        });
    }
}
