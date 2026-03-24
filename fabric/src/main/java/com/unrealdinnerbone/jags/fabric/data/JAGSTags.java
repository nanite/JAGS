package com.unrealdinnerbone.jags.fabric.data;

import com.unrealdinnerbone.jags.JAGSRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class JAGSTags extends FabricTagsProvider.BlockTagsProvider {


    public JAGSTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
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
                BlockTags.SUPPORTS_BAMBOO,
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
