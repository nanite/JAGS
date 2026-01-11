package com.unrealdinnerbone.jags.data;

import com.unrealdinnerbone.jags.JAGSRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class JAGSTags extends FabricTagProvider.BlockTagProvider {


    public JAGSTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        JAGSRegistry.FAKE_GRASS_BLOCKS.forEach((fakeGrassType, registryEntry) -> {
            this.valueLookupBuilder(JAGSRegistry.FAKE_GRASS)
                    .add(registryEntry.get());
        });
        this.valueLookupBuilder(JAGSRegistry.FAKE_GRASS)
                .add(JAGSRegistry.FAKE_GRASS_BLOCK.get());
        List<TagKey<Block>> tags = List.of(
                BlockTags.BAMBOO_PLANTABLE_ON,
                BlockTags.MINEABLE_WITH_SHOVEL,
                BlockTags.DIRT,
                BlockTags.SNIFFER_DIGGABLE_BLOCK
        );
        for (TagKey<Block> tag : tags) {
            this.valueLookupBuilder(tag)
                    .addTag(JAGSRegistry.FAKE_GRASS);
        }
    }
}
