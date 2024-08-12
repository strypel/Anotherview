package com.strypel.anotherview.client;

import com.github.exopandora.shouldersurfing.api.client.ShoulderSurfing;
import com.github.exopandora.shouldersurfing.client.ShoulderSurfingImpl;
import com.github.exopandora.shouldersurfing.config.Config;
import com.mojang.blaze3d.platform.InputConstants;
import com.strypel.anotherview.Anotherview;
import com.strypel.anotherview.client.view.ViewController;
import com.strypel.anotherview.client.view.ViewControllerImpl;
import com.strypel.anotherview.client.view.ViewControllerMode;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.client.ClientRegistry;

public final class KeyInit {
    public static KeyMapping avSettingsKeyMapping;
    public static final String CATEGORY_ANOTHERVIEW = "key.categories.anotherview";

    private KeyInit() {
    }

    public static void init() {
        avSettingsKeyMapping = registerKey("settings", CATEGORY_ANOTHERVIEW, InputConstants.KEY_MINUS);
    }

    private static KeyMapping registerKey(String name, String category, int keycode) {
        final var key = new KeyMapping("key." + Anotherview.MODID + "." + name, keycode, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }

    public static void tick(){
        if(KeyInit.avSettingsKeyMapping.consumeClick()){
            //Minecraft.getInstance().setScreen(new AVSettingsScreen(new TranslatableComponent("screen.anotherview.avsettings")));
            try {
                ViewController controller = ViewControllerImpl.getViewController();
                ViewControllerImpl.getViewController().setMode(ViewControllerMode.getNextBy(controller.getMode()));
                Minecraft mc = Minecraft.getInstance();
                LocalPlayer player = mc.player;
                player.displayClientMessage(Component.nullToEmpty(new TranslatableComponent("message.change_mode").getString() + ": §e" + ViewControllerImpl.getViewController().getMode().getTITLE()),true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
