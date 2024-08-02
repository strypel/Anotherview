package com.strypel.anotherview;

import com.mojang.logging.LogUtils;
import com.strypel.anotherview.client.view.ViewController;
import com.strypel.anotherview.client.view.ViewControllerImpl;
import com.strypel.anotherview.client.view.ViewControllerMode;
import com.strypel.anotherview.config.AnotherviewClientConfigs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;



@Mod(Anotherview.MODID)
public class Anotherview
{
    public static final String MODID = "anotherview";
    private static final Logger LOGGER = LogUtils.getLogger();
    public Anotherview() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, AnotherviewClientConfigs.SPEC,"anotherview-client.toml");
    }
}