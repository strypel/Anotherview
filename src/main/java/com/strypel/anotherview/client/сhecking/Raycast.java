package com.strypel.anotherview.client.сhecking;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3i;

import java.util.ArrayList;
import java.util.List;

public class Raycast {
    public static BlockState cast(Ray ray, ClientWorld level, List<Block> ignoredBlocks, List<ITag.INamedTag<Block>> ignoredTags){
        while (ray.length() < ray.maxLength){
            ray.increase();

            boolean f = true;
            Vector3i end = new Vector3i(MathHelper.floor(ray.getEnd().x()),MathHelper.floor(ray.getEnd().y()),MathHelper.floor(ray.getEnd().z()));
            BlockState block = level.getBlockState(new BlockPos(end));
            if(block.is(Blocks.AIR)) {
                f = false;
            }
            for (Block ignored : ignoredBlocks) {
                if(block.is(ignored))
                    f = false;
            }
            for (ITag.INamedTag<Block> tag : ignoredTags) {
                if(block.is(tag))
                    f = false;
            }



            if(f) return block;
        }
        return null;
    }
    public static BlockState cast(Ray ray, ClientWorld level){
        return cast(ray,level,new ArrayList<>(),new ArrayList<>());
    }
}
