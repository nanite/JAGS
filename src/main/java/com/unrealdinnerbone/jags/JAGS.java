package com.unrealdinnerbone.jags;

import com.mojang.serialization.Codec;
import com.unrealdinnerbone.jags.data.AdvancementDataProvider;
import com.unrealdinnerbone.jags.data.LootModifierGenerator;
import com.unrealdinnerbone.jags.data.SeedTrigger;
import com.unrealdinnerbone.jags.data.SimpleItemModifier;
import com.unrealdinnerbone.jags.item.GrassSeed;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(JAGS.MOD_ID)
public class JAGS {
    public static final String MOD_ID = "jags";

    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, JAGS.MOD_ID);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> SIMPLE_LOOT_MODIFIER  = LOOT_MODIFIERS.register("simple", () -> SimpleItemModifier.CODEC);
    public static final RegistryObject<GrassSeed> ITEM = ITEMS.register(MOD_ID, GrassSeed::new);
    public static final TagKey<Block> DIRT = BlockTags.create(new ResourceLocation(MOD_ID, "dirt"));

    public JAGS() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(JAGS::onData);
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CriteriaTriggers.register(SeedTrigger.INSTANCE);
        LOOT_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(JAGS::itemGroupEvent);
    }

    public static void itemGroupEvent(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ITEM.get());
        }
    }

    public static void onData(GatherDataEvent event) {
        event.getGenerator().addProvider(true, new LootModifierGenerator(event.getGenerator().getPackOutput()));
        event.getGenerator().addProvider(true, new AdvancementDataProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));

        event.getGenerator().addProvider(true, new BlockTagsProvider(event.getGenerator().getPackOutput(), event.getLookupProvider(), JAGS.MOD_ID, event.getExistingFileHelper()) {
            @Override
            protected void addTags(HolderLookup.Provider provider) {
                this.tag(DIRT).addTag(BlockTags.DIRT);
            }
        });

        event.getGenerator().addProvider(true, PackMetadataGenerator.forFeaturePack(event.getGenerator().getPackOutput(), Component.literal("JAGS Assets")));
    }
}
