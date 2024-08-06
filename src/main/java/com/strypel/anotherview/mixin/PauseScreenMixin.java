package com.strypel.anotherview.mixin;

import com.strypel.anotherview.client.gui.screen.AVSettingsScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PauseScreen.class)
public class PauseScreenMixin extends Screen {
    protected PauseScreenMixin(Component p_96550_) {
        super(p_96550_);
    }

    @Inject(method = "createPauseMenu", at = @At(value = "TAIL"))
    private void createPauseScreen(CallbackInfo ci){
        if(AVSettingsScreen.TEST_PAUSE_MENU_BUTTON){
            this.addRenderableWidget(new Button(this.width / 2 + 4 + 98 + 2, this.height / 4 + 72 + -16, 20, 20, Component.nullToEmpty(""), (p_96331_) -> {
                this.minecraft.setScreen(new AVSettingsScreen(Component.translatable("screen.anotherview.avsettings")));
            }));
        }
    }

}
