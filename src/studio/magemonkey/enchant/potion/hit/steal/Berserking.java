package studio.magemonkey.enchant.potion.hit.steal;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

public class Berserking extends PotionSteal {
    public Berserking() {
        super("Berserking", "Grants bonus damage on attack");

        addNaturalItems(ItemSet.AXES.getItems());
    }

    public PotionEffectType type() {
        return PotionEffectType.STRENGTH;
    }
}
