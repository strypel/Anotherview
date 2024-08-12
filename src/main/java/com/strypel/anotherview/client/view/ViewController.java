package com.strypel.anotherview.client.view;

import com.github.exopandora.shouldersurfing.api.model.Perspective;
import com.github.exopandora.shouldersurfing.client.ShoulderSurfingImpl;
import com.mojang.math.Vector3f;
import com.strypel.anotherview.Anotherview;
import com.strypel.anotherview.client.сhecking.Ray;
import com.strypel.anotherview.client.сhecking.Raycast;
import com.strypel.anotherview.config.AnotherviewClientConfigs;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ViewController {
    private ViewControllerMode MODE;
    private CameraType lastCameraType;
    private List<Block> ignoredBlocks = List.of(

    );
    private List<TagKey<Block>> ignoredTags = List.of(
            BlockTags.LEAVES
    );
    private boolean shoulders = false;
    public double rayLength = 3;
    public boolean firstPersonView = false;
    public boolean ignoreFoliage = true;

    public ViewController(ViewControllerMode mode,double rayLength){
        this.MODE = mode;
        this.rayLength = rayLength;
    }

    public void checkCeiling(){
        if(Minecraft.getInstance().level != null && Minecraft.getInstance().player != null){
            ClientLevel level = Minecraft.getInstance().level;
            LocalPlayer player = Minecraft.getInstance().player;

            double playerHeight = player.getBbHeight();
            Vec3 start = new Vec3(player.position().x,player.position().y + playerHeight,player.position().z);

            Ray ray = new Ray(start, new Vec3(Vector3f.YP).normalize(),rayLength);
            BlockState state = Raycast.cast(ray,level,this.ignoredBlocks,this.ignoreFoliage ? this.ignoredTags : new ArrayList<>());

            if(state != null){
                if(!firstPersonView) {
                    CameraType nowCameraType = Minecraft.getInstance().options.getCameraType();
                    if(CameraType.FIRST_PERSON != nowCameraType){
                        if(ModList.get().isLoaded("shouldersurfing")){
                            if(ShoulderSurfingImpl.getInstance().isShoulderSurfing()){
                                shoulders = true;
                            }
                        }
                        lastCameraType = nowCameraType;
                        Minecraft.getInstance().options.setCameraType(CameraType.FIRST_PERSON);
                        firstPersonView = true;
                    }
                }
            } else {
                if(firstPersonView) {
                    CameraType newCameraType = lastCameraType != null ? lastCameraType : CameraType.THIRD_PERSON_BACK;
                    Minecraft.getInstance().options.setCameraType(newCameraType);
                    if(ModList.get().isLoaded("shouldersurfing")){
                        if(shoulders){
                            ShoulderSurfingImpl.getInstance().changePerspective(Perspective.SHOULDER_SURFING);
                            shoulders = false;
                        }
                    }
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

    public CameraType getLastCameraType() {
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
