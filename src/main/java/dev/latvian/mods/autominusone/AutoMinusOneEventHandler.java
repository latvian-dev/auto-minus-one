package dev.latvian.mods.autominusone;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = AutoMinusOne.ID, value = Dist.CLIENT)
public class AutoMinusOneEventHandler {
	@SubscribeEvent
	public static void preClientTick(ClientTickEvent.Pre event) {
		if (AutoMinusOne.instance.updateConfig()) {
			Minecraft.getInstance().resizeGui();
		}
	}
}
