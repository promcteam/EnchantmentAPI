package studio.magemonkey.enchant.passive;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import studio.magemonkey.enchant.ConflictGroup;
import studio.magemonkey.fabled.api.CombatProtection;
import studio.magemonkey.fabled.enchants.api.CustomEnchantment;
import studio.magemonkey.fabled.enchants.api.ItemSet;
import studio.magemonkey.fabled.enchants.api.Tasks;

/**
 * Has a chance to knock enemies up into the air on hit
 */
public class Knockup extends CustomEnchantment {
    private static final String CHANCE = "chance";
    private static final String SPEED  = "speed";

    public Knockup() {
        super("Knockup", "Has a chance to knock the target into the air");

        setGroup(ConflictGroup.FORCE);
        setMaxLevel(4);
        setWeight(10);
        addNaturalItems(ItemSet.SWORDS.getItems());

        settings.set(CHANCE, 5, 2.5);
        settings.set(SPEED, 0.8, 0);
    }

    /**
     * Knocks enemies up on hit
     *
     * @param user   player with the enchantment
     * @param target enemy that was hit
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyOnHit(final LivingEntity user,
                           final LivingEntity target,
                           final int level,
                           final EntityDamageByEntityEvent event) {
        if (Math.random() * 100 > settings.get(CHANCE, level)) return;

        if (CombatProtection.canAttack(user, target)) {
            final double speed = settings.get(SPEED, level);
            Tasks.schedule(() -> target.setVelocity(new Vector(0, speed, 0)));
        }
    }
}