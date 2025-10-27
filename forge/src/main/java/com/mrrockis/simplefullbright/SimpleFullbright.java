package com.mrrockis.simplefullbright;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Constants.MOD_ID)
public class SimpleFullbright {
    public SimpleFullbright() {
        // Use Forge to bootstrap the Common mod.
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            MinecraftForge.EVENT_BUS.register(this);
        }
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(Constants.TOGGLE_KEY);
        Constants.LOG.info("Registered Keybinds for " + Constants.MOD_ID);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent.Post ignored) {
        Minecraft client = Minecraft.getInstance();
        if (client.player != null) {
            CommonClass.handleKeyInput(client);
        }
    }
}