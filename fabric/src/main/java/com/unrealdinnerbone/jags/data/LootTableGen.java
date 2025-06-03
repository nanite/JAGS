package com.unrealdinnerbone.jags.data;

import com.unrealdinnerbone.jags.JAGSRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class LootTableGen extends FabricBlockLootTableProvider {

    public LootTableGen(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        JAGSRegistry.FAKE_GRASS_BLOCKS.forEach((fakeGrassType, registryEntry) -> {
            add(registryEntry.get(), block -> createSingleItemTableWithSilkTouch(registryEntry.get(), Blocks.DIRT));
        });
    }
}
