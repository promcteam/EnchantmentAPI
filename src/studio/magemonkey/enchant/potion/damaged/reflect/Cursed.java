package studio.magemonkey.enchant.potion.damaged.reflect;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Applies wither when hit
 */
public class Cursed extends PotionReflect {
    public Cursed() {
        super("Cursed", "Applies wither to foes when hit");

        addNaturalItems(ItemSet.CHESTPLATES.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.WITHER;
    }
}
