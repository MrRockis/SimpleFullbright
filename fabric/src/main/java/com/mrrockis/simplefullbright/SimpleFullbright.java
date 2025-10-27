package com.mrrockis.simplefullbright;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class SimpleFullbright implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CommonClass.init();

        KeyBindingHelper.registerKeyBinding(Constants.TOGGLE_KEY);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                CommonClass.handleKeyInput(client);
            }
        });
    }
}
