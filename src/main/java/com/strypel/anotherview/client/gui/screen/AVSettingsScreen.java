package com.strypel.anotherview.client.gui.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.strypel.anotherview.client.view.ViewControllerImpl;
import com.strypel.anotherview.client.view.ViewControllerMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class AVSettingsScreen extends Screen {
    public static boolean TEST_PAUSE_MENU_BUTTON = false;
    public EditBox editbox_r;
    public Button foliageButton;
    public AVSettingsScreen(Component p_96550_) {
        super(p_96550_);
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(new Button(this.width / 2 - 102, this.height / 4 + 24 + -16, 204, 20,
                Component.nullToEmpty(ViewControllerImpl.getViewController().getMode().getTITLE()), (p_96337_) -> {
            ViewControllerMode previous = ViewControllerImpl.getViewController().getMode();
            ViewControllerImpl.getViewController().setMode(ViewControllerMode.getNextBy(previous));
            Minecraft.getInstance().setScreen(this);
        }));
        switch (ViewControllerImpl.getViewController().getMode()){
            case OFF -> {}
            case RAY_CAST -> {
                this.editbox_r = new EditBox(this.font,this.width / 2 - 102, this.height / 4 + 60 + -16,204, 20,Component.nullToEmpty("Ray length"));
                this.editbox_r.setValue(String.valueOf(ViewControllerImpl.getViewController().rayLength));
                this.addRenderableWidget(editbox_r);
                this.addRenderableWidget(new Button(this.width / 2 - 102, this.height / 4 + 24 + -16, 204, 20,
                        Component.nullToEmpty(ViewControllerImpl.getViewController().getMode().getTITLE()), (p_96337_) -> {
                    ViewControllerMode previous = ViewControllerImpl.getViewController().getMode();
                    ViewControllerImpl.getViewController().setMode(ViewControllerMode.getNextBy(previous));
                    Minecraft.getInstance().setScreen(this);
                }));
                String foliageText = ViewControllerImpl.getViewController().ignoreFoliage ? "Ignore" : "Сollide";
                foliageButton = new Button(this.width / 2 - 102,this.height / 4 + 98 + -16,200,20,Component.nullToEmpty(foliageText),p_93751_ -> {
                    ViewControllerImpl.getViewController().ignoreFoliage = !ViewControllerImpl.getViewController().ignoreFoliage;
                    Minecraft.getInstance().setScreen(this);
                });
                this.addRenderableWidget(foliageButton);
            }
        }
        this.addRenderableWidget(new Button(this.width / 2 - 100, this.height / 6 + 168, 200, 20, CommonComponents.GUI_DONE, (p_96257_) -> {
            this.minecraft.setScreen(null);
        }));
    }

    @Override
    public void render(PoseStack poseStack, int p_96563_, int p_96564_, float p_96565_) {
        renderBackground(poseStack);
        super.render(poseStack, p_96563_, p_96564_, p_96565_);
        this.renderScaledText(poseStack,this.title,this.width / 2, 40,16777215,1.4f,true);
        this.renderScaledText(poseStack,Component.translatable("screen.anotherview.avsettings.mode"),this.width / 2,this.height / 4 + 12 + -16,16777215,1f,true);
        switch (ViewControllerImpl.getViewController().getMode()){
            case OFF -> {}
            case RAY_CAST -> {
                this.renderScaledText(poseStack,Component.translatable("screen.anotherview.avsettings.raylength"),this.width / 2,this.height / 4 + 48 + -16,16777215,1f,true);
                this.renderScaledText(poseStack,Component.translatable("screen.anotherview.avsettings.ignorefoliage"),this.width / 2,this.height / 4 + 86 + -16,16777215,1f,true);
            }
        }
    }

    protected void renderScaledText(PoseStack stack,Component text,int x,int y,int color,float scale,boolean centred){
        stack.pushPose();
        stack.scale(scale,scale,scale);
        if(centred){
            this.drawCenteredString(stack,this.font, text, (int) (x * (1 / scale)), (int) (y * (1 / scale)), color);
        } else {
            this.drawString(stack,this.font, text, (int) (x * (1 / scale)), (int) (y * (1 / scale)), color);
        }
        stack.popPose();
    }
    protected void renderScaledText(PoseStack stack,Component text,int x,int y,int color,float scale){
        this.renderScaledText(stack,text,x,y,color,scale,false);
    }

    @Override
    public void removed() {
        super.removed();
        switch (ViewControllerImpl.getViewController().getMode()){
            case OFF -> {}
            case RAY_CAST -> {
                if(editbox_r.getValue().equals("hesoyam")){
                    TEST_PAUSE_MENU_BUTTON = !TEST_PAUSE_MENU_BUTTON;
                }
                try {
                    if(editbox_r != null){
                        ViewControllerImpl.getViewController().rayLength = Double.valueOf(editbox_r.getValue());
                    }
                } catch (Exception e) {
                    return;
                }
            }
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
