package com.unrealdinnerbone.jags;

import com.unrealdinnerbone.jags.advancements.SeedTrigger;
import com.unrealdinnerbone.jags.item.GrassSeed;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryFactory;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;
import java.util.List;

public class JAGSRegistry implements IRegistry {

    private static final RegistryObjects<Item> ITEMS = RegistryFactory.create(Registries.ITEM);
    public static final RegistryEntry<Item> GRASS_SEED = ITEMS.register("grass_seed", GrassSeed::new);

    public static final TagKey<Block> DIRT = TagKey.create(Registries.BLOCK, new ResourceLocation(JAGS.MOD_ID, "dirt"));
    public static final TagKey<Item> GRASS_SEED_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(JAGS.MOD_ID, "grass_seed"));

    public static SeedTrigger INSTANCE = RegistryFactory.registerCriterion(new SeedTrigger());

    @Override
    public void afterRegistered() {
        //Todo move this to trenzalore
        BuiltInRegistries.CREATIVE_MODE_TAB.registryKeySet().stream()
                .filter(tabResourceKey -> tabResourceKey.location().equals(new ResourceLocation("tools_and_utilities")))
                .findFirst()
                .ifPresent(tabResourceKey -> RegistryFactory.registerCreativeTabItems(tabResourceKey, List.of(GRASS_SEED)));

    }

    @Override
    public List<RegistryObjects<?>> getRegistryObjects() {
        return Arrays.asList(ITEMS);
    }

    @Override
    public String getModID() {
        return JAGS.MOD_ID;
    }
}
