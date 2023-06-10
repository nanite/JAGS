package com.unrealdinnerbone.jags.data;

import com.unrealdinnerbone.jags.JAGS;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class JAGSFabricData implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(JAGSModelProvider::new);
        pack.addProvider(JAGSBlockTagProvider::new);
        pack.addProvider(LangProvider::new);
        pack.addProvider(AdvancementProvider::new);
        pack.addProvider(JAGSItemTagProvider::new);
        pack.addProvider((output, registriesFuture) -> PackMetadataGenerator.forFeaturePack(output, Component.literal(JAGS.MOD_ID)));
    }

    @Override
    public @Nullable String getEffectiveModId() {
        return DataGeneratorEntrypoint.super.getEffectiveModId();
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        DataGeneratorEntrypoint.super.buildRegistry(registryBuilder);
    }
}
