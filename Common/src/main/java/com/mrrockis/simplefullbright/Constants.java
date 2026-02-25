package com.mrrockis.simplefullbright;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {

    public static final String MOD_ID = "simplefullbright";
    public static final String MOD_NAME = "Simple Fullbright";
    public static final Logger LOG = LogManager.getLogger(MOD_NAME);

    public static final String KEY_CATEGORY = "key.category.simplefullbright.main";

    public static final KeyMapping TOGGLE_KEY = new KeyMapping(
            "key.simplefullbright.toggle",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            KEY_CATEGORY
    );
}