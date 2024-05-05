package studio.magemonkey.enchant.potion.passive;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Passively grants night vision
 */
public class NightVision extends PotionPassive {
    public NightVision() {
        super("Night Vision", "Passively grants night vision");

        addNaturalItems(ItemSet.HELMETS.getItems());
    }

    /**
     * @return type of potion applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.NIGHT_VISION;
    }
}