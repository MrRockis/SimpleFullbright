package com.mrrockis.simplefullbright;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

public class SimpleFullbright implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyMappingHelper.registerKeyMapping(Constants.TOGGLE_KEY);
        Constants.LOG.info("Registered Keybinds for " + Constants.MOD_ID);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                CommonClass.handleKeyInput(client);
            }
        });
    }
}
