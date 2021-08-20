package com.unrealdinnerbone.jags.data;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class SimpleItemModifier extends LootModifier {

    private final ItemStack stack;

    protected SimpleItemModifier(ILootCondition[] conditionsIn, ItemStack itemStack) {
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
        public SimpleItemModifier read(ResourceLocation location, JsonObject object, ILootCondition[] conditions) {
            return new SimpleItemModifier(conditions, new ItemStack(JSONUtils.getAsItem(object, "item")));

        }

        @Override
        public JsonObject write(SimpleItemModifier instance) {
            JsonObject jsonObject = makeConditions(instance.conditions);
            jsonObject.addProperty("item", instance.getStack().getItem().getRegistryName().toString());
            return jsonObject;
        }

    }
}
