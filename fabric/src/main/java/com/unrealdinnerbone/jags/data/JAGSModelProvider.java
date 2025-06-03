package com.unrealdinnerbone.jags.data;

import com.unrealdinnerbone.jags.JAGSRegistry;
import com.unrealdinnerbone.trenzalore.lib.RLUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;

public class JAGSModelProvider extends FabricModelProvider {

    public JAGSModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        JAGSRegistry.FAKE_GRASS_BLOCKS.forEach((type, registryEntry) -> {
            //create model with different texture on each side

            blockStateModelGenerator.createTrivialBlock(registryEntry.get(), TexturedModel.CUBE_TOP_BOTTOM.updateTexture(textureMapping -> {
                textureMapping
                        .put(TextureSlot.SIDE, RLUtils.rl("minecraft", "block/grass_block_side"))
                        .put(TextureSlot.TOP, RLUtils.rl("minecraft", "block/grass_block_top"))
                        .put(TextureSlot.BOTTOM, RLUtils.rl("minecraft", "block/dirt"));
            }));


        });
        blockStateModelGenerator.createTrivialCube(JAGSRegistry.FAKE_GRASS_BLOCK.get());
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(JAGSRegistry.GRASS_SEED.get(), ModelTemplates.FLAT_ITEM);
    }
}
