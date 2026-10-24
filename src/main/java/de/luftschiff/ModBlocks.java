package de.luftschiff;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Registriert alle Bloecke (und ihre Block-Items) des Mods.
 * Stufe 1: reine Bauteile. Die Flug-Logik kommt in Stufe 2.
 */
public final class ModBlocks {
	private ModBlocks() {}

	/** Ballon: leicht, haelt das Schiff spaeter in der Luft. */
	public static final Block BALLOON_BLOCK = register("balloon_block", Block::new,
			BlockBehaviour.Properties.of()
					.strength(0.8f)
					.sound(SoundType.WOOL));

	/** Propeller: spaeter der Antrieb. */
	public static final Block PROPELLER_BLOCK = register("propeller_block", Block::new,
			BlockBehaviour.Properties.of()
					.strength(2.0f)
					.sound(SoundType.WOOD)
					.noOcclusion());

	/** Steuerpult: hier setzt man sich spaeter hin und steuert. */
	public static final Block HELM_BLOCK = register("helm_block", Block::new,
			BlockBehaviour.Properties.of()
					.strength(2.5f)
					.sound(SoundType.WOOD));

	private static Block register(String name, Function<BlockBehaviour.Properties, Block> factory,
			BlockBehaviour.Properties properties) {
		// Seit 1.21.2 muss jedes Block-/Item-Property seinen Registry-Key kennen.
		ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, LuftschiffMod.id(name));
		Block block = factory.apply(properties.setId(blockKey));
		Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, LuftschiffMod.id(name));
		BlockItem item = new BlockItem(block,
				new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
		Registry.register(BuiltInRegistries.ITEM, itemKey, item);

		return block;
	}

	/** Erzwingt das Laden der Klasse, damit die statischen Felder registriert werden. */
	public static void init() {}
}
