package studio.magemonkey.enchant.potion.hit.inflict;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Applies weakness on hit
 */
public class Weakness extends PotionInflict {
    public Weakness() {
        super("Weakness", "Lowers enemies' damage on hit");

        addNaturalItems(ItemSet.AXES.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.WEAKNESS;
    }
}
