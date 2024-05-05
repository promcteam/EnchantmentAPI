package studio.magemonkey.enchant.trap.enchant;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import studio.magemonkey.fabled.api.CombatProtection;
import studio.magemonkey.fabled.enchants.api.Tasks;

import java.util.ArrayList;

public class WebTrap extends RedstoneTrap {
    private static final String DURATION = "duration";
    private static final String RADIUS   = "radius";

    public WebTrap() {
        super("Web Trap", "Places a trap that spawns temporary webs");

        settings.set(LIFESPAN, -1, 0);
        settings.set(DURATION, 7, 0);
        settings.set(RADIUS, 3, 0);

        radius = 3;
        layout = new boolean[][]{
                {false, false, true, true},
                {false, false, true, false, true},
                {true, true, false, true, true},
                {true, false, true},
                {false, true, true}};
    }

    /**
     * Creates webs around an entity entering the trap
     *
     * @param trap   trap walked into
     * @param entity enemy that walked into the trap
     * @param level  enchantment level used for the trap
     */
    @Override
    public boolean onEnter(final Trap trap, final LivingEntity entity, final int level) {
        if (CombatProtection.canAttack(trap.getOwner(), entity)) {
            final Location         loc     = entity.getLocation();
            final ArrayList<Block> webs    = new ArrayList<Block>();
            final int              max     = (int) settings.get(RADIUS, level);
            final double           halfMax = max / 2.0;
            loc.setX(loc.getX() - halfMax);
            loc.setZ(loc.getZ() - halfMax);
            loc.setY(loc.getY() - halfMax + 1);

            final int count = max * max * max;
            for (int i = 0; i < count; i++) {
                final Location temp = new Location(loc.getWorld(),
                        loc.getX() + i % max,
                        loc.getY() + ((double) i / max) / max,
                        loc.getZ() + ((double) i / max) % max);
                final Block block = loc.getWorld().getBlockAt(temp);
                if (block.getType() == Material.AIR || !block.getType().isSolid()) {
                    webs.add(block);
                    block.setType(Material.COBWEB);
                }
            }

            final int duration = (int) (settings.get(DURATION, level) * 20);
            Tasks.schedule(() -> webs.forEach(web -> {
                if (web.getType() == Material.COBWEB) web.setType(Material.AIR);
            }), duration);
            return true;
        }
        return false;
    }
}
