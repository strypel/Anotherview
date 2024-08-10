package com.strypel.anotherview.client;

import com.strypel.anotherview.Anotherview;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.awt.event.KeyEvent;

public final class KeyInit {
    public static KeyBinding avSettingsKeyBinding;
    public static final String CATEGORY_ANOTHERVIEW = "key.categories.anotherview";

    public static void init() {
        avSettingsKeyBinding = create("settings", KeyEvent.VK_MINUS,CATEGORY_ANOTHERVIEW);

        ClientRegistry.registerKeyBinding(avSettingsKeyBinding);
    }

    private static KeyBinding create(String name, int key,String category) {
        return new KeyBinding("key." + Anotherview.MODID + "." + name, key, category);
    }
}
