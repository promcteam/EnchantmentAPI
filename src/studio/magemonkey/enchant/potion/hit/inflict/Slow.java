package studio.magemonkey.enchant.potion.hit.inflict;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Slows enemies on hit
 */
public class Slow extends PotionInflict {
    public Slow() {
        super("Slow", "Slows enemies on hit");

        addNaturalItems(ItemSet.HOES.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.SLOWNESS;
    }
}
