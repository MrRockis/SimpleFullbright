package com.mrrockis.simplefullbright;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(Constants.MOD_ID)
public class SimpleFullbright {
    public SimpleFullbright(IEventBus eventBus, Dist dist) {
        if (dist.isClient()) {
            eventBus.addListener(this::onRegisterKeybinds);
            NeoForge.EVENT_BUS.register(this);
        }
    }

    private void onRegisterKeybinds(RegisterKeyMappingsEvent event) {
        event.register(Constants.TOGGLE_KEY);
        Constants.LOG.info("Registered Keybinds for " + Constants.MOD_ID);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post event) {
        Minecraft client = Minecraft.getInstance();
        if (client.player != null) {
            CommonClass.handleKeyInput(client);
        }
    }
}