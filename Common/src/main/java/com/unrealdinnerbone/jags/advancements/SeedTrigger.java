package com.unrealdinnerbone.jags.advancements;

import com.google.gson.JsonObject;
import com.unrealdinnerbone.jags.JAGS;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryFactory;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class SeedTrigger extends SimpleCriterionTrigger<SeedTrigger.Instance> {



    public static final ResourceLocation ID = new ResourceLocation(JAGS.MOD_ID, JAGS.MOD_ID);

    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer serverPlayerEntity) {
        this.trigger(serverPlayerEntity, (value) -> true);
    }

    @Override
    protected Instance createInstance(JsonObject jsonObject, ContextAwarePredicate awarePredicate, DeserializationContext context) {
        return new Instance(awarePredicate);
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        public Instance(ContextAwarePredicate awarePredicate) {
            super(SeedTrigger.ID, awarePredicate);
        }
    }
}