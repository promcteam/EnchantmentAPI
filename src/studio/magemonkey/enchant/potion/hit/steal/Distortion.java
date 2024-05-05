package studio.magemonkey.enchant.potion.hit.steal;

import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;

public class Distortion extends PotionSteal {

    public Distortion() {
        super("Distortion", "Grants invisibility on hit");

        addNaturalItems(ItemSet.HOES.getItems());
    }

    public PotionEffectType type() {
        return PotionEffectType.INVISIBILITY;
    }
}
