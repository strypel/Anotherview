package com.strypel.anotherview.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.strypel.anotherview.client.view.ViewControllerImpl;
import com.strypel.anotherview.client.view.ViewControllerMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.EditSignScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class AVSettingsScreen extends Screen {
    public static boolean TEST_PAUSE_MENU_BUTTON = false;
    public Button foliageButton;
    public AVSettingsScreen(ITextComponent p_96550_) {
        super(p_96550_);
    }

    @Override
    protected void init() {
        super.init();
        this.addWidget(new Button(this.width / 2 - 102, this.height / 4 + 24 + -16, 204, 20,
                ITextComponent.nullToEmpty(ViewControllerImpl.getViewController().getMode().getTITLE()), (p_96337_) -> {
            ViewControllerMode previous = ViewControllerImpl.getViewController().getMode();
            ViewControllerImpl.getViewController().setMode(ViewControllerMode.getNextBy(previous));
            Minecraft.getInstance().setScreen(this);
        }));
        switch (ViewControllerImpl.getViewController().getMode()){
            case OFF -> {}
            case RAY_CAST -> {
                this.addWidget(new Button(this.width / 2 - 102, this.height / 4 + 24 + -16, 204, 20,
                        ITextComponent.nullToEmpty(ViewControllerImpl.getViewController().getMode().getTITLE()), (p_96337_) -> {
                    ViewControllerMode previous = ViewControllerImpl.getViewController().getMode();
                    ViewControllerImpl.getViewController().setMode(ViewControllerMode.getNextBy(previous));
                    Minecraft.getInstance().setScreen(this);
                }));
                String foliageText = ViewControllerImpl.getViewController().ignoreFoliage ? "Ignore" : "Сollide";
                foliageButton = new Button(this.width / 2 - 102,this.height / 4 + 98 + -16,200,20,ITextComponent.nullToEmpty(foliageText),p_93751_ -> {
                    ViewControllerImpl.getViewController().ignoreFoliage = !ViewControllerImpl.getViewController().ignoreFoliage;
                    Minecraft.getInstance().setScreen(this);
                });
                this.addWidget(foliageButton);
            }
        }
        this.addWidget(new Button(this.width / 2 - 100, this.height / 6 + 168, 200, 20, DialogTexts.GUI_DONE, (p_96257_) -> {
            this.minecraft.setScreen(null);
        }));
    }

    @Override
    public void render(MatrixStack poseStack, int p_96563_, int p_96564_, float p_96565_) {
        renderBackground(poseStack);
        super.render(poseStack, p_96563_, p_96564_, p_96565_);
        this.renderScaledText(poseStack,this.title,this.width / 2, 40,16777215,1.4f,true);
        this.renderScaledText(poseStack,new TranslationTextComponent("screen.anotherview.avsettings.mode"),this.width / 2,this.height / 4 + 12 + -16,16777215,1f,true);
        switch (ViewControllerImpl.getViewController().getMode()){
            case OFF -> {}
            case RAY_CAST -> {
                this.renderScaledText(poseStack,new TranslationTextComponent("screen.anotherview.avsettings.raylength"),this.width / 2,this.height / 4 + 48 + -16,16777215,1f,true);
                this.renderScaledText(poseStack,new TranslationTextComponent("screen.anotherview.avsettings.ignorefoliage"),this.width / 2,this.height / 4 + 86 + -16,16777215,1f,true);
            }
        }
    }

    protected void renderScaledText(MatrixStack stack,ITextComponent text,int x,int y,int color,float scale,boolean centred){
        stack.pushPose();
        stack.scale(scale,scale,scale);
        if(centred){
            this.drawCenteredString(stack,this.font, text, (int) (x * (1 / scale)), (int) (y * (1 / scale)), color);
        } else {
            this.drawString(stack,this.font, text, (int) (x * (1 / scale)), (int) (y * (1 / scale)), color);
        }
        stack.popPose();
    }
    protected void renderScaledText(MatrixStack stack,ITextComponent text,int x,int y,int color,float scale){
        this.renderScaledText(stack,text,x,y,color,scale,false);
    }

    @Override
    public void removed() {
        super.removed();
        switch (ViewControllerImpl.getViewController().getMode()){
            case OFF -> {}
            case RAY_CAST -> {}
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
