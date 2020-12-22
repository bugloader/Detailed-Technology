package detailedTechnology;

import detailedTechnology.blockEntity.BrickCrucibleEntity;
import detailedTechnology.blockEntity.StoneMileEntity;
import detailedTechnology.blocks.*;
import detailedTechnology.gui.CrucibleScreenHandler;
import detailedTechnology.gui.StoneMileScreenHandler;
import detailedTechnology.items.ClaySmallCrucibleWithLiquid;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class DetailedTechnology implements ModInitializer {

	public static final String MOD_ID = "dt";



	public static ScreenHandlerType<CrucibleScreenHandler> crucibleScreenHandler;
	public static ScreenHandlerType<StoneMileScreenHandler> stoneMileScreenHandler;

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier("tutorial", "general"),
			() -> new ItemStack(Blocks.COBBLESTONE));

	public static final ItemGroup OTHER_GROUP = FabricItemGroupBuilder.create(
			new Identifier("tutorial", "other"))
			.icon(() -> new ItemStack(Items.BOWL))
			.build();

	public static final Item copperIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item copperDrop = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item copperNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item copperSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item tinIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item tinNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item tinSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item bronzeIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item bronzeNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item ironSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));

	public static final Item wetClaySmallCrucible = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item claySmallCrucible = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item claySmallCrucibleWithCopper = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item claySmallCrucibleWithTin = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item claySmallCrucibleWithBronze = new Item(new FabricItemSettings().group(ITEM_GROUP));

	public static final ClaySmallCrucibleWithLiquid claySmallCrucibleWithMeltingCopper
			= new ClaySmallCrucibleWithLiquid("copper");
	public static final ClaySmallCrucibleWithLiquid claySmallCrucibleWithMeltingTin
			= new ClaySmallCrucibleWithLiquid("tin");
	public static final ClaySmallCrucibleWithLiquid claySmallCrucibleWithMeltingBronze
			= new ClaySmallCrucibleWithLiquid("bronze");

	public static BlockEntityType<BrickCrucibleEntity> brickCrucibleEntity;
	public static BlockEntityType<StoneMileEntity> stoneMileEntity;

	public static final CopperOreBlock copperOreBlock = new CopperOreBlock();
	public static final CopperBlock copperBlock = new CopperBlock();
	public static final TinOreBlock tinOreBlock = new TinOreBlock();
	public static final TinBlock tinBlock = new TinBlock();
	public static final BronzeBlock bronzeBlock = new BronzeBlock();

	public static final StoneMileRunner stoneMileRunner = new StoneMileRunner();

	public static final BrickCrucible brickCrucible = new BrickCrucible();
	public static final StoneMile stoneMile = new StoneMile();

	public static final ClayModelIngot clayModelIngot = new ClayModelIngot();


	private static ConfiguredFeature<?, ?> oreCopperOverWorld = Feature.ORE.configure(new OreFeatureConfig(
			OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,copperOreBlock.getDefaultState(),32))
			.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(40,40,60)))
			.spreadHorizontally().repeat(4);
	private static ConfiguredFeature<?, ?> oreTinOverWorld = Feature.ORE.configure(new OreFeatureConfig(
			OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,tinOreBlock.getDefaultState(),16))
			.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(30,30,55)))
			.spreadHorizontally().repeat(4);

	@Override
	public void onInitialize() {

		brickCrucibleEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":brick_crucible",
				BlockEntityType.Builder.create(BrickCrucibleEntity::new,brickCrucible).build(null));
		stoneMileEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":stone_mile",
				BlockEntityType.Builder.create(StoneMileEntity::new,stoneMile).build(null));

		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"copper_ore"),copperOreBlock);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "copper_ore"),
				new BlockItem(copperOreBlock, new FabricItemSettings().group(ITEM_GROUP)));

		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"copper_block"),copperBlock);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "copper_block"),
				new BlockItem(copperBlock, new FabricItemSettings().group(ITEM_GROUP)));

		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"tin_ore"),tinOreBlock);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "tin_ore"),
				new BlockItem(tinOreBlock, new FabricItemSettings().group(ITEM_GROUP)));

		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"tin_block"),tinBlock);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "tin_block"),
				new BlockItem(tinBlock, new FabricItemSettings().group(ITEM_GROUP)));

		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"bronze_block"),bronzeBlock);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "bronze_block"),
				new BlockItem(bronzeBlock, new FabricItemSettings().group(ITEM_GROUP)));

		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"brick_crucible"),brickCrucible);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "brick_crucible"),
				new BlockItem(brickCrucible, new FabricItemSettings().group(ITEM_GROUP)));

		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"stone_mile"),stoneMile);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stone_mile"),
				new BlockItem(stoneMile, new FabricItemSettings().group(ITEM_GROUP)));

		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"stone_mile_runner"),stoneMileRunner);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stone_mile_runner"),
				new BlockItem(stoneMileRunner, new FabricItemSettings().group(ITEM_GROUP)));

		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"clay_ingot_model"),clayModelIngot);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "clay_ingot_model"),
				new BlockItem(clayModelIngot, new FabricItemSettings().group(ITEM_GROUP)));

		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"copper_ingot"), copperIngot);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"copper_drop"), copperDrop);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"copper_nugget"), copperNugget);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"copper_sharp"), copperSharp);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"tin_ingot"), tinIngot);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"tin_nugget"), tinNugget);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"tin_sharp"), tinSharp);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"bronze_ingot"), bronzeIngot);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"bronze_nugget"), bronzeNugget);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"iron_sharp"), ironSharp);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"wet_clay_small_crucible"), wetClaySmallCrucible);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"clay_small_crucible"), claySmallCrucible);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"clay_crucible_with_copper"), claySmallCrucibleWithCopper);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"clay_crucible_with_tin"), claySmallCrucibleWithTin);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"clay_crucible_with_bronze"), claySmallCrucibleWithBronze);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"clay_crucible_with_melting_copper"), claySmallCrucibleWithMeltingCopper);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"clay_crucible_with_melting_tin"), claySmallCrucibleWithMeltingTin);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"clay_crucible_with_melting_bronze"), claySmallCrucibleWithMeltingBronze);

		crucibleScreenHandler = ScreenHandlerRegistry.registerSimple(
				new Identifier(MOD_ID, "brick_crucible"),CrucibleScreenHandler::new);
		stoneMileScreenHandler = ScreenHandlerRegistry.registerSimple(
				new Identifier(MOD_ID, "stone_mile"),StoneMileScreenHandler::new);

		RegistryKey<ConfiguredFeature<?, ?>> oreCopperOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
				new Identifier(MOD_ID, "ore_copper_overworld"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreCopperOverworld.getValue(), oreCopperOverWorld);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreCopperOverworld);

		RegistryKey<ConfiguredFeature<?, ?>> oreTinOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
				new Identifier(MOD_ID, "ore_tin_overworld"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreTinOverworld.getValue(), oreTinOverWorld);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreTinOverworld);

		System.out.println("Hello detailed world!");
	}
}
