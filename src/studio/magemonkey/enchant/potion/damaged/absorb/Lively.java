package studio.magemonkey.enchant.potion.damaged.absorb;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Gains movement speed when hit
 */
public class Lively extends PotionAbsorb {

    public Lively() {
        super("Lively", "Grants bonus speed when hit");

        addNaturalItems(ItemSet.BOOTS.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.SPEED;
    }
}
