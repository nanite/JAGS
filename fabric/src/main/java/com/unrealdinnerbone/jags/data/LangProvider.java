package com.unrealdinnerbone.jags.data;

import com.unrealdinnerbone.jags.JAGSRegistry;
import com.unrealdinnerbone.jags.block.FakeGrassBlock;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class LangProvider extends FabricLanguageProvider {

    protected LangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(JAGSRegistry.GRASS_SEED.get(), "Grass Seed");
        translationBuilder.add(JAGSRegistry.FAKE_GRASS_BLOCK.get(), "Fake Grass Block");
        translationBuilder.add("advancements.jags.root.description", "Plant a grass seed");
        translationBuilder.add("advancements.jags.root.title", "Is this what outside looks like?");

        for (Map.Entry<JAGSRegistry.FakeGrassType, RegistryEntry<FakeGrassBlock>> value : JAGSRegistry.FAKE_GRASS_BLOCKS.entrySet()) {
            translationBuilder.add(value.getValue().get(), "Fake Grass Block (" + value.getKey().getCapName() + ")");
        }
    }
}
