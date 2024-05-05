package studio.magemonkey.enchant.potion.damaged.reflect;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Applies blinding when hit by an enemy
 */
public class Brilliance extends PotionReflect {
    public Brilliance() {
        super("Brilliance", "Blinds foes when hit");

        addNaturalItems(ItemSet.HELMETS.getItems());
    }

    /**
     * @return potion type applied by this potion
     */
    public PotionEffectType type() {
        return PotionEffectType.BLINDNESS;
    }
}
