package com.mrrockis.simplefullbright.mixin;

import com.mrrockis.simplefullbright.CommonClass;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Inject(method = "getNightVisionScale", at = @At("HEAD"), cancellable = true, remap = false)
    private static void disableNightVisionVisuals(LivingEntity livingEntity, float nanoTime, CallbackInfoReturnable<Float> cir) {
        if (CommonClass.isFullbrightEnabled()) {
            cir.setReturnValue(0.0F);
        }
    }
}
