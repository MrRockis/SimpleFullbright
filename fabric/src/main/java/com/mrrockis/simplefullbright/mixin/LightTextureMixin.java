package com.mrrockis.simplefullbright.mixin;

import com.mrrockis.simplefullbright.CommonClass;
import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LightTexture.class)
public class LightTextureMixin {
    @ModifyVariable(
            method = "updateLightTexture",
            at = @At(value = "STORE"),
            name = "o"
    )
    private float modifyGamma(float originalGamma) {
        if (CommonClass.isFullbrightEnabled()) {
            return CommonClass.getHighGamma();
        } else {
            return originalGamma;
        }
    }
}
