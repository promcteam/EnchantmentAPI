package studio.magemonkey.enchant.potion.passive;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;
import studio.magemonkey.fabled.enchants.api.ItemSet;
import studio.magemonkey.fabled.enchants.api.Tasks;

/**
 * Passively grants bonus jump height
 */
public class Gears extends PotionPassive {

    public Gears() {
        super("Gears", "Passively grants movement speed bonus");

        addNaturalItems(ItemSet.BOOTS.getItems());
    }

    public PotionEffectType type() {
        return PotionEffectType.SPEED;
    }

    @Override
    public void applyDefense(
            final LivingEntity user, final LivingEntity target, final int level, final EntityDamageEvent event) {
        Tasks.schedule(() -> applyEquip(user, level));
    }
}
