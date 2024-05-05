package studio.magemonkey.enchant.potion.damaged.reflect;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Slows enemies on hit
 */
public class Frost extends PotionReflect {
    public Frost() {
        super("Frost", "Slows enemies when hit");

        addNaturalItems(ItemSet.LEGGINGS.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.getByName("SLOW") != null
                ? PotionEffectType.getByName("SLOW")
                : PotionEffectType.getByName("SLOWNESS");
    }
}
