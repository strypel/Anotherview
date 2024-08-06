package com.strypel.anotherview.event;

import com.strypel.anotherview.Anotherview;
import com.strypel.anotherview.client.KeyBinding;
import com.strypel.anotherview.client.view.ViewController;
import com.strypel.anotherview.client.view.ViewControllerImpl;
import com.strypel.anotherview.client.view.ViewControllerMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
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
        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.SETTINGS_KEY);
        }
    }

    @Mod.EventBusSubscriber(modid = Anotherview.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)
    public final class ClientForgeEvents{
        private static boolean wflag = false;

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.SETTINGS_KEY.consumeClick()) {
                //Minecraft.getInstance().setScreen(new AVSettingsScreen(new TranslatableComponent("screen.anotherview.avsettings")));
                try {
                    ViewController controller = ViewControllerImpl.getViewController();
                    ViewControllerImpl.getViewController().setMode(ViewControllerMode.getNextBy(controller.getMode()));
                    Minecraft mc = Minecraft.getInstance();
                    LocalPlayer player = mc.player;
                    player.displayClientMessage(Component.nullToEmpty(Component.translatable("message.change_mode").getString() + ": §e" + ViewControllerImpl.getViewController().getMode().getTITLE()),true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //@SuppressWarnings("resource")
        @SubscribeEvent
        public static void clientTick(TickEvent.ClientTickEvent event){

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
