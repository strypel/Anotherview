package com.strypel.anotherview.event;

import com.strypel.anotherview.Anotherview;
import com.strypel.anotherview.client.KeyInit;
import com.strypel.anotherview.client.view.ViewController;
import com.strypel.anotherview.client.view.ViewControllerImpl;
import com.strypel.anotherview.client.view.ViewControllerMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

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
            if (event.phase == TickEvent.Phase.END) {
                Minecraft mc = Minecraft.getInstance();
                ClientPlayerEntity player = mc.player;
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
