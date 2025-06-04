package com.unrealdinnerbone.jags.data;

import com.unrealdinnerbone.jags.JAGS;
import com.unrealdinnerbone.jags.JAGSRegistry;
import com.unrealdinnerbone.jags.block.FakeGrassBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class JAGSTags extends FabricTagProvider.BlockTagProvider {

    private final TagKey<Block> FAKE_GRASS =  TagKey.create(Registries.BLOCK, JAGS.rl("fake_grass"));

    public JAGSTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        JAGSRegistry.FAKE_GRASS_BLOCKS.forEach((fakeGrassType, registryEntry) -> {
            this.getOrCreateTagBuilder(FAKE_GRASS)
                    .add(registryEntry.get());
        });
        this.getOrCreateTagBuilder(FAKE_GRASS)
                .add(JAGSRegistry.FAKE_GRASS_BLOCK.get());
        List<TagKey<Block>> tags = List.of(
                BlockTags.BAMBOO_PLANTABLE_ON,
                BlockTags.DEAD_BUSH_MAY_PLACE_ON,
                BlockTags.MINEABLE_WITH_SHOVEL,
                BlockTags.DIRT,
                BlockTags.SNIFFER_DIGGABLE_BLOCK
        );
        for (TagKey<Block> tag : tags) {
            this.getOrCreateTagBuilder(tag)
                    .addTag(FAKE_GRASS);
        }
    }
}
