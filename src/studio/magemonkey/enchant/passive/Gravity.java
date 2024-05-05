package studio.magemonkey.enchant.passive;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import studio.magemonkey.enchant.ConflictGroup;
import studio.magemonkey.fabled.Fabled;
import studio.magemonkey.fabled.enchants.api.CustomEnchantment;
import studio.magemonkey.fabled.enchants.api.ItemSet;

/**
 * Pulls in all nearby enemies on hit
 */
public class Gravity extends CustomEnchantment {
    private static final String CHANCE = "chance";
    private static final String RANGE  = "range";
    private static final String DAMAGE = "damage";
    private static final String SPEED  = "speed";

    public Gravity() {
        super("Gravity", "Damages and pulls in enemies on attack");

        setGroup(ConflictGroup.FORCE);
        setMaxLevel(6);
        setWeight(1);
        addNaturalItems(ItemSet.HOES.getItems());

        settings.set(CHANCE, 100, 0);
        settings.set(RANGE, 5, 1);
        settings.set(DAMAGE, 1, 0);
        settings.set(SPEED, 0.1, 0);
    }

    /**
     * Pulls in nearby enemies on hit
     *
     * @param user   player with the enchantment
     * @param target enemy that was hit
     * @param level  enchantment level
     * @param event  event details
     */
    @Override
    public void applyOnHit(LivingEntity user, LivingEntity target, int level, EntityDamageByEntityEvent event) {
        if (Math.random() * 100 > settings.get(CHANCE, level)) return;

        final double range  = settings.get(RANGE, level);
        final double damage = settings.get(DAMAGE, level);
        final double speed  = settings.get(SPEED, level);
        for (Entity entity : user.getNearbyEntities(range, range, range)) {
            if (!(entity instanceof LivingEntity) || Fabled.getSettings().isAlly(user, (LivingEntity) entity)) continue;

            ((LivingEntity) entity).damage(damage, user);
            entity.setVelocity(user.getLocation().subtract(entity.getLocation()).toVector().multiply(speed));
        }
    }
}
