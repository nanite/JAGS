package com.unrealdinnerbone.jags.data;


import com.unrealdinnerbone.jags.JAGS;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;


public class AdvancementDataProvider extends AdvancementProvider {

    public AdvancementDataProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, existingFileHelper);
    }

    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
        new AdvancementData().accept(consumer);
    }

    public static class AdvancementData implements Consumer<Consumer<Advancement>> {

        @Override
        public void accept(Consumer<Advancement> consumer) {

            ResourceLocation background = new ResourceLocation("minecraft", "textures/gui/advancements/backgrounds/stone.png");

            Advancement advancement = Advancement.Builder.advancement()
                    .display(JAGS.ITEM.get(), getTranslation("title"), getTranslation("description"), background, FrameType.TASK, true, true, true)
                    .addCriterion("use_seed", new SeedTrigger.Instance(EntityPredicate.Composite.ANY))
                    .save(consumer, JAGS.MOD_ID + ":" + JAGS.MOD_ID);

        }
    }


    private static Component getTranslation(String key) {
        return new TranslatableComponent("advancements." + JAGS.MOD_ID + ".root." + key);
    }
}