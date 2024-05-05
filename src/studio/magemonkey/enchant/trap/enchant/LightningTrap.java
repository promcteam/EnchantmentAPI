package studio.magemonkey.enchant.trap.enchant;

import org.bukkit.entity.LivingEntity;
import studio.magemonkey.fabled.api.CombatProtection;
import studio.magemonkey.fabled.enchants.api.Cooldowns;

public class LightningTrap extends RedstoneTrap {
    public LightningTrap() {
        super("Lightning Trap", "Places a trap that strikes lightning");

        Cooldowns.configure(settings, 15, -2);
        settings.set(LIFESPAN, -1, 0);

        radius = 3;
        layout = new boolean[][]{
                {false, true, false, true},
                {true, true, false, true, true},
                {false, false, true, false, false},
                {true, true, false, true, true},
                {false, true, false, true}};
    }

    /**
     * Blows up the trap when an enemy enters the trap
     *
     * @param trap   trap walked into
     * @param entity enemy that walked into the trap
     * @param level  enchantment level used for the trap
     */
    @Override
    public boolean onEnter(Trap trap, LivingEntity entity, int level) {
        if (CombatProtection.canAttack(trap.getOwner(), entity)) {
            entity.getWorld().strikeLightning(entity.getLocation());
            return true;
        }
        return false;
    }
}
