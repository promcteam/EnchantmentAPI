package studio.magemonkey.enchant.trap.enchant;

import org.bukkit.potion.PotionEffectType;

public class SlowTrap extends PotionTrap {
    public SlowTrap() {
        super("Slow Trap", "Places a trap that slows enemies",
                PotionEffectType.getByName("SLOW") != null
                        ? PotionEffectType.getByName("SLOW")
                        : PotionEffectType.getByName("SLOWNESS"));

        radius = 3;
        layout = new boolean[][]{
                {false, true, false, true},
                {true, false, true, false, true},
                {false, true, true, true, false},
                {true, false, true, false, true},
                {false, true, false, true}};
    }
}
