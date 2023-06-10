package com.unrealdinnerbone.jags.data;

import com.unrealdinnerbone.jags.JAGSRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class LangProvider extends FabricLanguageProvider {

    protected LangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(JAGSRegistry.GRASS_SEED.get(), "Grass Seed");
        translationBuilder.add("advancements.jags.root.description", "Plant a grass seed");
        translationBuilder.add("advancements.jags.root.title", "Is this what outside looks like?");
    }
}
