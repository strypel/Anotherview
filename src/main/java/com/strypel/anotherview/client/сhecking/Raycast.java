package com.strypel.anotherview.client.сhecking;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class Raycast {
    public static BlockState cast(Ray ray, ClientLevel level, List<Block> ignoredBlocks, List<TagKey<Block>> ignoredTags){
        while (ray.length() < ray.maxLength){
            ray.increase();

            boolean f = true;
            BlockState block = level.getBlockState(new BlockPos(ray.getEnd()));
            if(block.is(Blocks.AIR)) {
                f = false;
            }
            for (Block ignored : ignoredBlocks) {
                if(block.is(ignored))
                    f = false;
            }
            for (TagKey<Block> tag : ignoredTags) {
                if(block.is(tag))
                    f = false;
            }

            if(f) return block;
        }
        return null;
    }
    public static BlockState cast(Ray ray, ClientLevel level){
        return cast(ray,level,new ArrayList<>(),new ArrayList<>());
    }
}
