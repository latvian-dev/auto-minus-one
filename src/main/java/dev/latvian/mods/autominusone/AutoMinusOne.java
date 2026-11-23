package dev.latvian.mods.autominusone;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = "autominusone", dist = Dist.CLIENT)
public class AutoMinusOne {
	public AutoMinusOne(ModContainer container) {
		container.registerConfig(ModConfig.Type.CLIENT, AutoMinusOneConfig.CONFIG_SPEC, "auto-minus-one-client.toml");
		container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
	}

	public static int calculateScale(int maxScale, boolean enforceUnicode, int w, int h) {
		var config = AutoMinusOneConfig.CONFIG;

		if (maxScale != 0 || config.disabled.getAsBoolean()) {
			return -1;
		}

		int scale = 1;

		while (scale < w && scale < h && w / (scale + 1) >= 320 && h / (scale + 1) >= 240) {
			scale++;
		}

		scale = Math.max(config.minScale.getAsInt(), scale - 1);

		if (enforceUnicode && scale % 2 != 0 && !config.ignoreUnicode.getAsBoolean()) {
			scale++;
		}

		return scale;
	}
}
