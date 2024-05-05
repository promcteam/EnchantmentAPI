package studio.magemonkey.enchant.potion.damaged.absorb;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Gain damage when hit
 */
public class Adrenaline extends PotionAbsorb {
    public Adrenaline() {
        super("Adrenaline", "Grants extra damage when hit");

        addNaturalItems(ItemSet.LEGGINGS.getItems());
    }

    /**
     * @return potion type applied by this enchantment
     */
    public PotionEffectType type() {
        return PotionEffectType.getByName("INCREASE_DAMAGE") != null
                ? PotionEffectType.getByName("INCREASE_DAMAGE")
                : PotionEffectType.getByName("STRENGTH");
    }
}
