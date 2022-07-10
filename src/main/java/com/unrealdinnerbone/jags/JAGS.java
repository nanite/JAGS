package com.unrealdinnerbone.jags;

import com.mojang.serialization.Codec;
import com.unrealdinnerbone.jags.data.LootModifierGenerator;
import com.unrealdinnerbone.jags.data.SimpleItemModifier;
import com.unrealdinnerbone.jags.item.GrassSeed;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(JAGS.MOD_ID)
public class JAGS {
    public static final String MOD_ID = "jags";

    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);


    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, MOD_ID);

    public static final RegistryObject<GrassSeed> ITEM = ITEMS.register(MOD_ID, GrassSeed::new);

    private static final RegistryObject<Codec<SimpleItemModifier>> GRASS_SEEDS = GLM.register("wheat_harvest",() -> SimpleItemModifier.CODEC);

    public static final TagKey<Block> DIRT = BlockTags.create(new ResourceLocation(MOD_ID, "dirt"));

    public JAGS() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(JAGS::onData);
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        GLM.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void onData(GatherDataEvent event) {
        event.getGenerator().addProvider(true, new LootModifierGenerator(event.getGenerator()));
        event.getGenerator().addProvider(true, new BlockTagsProvider(event.getGenerator(), MOD_ID, event.getExistingFileHelper()) {
            @Override
            protected void addTags() {
                this.tag(DIRT).addTag(BlockTags.DIRT);
            }
        });
    }
}
