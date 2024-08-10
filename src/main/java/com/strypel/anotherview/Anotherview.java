package com.strypel.anotherview;

import com.strypel.anotherview.config.AnotherviewClientConfigs;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Mod(Anotherview.MODID)
public class Anotherview
{
    public static final String MODID = "anotherview";
    private static final Logger LOGGER = LoggerFactory.getLogger(Anotherview.class);
    public Anotherview() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, AnotherviewClientConfigs.SPEC,"anotherview-client.toml");
    }
}