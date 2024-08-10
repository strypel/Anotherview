package com.strypel.anotherview.client.view;

import com.strypel.anotherview.client.сhecking.Ray;
import com.strypel.anotherview.client.сhecking.Raycast;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ViewController {
    private ViewControllerMode MODE;
    private PointOfView lastCameraType;
    private List<Block> ignoredBlocks = List.of(

    );
    private List<ITag.INamedTag<Block>> ignoredTags = List.of(
            BlockTags.LEAVES
    );
    public double rayLength = 3;
    public boolean firstPersonView = false;
    public boolean ignoreFoliage = true;

    public ViewController(ViewControllerMode mode,double rayLength){
        this.MODE = mode;
        this.rayLength = rayLength;
    }

    public void checkCeiling(){
        if(Minecraft.getInstance().level != null && Minecraft.getInstance().player != null){
            ClientWorld level = Minecraft.getInstance().level;
            ClientPlayerEntity player = Minecraft.getInstance().player;

            double playerHeight = player.getBbHeight();
            Vector3f start = new Vector3f((float) player.position().x, (float) (player.position().y + playerHeight), (float) player.position().z);

            Ray ray = new Ray(start, Vector3f.YP,rayLength);
            BlockState state = Raycast.cast(ray,level,this.ignoredBlocks,this.ignoreFoliage ? this.ignoredTags : new ArrayList<>());

            if(state != null){
                if(!firstPersonView) {
                    PointOfView nowCameraType = Minecraft.getInstance().options.getCameraType();
                    if(PointOfView.FIRST_PERSON.FIRST_PERSON != nowCameraType){
                        lastCameraType = nowCameraType;
                        Minecraft.getInstance().options.setCameraType(PointOfView.FIRST_PERSON);
                        firstPersonView = true;
                    }
                }
            } else {
                if(firstPersonView) {
                    PointOfView newCameraType = lastCameraType != null ? lastCameraType : PointOfView.THIRD_PERSON_BACK;
                    Minecraft.getInstance().options.setCameraType(newCameraType);
                    firstPersonView = false;
                }
            }
        }
    }

    @SubscribeEvent
    public void tick(TickEvent event){
        if(MODE != null){
            switch (MODE){
                case OFF -> {}
                case RAY_CAST -> checkCeiling();
            }
        }

    }

    public void setMode(ViewControllerMode mode) {
        if(mode != null){
            this.MODE = mode;
        }
    }
    public ViewControllerMode getMode(){
        return this.MODE;
    }

    public PointOfView getLastCameraType() {
        return lastCameraType;
    }

    public void setIgnoredBlocks(List<Block> ignoredBlocks) {
        if(ignoredBlocks != null)
            this.ignoredBlocks = ignoredBlocks;
    }

    public List<Block> getIgnoredBlocks() {
        return ignoredBlocks;
    }
}
