package com.strypel.anotherview.event;

import com.strypel.anotherview.Anotherview;
import com.strypel.anotherview.client.KeyInit;
import com.strypel.anotherview.client.view.ViewController;
import com.strypel.anotherview.client.view.ViewControllerImpl;
import com.strypel.anotherview.client.view.ViewControllerMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Anotherview.MODID, bus = Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {
	
	@SubscribeEvent
	public static void onKeyPress(InputEvent.KeyInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.level == null) return;
		onInput(mc, event.getKey(), event.getAction());
	}
	
	@SubscribeEvent
	public static void onMouseClick(InputEvent.MouseInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.level == null) return;
		onInput(mc, event.getButton(), event.getAction());
	}
	
	private static void onInput(Minecraft mc, int key, int action) {
		if (KeyInit.avSettingsKeyBinding.consumeClick()) {
			//Minecraft.getInstance().setScreen(new AVSettingsScreen(new TranslatableComponent("screen.anotherview.avsettings")));
			try {
				ViewController controller = ViewControllerImpl.getViewController();
				ViewControllerImpl.getViewController().setMode(ViewControllerMode.getNextBy(controller.getMode()));
				ClientPlayerEntity player = mc.player;
				player.displayClientMessage(ITextComponent.nullToEmpty(new TranslationTextComponent("message.change_mode").getString() + ": §e" + ViewControllerImpl.getViewController().getMode().getTITLE()),true);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}