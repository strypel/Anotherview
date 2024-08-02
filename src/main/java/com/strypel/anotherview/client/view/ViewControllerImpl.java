package com.strypel.anotherview.client.view;

import com.strypel.anotherview.client.view.ViewController;
import com.strypel.anotherview.config.AnotherviewClientConfigs;
import net.minecraftforge.common.MinecraftForge;

public class ViewControllerImpl {
    private static ViewController viewController;
    public static void setViewController(ViewController controller){
        if(viewController != null){
            MinecraftForge.EVENT_BUS.unregister(viewController);
        }
        MinecraftForge.EVENT_BUS.register(controller);
        viewController = controller;
    }
    public static ViewController getViewController(){
        return viewController;
    }

    public static void save(){
        AnotherviewClientConfigs.MODE.set(viewController.getMode().getTITLE());
    }

    public static void load(){
        viewController.setMode(ViewControllerMode.getModeBy(AnotherviewClientConfigs.MODE.get()));
    }
}
