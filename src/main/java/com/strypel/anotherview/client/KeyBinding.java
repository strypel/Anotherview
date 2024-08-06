package com.strypel.anotherview.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_ANOTHERVIEW = "key.categories.anotherview";
    public static final String KEY_AVSETTINGS = "key.anotherview.settings";

    public static final KeyMapping SETTINGS_KEY = new KeyMapping(KEY_AVSETTINGS, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_MINUS, KEY_CATEGORY_ANOTHERVIEW);
}