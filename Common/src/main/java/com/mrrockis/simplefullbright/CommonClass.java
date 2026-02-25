package com.mrrockis.simplefullbright;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;

public class CommonClass {
    private static boolean fullbrightEnabled = false;
    private static double originalGamma = 1.0;

    public static boolean isFullbrightEnabled() {
        return fullbrightEnabled;
    }

    public static void setFullbrightEnabled(boolean value) {
        fullbrightEnabled = value;
    }

    public static int getGammaPercentage(Minecraft client) {
        return (int) (client.options.gamma * 100);
    }

    public static float getHighGamma() {
        return 15.0f;
    }

    public static void handleKeyInput(Minecraft client) {
        while (Constants.TOGGLE_KEY.consumeClick()) {
            toggleFullbright(client);
        }
    }

    public static void toggleFullbright(Minecraft client) {
        fullbrightEnabled = !fullbrightEnabled;
        Constants.LOG.info("Toggling Fullbright: " + isFullbrightEnabled());

        if (isFullbrightEnabled()) {
            // Store original gamma
            originalGamma = client.options.gamma;

            client.options.gamma = CommonClass.getHighGamma();
            sendActionBar(client, "Gamma: " + getGammaPercentage(client) + "%");
        } else {
            // Restore original gamma
            // Check if original gamma is maxed out
            if(originalGamma >= CommonClass.getHighGamma()) {
                originalGamma = 1.0;
            }

            client.options.gamma = originalGamma;
            sendActionBar(client, "Gamma: " + getGammaPercentage(client) + "%");
        }

        Config.save();
    }

    public static void sendActionBar(Minecraft client, String message) {
        if (client.player != null) {
            client.player.displayClientMessage(new TextComponent(message).withStyle(ChatFormatting.GOLD), true);
        }
    }
}