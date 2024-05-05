package studio.magemonkey.enchant.potion.hit.steal;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Gains movement speed on attack
 */
public class Fervor extends PotionSteal {

    public Fervor() {
        super("Fervor", "Grants bonus speed on hit");

        addNaturalItems(ItemSet.SWORDS.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.SPEED;
    }
}
