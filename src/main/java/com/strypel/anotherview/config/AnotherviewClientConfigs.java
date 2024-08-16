package com.strypel.anotherview.config;

import com.strypel.anotherview.client.view.ViewControllerMode;
import net.minecraftforge.common.ForgeConfigSpec;

public class AnotherviewClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> MODE;
    public static final ForgeConfigSpec.ConfigValue<Double> RAY_LENGTH;
    public static final ForgeConfigSpec.ConfigValue<Boolean> IGNORE_FOLIAGE;

    static {
        BUILDER.push("Anotherview settings");

        // HERE DEFINE YOUR CONFIGS
        MODE = BUILDER.comment("Anotherview operating mode").define("mode", ViewControllerMode.RAY_CAST.getTITLE());
        RAY_LENGTH = BUILDER.comment("RAYCAST mode ray length").define("ray_length", 3.0);
        IGNORE_FOLIAGE = BUILDER.comment("Will the mod ignore foliage?").define("ignore_foliage", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}