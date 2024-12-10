package com.unrealdinnerbone.jags.client;

import com.unrealdinnerbone.jags.JAGS;
import com.unrealdinnerbone.jags.data.LangProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;

public class JAGSFabricData implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(JAGSModelProvider::new);
        pack.addProvider(LangProvider::new);
        pack.addProvider((output, registriesFuture) -> PackMetadataGenerator.forFeaturePack(output, Component.literal(JAGS.MOD_ID)));
    }
}
