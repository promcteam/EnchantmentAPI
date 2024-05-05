package studio.magemonkey.enchant.potion.damaged.reflect;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Applies weakness when hit by an enemy
 */
public class Demoralizing extends PotionReflect {
    public Demoralizing() {
        super("Demoralizing", "Lowers damage of enemies when hit");

        addNaturalItems(ItemSet.CHESTPLATES.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.WEAKNESS;
    }
}
