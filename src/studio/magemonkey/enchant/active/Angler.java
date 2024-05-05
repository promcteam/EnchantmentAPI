package studio.magemonkey.fabled.enchant.active;

import org.bukkit.Material;
import studio.magemonkey.fabled.enchants.api.CustomEnchantment;

public class Angler extends CustomEnchantment {
    public static Angler instance;

    public Angler() {
        super("Angler", "Yields extra fish while fishing");
        setMaxLevel(5);
        setWeight(4);
        addNaturalItems(Material.FISHING_ROD);

        instance = this;
    }
}
