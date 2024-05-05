package studio.magemonkey.enchant.active;

import org.bukkit.Material;
import studio.magemonkey.fabled.enchants.api.CustomEnchantment;

public class Fried extends CustomEnchantment {
    public static Fried instance;

    public Fried() {
        super("Fried", "Catches cooked fish instead of raw ones");

        setMaxLevel(1);
        setWeight(10);
        addNaturalItems(Material.FISHING_ROD);

        instance = this;
    }
}
