package studio.magemonkey.enchant.potion.hit.inflict;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Applies poison on hit
 */
public class Poison extends PotionInflict {
    public Poison() {
        super("Poison", "Poisons enemies on hit");

        addNaturalItems(ItemSet.HOES.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.POISON;
    }
}