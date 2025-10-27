package com.mrrockis.simplefullbright;

import net.minecraft.client.Minecraft;

public class CommonClass {

    private static boolean fullbrightEnabled = false;

    private static double gamma = 1.0;
    private static double originalGamma = -1;

    public static boolean isFullbrightEnabled() {
        return fullbrightEnabled;
    }

    public static double getGamma() {
        return gamma;
    }

    public static float getHighGamma() {
        return 100.0f;
    }

    public static void handleKeyInput(Minecraft client) {
        while (Constants.TOGGLE_KEY.consumeClick()) {
            toggleFullbright(client);
        }
    }

    public static void toggleFullbright(Minecraft client) {
        fullbrightEnabled = !fullbrightEnabled;
        Constants.LOG.info("Toggling Fullbright: " + fullbrightEnabled);

        if (fullbrightEnabled) {
            // Store original gamma if we haven't already
            if (originalGamma == -1.0) {
                originalGamma = client.options.gamma().get();
            }
        } else {
            // Restore original gamma
            if (originalGamma != -1.0) {
                client.options.gamma().set(originalGamma);
                originalGamma = -1.0;
            }
        }
    }
}