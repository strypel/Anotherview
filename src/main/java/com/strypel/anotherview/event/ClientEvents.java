package com.strypel.anotherview.event;

import com.strypel.anotherview.Anotherview;
import com.strypel.anotherview.client.KeyInit;
import com.strypel.anotherview.client.view.ViewController;
import com.strypel.anotherview.client.view.ViewControllerImpl;
import com.strypel.anotherview.client.view.ViewControllerMode;
import com.strypel.anotherview.config.AnotherviewClientConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.spongepowered.asm.mixin.injection.Inject;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Anotherview.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
    public class ClientModEvents{
        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event){
            ViewControllerImpl.setViewController(new ViewController(ViewControllerMode.RAY_CAST,3));
            KeyInit.init();
        }
    }

    @Mod.EventBusSubscriber(modid = Anotherview.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)
    public final class ClientForgeEvents{
        private static boolean wflag = false;

        //@SuppressWarnings("resource")
        @SubscribeEvent
        public static void clientTick(TickEvent.ClientTickEvent event){
            KeyInit.tick();

            if (event.phase == TickEvent.Phase.END) {
                Minecraft mc = Minecraft.getInstance();
                LocalPlayer player = mc.player;
                if (player != null && player.isAlive()) {
                    if(!wflag){
                        ViewControllerImpl.load();
                        wflag = true;
                    }
                } else {
                    if(wflag){
                        ViewControllerImpl.save();
                        wflag = false;
                    }
                }
            }
        }
    }


}
