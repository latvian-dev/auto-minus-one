package dev.latvian.mods.autominusone.core.mixin.client;

import com.mojang.blaze3d.platform.Window;
import dev.latvian.mods.autominusone.AutoMinusOne;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Window.class)
public class WindowMixin {
	@Shadow
	private int framebufferWidth;

	@Shadow
	private int framebufferHeight;

	@Inject(method = "calculateScale", at = @At("HEAD"), cancellable = true)
	private void vl$calculateScale(int maxScale, boolean enforceUnicode, CallbackInfoReturnable<Integer> cir) {
		int s = AutoMinusOne.instance.calculateScale(maxScale, enforceUnicode, framebufferWidth, framebufferHeight);

		if (s != -1) {
			cir.setReturnValue(s);
		}
	}
}
