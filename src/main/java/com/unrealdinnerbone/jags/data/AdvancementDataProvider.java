package com.unrealdinnerbone.jags.data;


import com.unrealdinnerbone.jags.JAGS;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


public class AdvancementDataProvider extends AdvancementProvider {

    public AdvancementDataProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper existingFileHelper) {
        super(packOutput, provider, List.of(new AdvancementData()), existingFileHelper);
    }

    public static class AdvancementData implements AdvancementSubProvider {


        @Override
        public void generate(HolderLookup.Provider provider, Consumer<Advancement> advancementConsumer) {
            ResourceLocation background = new ResourceLocation("minecraft", "textures/gui/advancements/backgrounds/stone.png");

            Advancement advancement = Advancement.Builder.advancement()
                    .display(JAGS.ITEM.get(), getTranslation("title"), getTranslation("description"), background, FrameType.TASK, true, true, true)
                    .addCriterion("use_seed", new SeedTrigger.Instance(EntityPredicate.Composite.ANY))
                    .save(advancementConsumer, JAGS.MOD_ID + ":" + JAGS.MOD_ID);
        }
    }


    private static Component getTranslation(String key) {
        return Component.translatable("advancements." + JAGS.MOD_ID + ".root." + key);
    }
}