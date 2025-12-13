package com.mrrockis.simplefullbright;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

    public static final String MOD_ID = "simplefullbright";
    public static final String MOD_NAME = "Simple Fullbright";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static final KeyMapping.Category KEY_CATEGORY =
            new KeyMapping.Category(Identifier.fromNamespaceAndPath("simplefullbright", "main"));

    public static final KeyMapping TOGGLE_KEY = new KeyMapping(
            "key.simplefullbright.toggle",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            KEY_CATEGORY
    );
}