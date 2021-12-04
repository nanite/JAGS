package com.unrealdinnerbone.jags.data;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class SimpleItemModifier extends LootModifier {

    private final ItemStack stack;


    public SimpleItemModifier(LootItemCondition[] conditionsIn, ItemStack itemStack) {
        super(conditionsIn);
        this.stack = itemStack;
    }

    public ItemStack getStack() {
        return stack;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(stack.copy());
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<SimpleItemModifier> {
        @Override
        public SimpleItemModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditions) {
            return new SimpleItemModifier(conditions, new ItemStack(GsonHelper.getAsItem(object, "item")));

        }

        @Override
        public JsonObject write(SimpleItemModifier instance) {
            JsonObject jsonObject = makeConditions(instance.conditions);
            jsonObject.addProperty("item", instance.getStack().getItem().getRegistryName().toString());
            return jsonObject;
        }

    }
}
