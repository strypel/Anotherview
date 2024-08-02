package com.strypel.anotherview.config;

import com.strypel.anotherview.client.view.ViewControllerMode;
import net.minecraftforge.common.ForgeConfigSpec;

public class AnotherviewClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> MODE;

    static {
        BUILDER.push("View controller settings");

        // HERE DEFINE YOUR CONFIGS
        MODE = BUILDER.comment("View controller operating mode").define("Mode", ViewControllerMode.RAY_CAST.getTITLE());

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}