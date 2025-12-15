package com.mrrockis.simplefullbright.compat;

import com.mrrockis.simplefullbright.CommonClass;
import com.mrrockis.simplefullbright.Constants;
import net.caffeinemc.mods.sodium.api.config.ConfigEntryPoint;
import net.caffeinemc.mods.sodium.api.config.option.ControlValueFormatter;
import net.caffeinemc.mods.sodium.api.config.structure.ConfigBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class SodiumConfigBuilder implements ConfigEntryPoint {
    @Override
    public void registerConfigLate(ConfigBuilder builder) {
        Identifier identifier = Identifier.fromNamespaceAndPath("sodium", "general.gamma");
        int maxValue = (int) (CommonClass.getHighGamma() * 100);

        builder.registerModOptions(Constants.MOD_ID, Constants.MOD_NAME, "1.0")
                .registerOptionOverlay(identifier, builder.createIntegerOption(identifier)
                        .setRange(0, maxValue, 1)
                        .setValueFormatter(getGammaText())
                );

        Constants.LOG.info("Sodium gamma option range set to 0-" + maxValue);
    }

    private ControlValueFormatter getGammaText() {
        return (value -> {
            if (value == 0) {
                return Component.translatable("options.gamma.min");
            } else if (value == 50) {
                return Component.translatable("options.gamma.default");
            } else if (value == 100) {
                return Component.translatable("options.gamma.max");
            } else if (value == (CommonClass.getHighGamma() * 100)) {
                return Component.literal("Fullbright");
            } else {
                return Component.literal(value + "%");
            }
        });
    }
}
