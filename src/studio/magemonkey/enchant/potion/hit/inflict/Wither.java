package studio.magemonkey.enchant.potion.hit.inflict;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Applies wither on hit
 */
public class Wither extends PotionInflict {
    public Wither() {
        super("Wither", "Applies wither on hit");

        addNaturalItems(ItemSet.HOES.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.WITHER;
    }
}
