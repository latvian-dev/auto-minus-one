package dev.latvian.mods.autominusone;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.ModConfigSpec;

@Mod(value = AutoMinusOne.ID, dist = Dist.CLIENT)
public class AutoMinusOne {
	public static final String ID = "autominusone";

	public static AutoMinusOne instance;

	public final AutoMinusOneConfig config;
	private boolean uninitialized;
	private boolean disabled;
	private int minScale;
	private boolean ignoreUnicode;
	private boolean lock;

	public AutoMinusOne(ModContainer container) {
		instance = this;

		var configPair = new ModConfigSpec.Builder().configure(AutoMinusOneConfig::new);
		this.config = configPair.getLeft();
		container.registerConfig(ModConfig.Type.CLIENT, configPair.getRight(), "auto-minus-one-client.toml");
		container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

		this.uninitialized = true;
	}

	public boolean updateConfig() {
		var prevDisabled = disabled;
		var prevMinScale = minScale;
		var prevIgnoreUnicode = ignoreUnicode;
		var prevLock = lock;

		disabled = config.disabled.getAsBoolean();
		minScale = config.minScale.getAsInt();
		ignoreUnicode = config.ignoreUnicode.getAsBoolean();
		lock = config.lock.getAsBoolean();

		return prevDisabled != disabled || prevMinScale != minScale || prevIgnoreUnicode != ignoreUnicode || prevLock != lock;
	}

	public int calculateScale(int maxScale, boolean enforceUnicode, int w, int h) {
		if (uninitialized) {
			uninitialized = false;
			updateConfig();
		}

		if (disabled) {
			return -1;
		}

		if (maxScale != 0 && lock) {
			maxScale = 0;
		}

		if (maxScale != 0) {
			return -1;
		}

		int scale = 1;

		while (scale < w && scale < h && w / (scale + 1) >= 320 && h / (scale + 1) >= 240) {
			scale++;
		}

		scale = Math.max(minScale, scale - 1);

		if (enforceUnicode && scale % 2 != 0 && !ignoreUnicode) {
			scale++;
		}

		return scale;
	}
}
