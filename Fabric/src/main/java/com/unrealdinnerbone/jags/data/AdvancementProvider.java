package com.unrealdinnerbone.jags.data;

import com.unrealdinnerbone.jags.JAGS;
import com.unrealdinnerbone.jags.JAGSRegistry;
import com.unrealdinnerbone.jags.advancements.SeedTrigger;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class AdvancementProvider extends FabricAdvancementProvider {

    public AdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        ResourceLocation background = new ResourceLocation("minecraft", "textures/gui/advancements/backgrounds/stone.png");
        consumer.accept(Advancement.Builder.advancement()
                .display(JAGSRegistry.GRASS_SEED.get(),
                        getTranslation("title"),
                        getTranslation("description"),
                        background,
                        FrameType.TASK, true, true, true)
                .addCriterion("use_seed",
                        new SeedTrigger.Instance(ContextAwarePredicate.ANY))
                .build(new ResourceLocation(JAGS.MOD_ID, "touch_grass")));




    }

    private static Component getTranslation(String key) {
        return Component.translatable("advancements." + JAGS.MOD_ID + ".root." + key);
    }
}
