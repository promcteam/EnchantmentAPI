package studio.magemonkey.enchant.potion.passive;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Passively grants bonus jump height
 */
public class Jump extends PotionPassive {
    public Jump() {
        super("Jump", "Passively grants jump bonus");

        addNaturalItems(ItemSet.BOOTS.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.getByName("JUMP") != null
                ? PotionEffectType.getByName("JUMP")
                : PotionEffectType.getByName("JUMP_BOOST");
    }
}
