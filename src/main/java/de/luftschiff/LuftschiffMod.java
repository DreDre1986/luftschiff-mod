package de.luftschiff;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuftschiffMod implements ModInitializer {
	public static final String MOD_ID = "luftschiff";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		ModBlocks.init();

		// Bloecke in den Creative-Tab "Funktionale Bloecke" einsortieren.
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
			entries.accept(ModBlocks.BALLOON_BLOCK);
			entries.accept(ModBlocks.PROPELLER_BLOCK);
			entries.accept(ModBlocks.HELM_BLOCK);
		});

		LOGGER.info("Luftschiff-Mod geladen.");
	}
}
