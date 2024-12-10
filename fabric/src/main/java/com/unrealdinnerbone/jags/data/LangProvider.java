package com.unrealdinnerbone.jags.data;

import com.unrealdinnerbone.jags.JAGSRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class LangProvider extends FabricLanguageProvider {


    public LangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(JAGSRegistry.GRASS_SEED.get(), "Grass Seed");
        translationBuilder.add("advancements.jags.root.description", "Plant a grass seed");
        translationBuilder.add("advancements.jags.root.title", "Is this what outside looks like?");
    }
}
