package com.strypel.anotherview.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ActiveRenderInfo.class)
public abstract class CameraMixin {

    private double dist = 0;
    private boolean needReset = false;
    private long lastTime = 0;
    private double speed = 4;

    @Shadow protected abstract double getMaxZoom(double p_90567_);

    @Inject(method = "tick",at = @At(value = "TAIL"))
    public void tick(CallbackInfo ci){
        if(!needReset){
            if(Minecraft.getInstance().options.getCameraType().isFirstPerson()){
                this.dist = 0;
            }
        }
    }

    @Redirect(method = "setup", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ActiveRenderInfo;getMaxZoom(D)D"))
    public double getMaxZoom(ActiveRenderInfo instance, double f){
        long time = System.nanoTime();
        double deltaTime = ((time - lastTime) / 1000000000.0);
        this.lastTime = time;

        if(dist < f){
            this.dist += speed * deltaTime;
        }

        return this.getMaxZoom(dist);
    }
}
