package studio.magemonkey.enchant.potion.damaged.reflect;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Poisons enemies when they hit you
 */
public class Toxic extends PotionReflect {
    public Toxic() {
        super("Toxic", "Poisons enemies when hit");

        addNaturalItems(ItemSet.CHESTPLATES.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.POISON;
    }
}
