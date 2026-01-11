package com.unrealdinnerbone.jags;

import com.unrealdinnerbone.jags.block.FakeGrassBlock;
import com.unrealdinnerbone.jags.item.GrassSeed;
import com.unrealdinnerbone.trenzalore.api.platform.services.ICreativeTabRegister;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.AbstractRegistryObjects;
import com.unrealdinnerbone.trenzalore.api.registry.BlockRegistryObjects;
import com.unrealdinnerbone.trenzalore.api.registry.ItemRegistryObjects;
import com.unrealdinnerbone.trenzalore.api.registry.Regeneration;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import com.unrealdinnerbone.trenzalore.lib.CreativeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JAGSRegistry implements IRegistry {

    public static final TagKey<Block> FAKE_GRASS =  TagKey.create(Registries.BLOCK, JAGS.rl("fake_grass"));


    private static final ItemRegistryObjects ITEMS = Regeneration.createItemRegistry(JAGS.MOD_ID);
    private static final BlockRegistryObjects BLOCKS = Regeneration.createBlockRegistry(JAGS.MOD_ID);

    public static final RegistryEntry.ItemEntry<GrassSeed> GRASS_SEED = ITEMS.register("grass_seed", GrassSeed::new, properties -> properties);

    public static final RegistryEntry.BlockEntry<Block> FAKE_GRASS_BLOCK = BLOCKS.register("fake_grass_block", FakeGrassBlock::new, properties -> properties.mapColor(MapColor.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS));
    public static final RegistryEntry.ItemEntry<BlockItem> FAKE_GRASS_BLOCK_ITEM = ITEMS.registerBlockItem("fake_grass_block", FAKE_GRASS_BLOCK, properties -> properties);

    public static final Map<FakeGrassType, RegistryEntry.BlockEntry<FakeGrassBlock>> FAKE_GRASS_BLOCKS = new HashMap<>();
    public static final Map<FakeGrassType, RegistryEntry.ItemEntry<Item>> FAKE_GRASS_BLOCK_ITEMS = new HashMap<>();

    static {
        for (FakeGrassType value : FakeGrassType.values()) {
            RegistryEntry.BlockEntry<FakeGrassBlock> register = BLOCKS.register(value.name().toLowerCase() + "_fake_grass_block", FakeGrassBlock::new, properties -> properties.mapColor(MapColor.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS));
            FAKE_GRASS_BLOCKS.put(value, register);
        }
        for (FakeGrassType value : FakeGrassType.values()) {
            RegistryEntry.ItemEntry<Item> item = ITEMS.register(value.name().toLowerCase() + "_fake_grass_block", (prop) -> new BlockItem(FAKE_GRASS_BLOCKS.get(value).get(), prop), properties -> properties);
            FAKE_GRASS_BLOCK_ITEMS.put(value, item);
        }
    }

    @Override
    public void afterRegistered(ICreativeTabRegister creativeTabRegister) {
        creativeTabRegister.addItemToCreativeTab(creativeTabRegister.tabs().toolsAndUtilities(), GRASS_SEED);
        creativeTabRegister.addItemToCreativeTab(creativeTabRegister.tabs().buildingBlocks(), FAKE_GRASS_BLOCK_ITEM);
        FAKE_GRASS_BLOCK_ITEMS.forEach((type, entry) -> creativeTabRegister.addItemToCreativeTab(creativeTabRegister.tabs().buildingBlocks(), entry));
    }

    @Override
    public List<AbstractRegistryObjects<?>> getRegistryObjects() {
        return Arrays.asList(BLOCKS, ITEMS);
    }

    @Override
    public String getModID() {
        return JAGS.MOD_ID;
    }

    public enum FakeGrassType {
        BADLANDS("#90814D"),
        CHEERY("#B6DB61"),
        DESERT("#BFB755"),
        SNOW("#9ABE4B"),
        JUNGLE("#59C93C"),
        SPARSE_JUNGLE("#64C73F"),
        MUSHROOM("#55C93F"),
        SWAMP("#6A7039"),
        MANGROVE("#4C763C"),
        PLAINS("#91BD59"),
        DARK_FOREST("#507A32"),
        PALE_GARDEN("#878D76"),
        BIRCH("#88BB67"),
        OCEAN("#8EB971"),
        MEADOW("#8EB971"),
        ;

        private final int hex;

        FakeGrassType(String hex) {
            this.hex = SimpleColor.fromHex(hex).asRGB();
        }

        public String getCapName() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }

        public int getHex() {
            return hex;
        }
    }
}
