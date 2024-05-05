package studio.magemonkey.enchant.potion.hit.inflict;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Blinds enemies on hit
 */
public class Blind extends PotionInflict {
    public Blind() {
        super("Blind", "Blinds enemies on hit");

        addNaturalItems(ItemSet.PICKAXES.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.BLINDNESS;
    }
}
