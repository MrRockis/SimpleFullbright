package com.mrrockis.simplefullbright;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(Minecraft.getInstance().gameDirectory, "config/simplefullbright.json");

    public static double gammaValue = CommonClass.getHighGamma();

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                ConfigData data = GSON.fromJson(reader, ConfigData.class);
                if (data != null) {
                    gammaValue = data.gammaValue;
                }
            } catch (IOException e) {
                Constants.LOG.error("Failed to load SimpleFullbright config", e);
            }
        } else {
            save();
        }
    }

    public static void save() {
        try {
            File configDir = CONFIG_FILE.getParentFile();
            if (!configDir.exists()) {
                configDir.mkdirs();
            }

            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
                ConfigData data = new ConfigData();
                data.gammaValue = gammaValue;
                GSON.toJson(data, writer);
            }
        } catch (IOException e) {
            Constants.LOG.error("Failed to save SimpleFullbright config", e);
        }
    }

    private static class ConfigData {
        double gammaValue = 1.0;
    }
}