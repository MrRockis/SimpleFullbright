package com.mrrockis.simplefullbright.mixin;

import com.mrrockis.simplefullbright.CommonClass;
import com.mrrockis.simplefullbright.Config;
import com.mrrockis.simplefullbright.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Constructor;
import java.util.function.Consumer;

@Mixin(Options.class)
public class MixinOptions {
    @Shadow
    @Final
    @Mutable
    private OptionInstance<Double> gamma;

    @Shadow
    protected Minecraft minecraft;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onConstruct(CallbackInfo ci) {
        // Load the saved gamma value from config
        Config.load();

        try {
            double highGamma = CommonClass.getHighGamma();

            Object extended = OptionInstance.UnitDouble.INSTANCE.xmap(
                    base -> base * highGamma,
                    value -> value / highGamma
            );

            OptionInstance.CaptionBasedToString<Double> caption = (captionComponent, val) -> {
                int i = (int) (val * 100.0);
                if (i == 0) {
                    return Component.translatable("options.gamma.min");
                } else if (i == 50) {
                    return Component.translatable("options.gamma.default");
                } else if (i == 100) {
                    return Component.translatable("options.gamma.max");
                } else if (i == (CommonClass.getHighGamma() * 100)) {
                    return Component.literal("Fullbright");
                } else {
                    return Component.literal(i + "%");
                }
            };

            // Find a matching constructor of OptionInstance with 6 params and invoke via reflection
            Constructor<?>[] ctors = OptionInstance.class.getDeclaredConstructors();
            Constructor<?> target = null;
            for (Constructor<?> c : ctors) {
                if (c.getParameterCount() == 6) {
                    target = c;
                    break;
                }
            }

            if (target == null) {
                Constants.LOG.warn("Could not find OptionInstance constructor with 6 parameters to replace gamma option");
                return;
            }

            target.setAccessible(true);

            Consumer<Double> onGammaChange = (newValue) -> {
                Config.gammaValue = newValue;
                Config.save();
            };

            Object instance = target.newInstance(
                    "options.gamma",
                    OptionInstance.noTooltip(),
                    caption,
                    extended,
                    Config.gammaValue,
                    onGammaChange
            );

            this.gamma = (OptionInstance<Double>) instance;
            Constants.LOG.info("Replaced Options.gamma with extended range (up to " + (highGamma * 100) + "%).");
            Constants.LOG.info("Gamma set to: " + (Config.gammaValue * 100) + "%");
        } catch (Throwable t) {
            Constants.LOG.warn("Failed to replace gamma option for extended range", t);
        }
    }
}
