package com.strypel.anotherview.mixin;

import com.strypel.anotherview.client.gui.screen.AVSettingsScreen;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(IngameMenuScreen.class)
public class PauseScreenMixin extends Screen {
    protected PauseScreenMixin(ITextComponent p_96550_) {
        super(p_96550_);
    }

    @Inject(method = "createPauseMenu", at = @At(value = "TAIL"))
    private void createPauseScreen(CallbackInfo ci){
        if(AVSettingsScreen.TEST_PAUSE_MENU_BUTTON){
            this.addButton(new Button(this.width / 2 + 4 + 98 + 2, this.height / 4 + 72 + -16, 20, 20, ITextComponent.nullToEmpty(""), (p_96331_) -> {
                this.minecraft.setScreen(new AVSettingsScreen(new TranslationTextComponent("screen.anotherview.avsettings")));
            }));
        }
    }

}
