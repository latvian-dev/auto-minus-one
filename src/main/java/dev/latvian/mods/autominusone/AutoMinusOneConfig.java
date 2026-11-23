package dev.latvian.mods.autominusone;

import net.neoforged.neoforge.common.ModConfigSpec;

public class AutoMinusOneConfig {
	public static final AutoMinusOneConfig CONFIG;
	public static final ModConfigSpec CONFIG_SPEC;

	static {
		var pair = new ModConfigSpec.Builder().configure(AutoMinusOneConfig::new);
		CONFIG = pair.getLeft();
		CONFIG_SPEC = pair.getRight();
	}

	public final ModConfigSpec.BooleanValue disabled;
	public final ModConfigSpec.IntValue minScale;
	public final ModConfigSpec.BooleanValue ignoreUnicode;

	private AutoMinusOneConfig(ModConfigSpec.Builder builder) {
		this.disabled = builder.define("disabled", false);
		this.minScale = builder.defineInRange("min_scale", 2, 1, 4);
		this.ignoreUnicode = builder.define("ignore_unicode", false);
	}
}
