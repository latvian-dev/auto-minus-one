package dev.latvian.mods.autominusone;

import net.neoforged.neoforge.common.ModConfigSpec;

public class AutoMinusOneConfig {
	public final ModConfigSpec.BooleanValue disabled;
	public final ModConfigSpec.IntValue minScale;
	public final ModConfigSpec.BooleanValue ignoreUnicode;
	public final ModConfigSpec.BooleanValue lock;

	public AutoMinusOneConfig(ModConfigSpec.Builder builder) {
		this.disabled = builder.comment("Disable this mod").define("disabled", false);
		this.minScale = builder.comment("When window size is very small, it will not go lower than this scale").defineInRange("min_scale", 2, 1, 4);
		this.ignoreUnicode = builder.comment("When unicode font is enabled it will automatically upscale to nearest whole scale").define("ignore_unicode", false);
		this.lock = builder.comment("Prevents you from changing it manually").define("lock", true);
	}
}
