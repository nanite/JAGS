package com.unrealdinnerbone.jags.data;

import com.google.gson.JsonObject;
import com.unrealdinnerbone.jags.JAGS;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class SeedTrigger extends SimpleCriterionTrigger<SeedTrigger.Instance> {

    public static SeedTrigger INSTANCE = new SeedTrigger();

    public static final ResourceLocation ID = new ResourceLocation(JAGS.MOD_ID, JAGS.MOD_ID);

    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer serverPlayerEntity) {
        this.trigger(serverPlayerEntity, (value) -> true);
    }

    @Override
    protected Instance createInstance(JsonObject jsonObject, EntityPredicate.Composite composite, DeserializationContext context) {
        return new SeedTrigger.Instance(composite);
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        public Instance(EntityPredicate.Composite andPredicate) {
            super(SeedTrigger.ID, andPredicate);
        }
    }
}