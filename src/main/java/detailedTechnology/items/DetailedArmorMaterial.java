package detailedTechnology.items;

import java.util.function.Supplier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

public enum DetailedArmorMaterial implements ArmorMaterial {
//	          新建的装甲材料对象有以下几个属性，按照构造时的顺序和作用分别为        材料名字（string类型的材料名字，耐久，护甲值（int数组，分别表示不同部位护甲值），附魔亲和度，装备时的声音，强度，击退抗性，合成和修复时所用的材料）
	   COPPER("copper", 13, new int[]{2, 4, 5, 1}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
		      return Ingredient.ofItems(detailedTechnology.DetailedTechnology.copperIngot);
	   });

	   private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
	   private final String name;
	   private final int durabilityMultiplier;
	   private final int[] protectionAmounts;
	   private final int enchantability;
	   private final SoundEvent equipSound;
	   private final float toughness;
	   private final float knockbackResistance;
	   private final Lazy<Ingredient> repairIngredientSupplier;

	   private DetailedArmorMaterial(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
	      this.name = name;
	      this.durabilityMultiplier = durabilityMultiplier;
	      this.protectionAmounts = protectionAmounts;
	      this.enchantability = enchantability;
	      this.equipSound = equipSound;
	      this.toughness = toughness;
	      this.knockbackResistance = knockbackResistance;
	      this.repairIngredientSupplier = new Lazy(repairIngredientSupplier);
	   }

	   public int getDurability(EquipmentSlot slot) {
	      return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
	   }

	   public int getProtectionAmount(EquipmentSlot slot) {
	      return this.protectionAmounts[slot.getEntitySlotId()];
	   }

	   public int getEnchantability() {
	      return this.enchantability;
	   }

	   public SoundEvent getEquipSound() {
	      return this.equipSound;
	   }

	   public Ingredient getRepairIngredient() {
	      return (Ingredient)this.repairIngredientSupplier.get();
	   }

	   @Environment(EnvType.CLIENT)
	   public String getName() {
	      return this.name;
	   }

	   public float getToughness() {
	      return this.toughness;
	   }

	   public float getKnockbackResistance() {
	      return this.knockbackResistance;
	   }
}
