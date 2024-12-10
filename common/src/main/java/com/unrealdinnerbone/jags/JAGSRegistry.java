package com.unrealdinnerbone.jags;

import com.unrealdinnerbone.jags.item.GrassSeed;
import com.unrealdinnerbone.trenzalore.api.platform.services.ICreativeTabRegister;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.ItemRegistryObjects;
import com.unrealdinnerbone.trenzalore.api.registry.Regeneration;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import com.unrealdinnerbone.trenzalore.lib.CreativeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class JAGSRegistry implements IRegistry {

    private static final ItemRegistryObjects ITEMS = Regeneration.createItemRegistry(JAGS.MOD_ID);

    public static final RegistryEntry<GrassSeed> GRASS_SEED = ITEMS.register("grass_seed", GrassSeed::new, UnaryOperator.identity());

    @Override
    public void afterRegistered(ICreativeTabRegister register) {
        register.addItemToCreativeTab(CreativeTabs.TOOLS_AND_UTILITIES, List.of(GRASS_SEED));
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
