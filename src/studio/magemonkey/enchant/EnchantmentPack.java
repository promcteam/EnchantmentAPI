package studio.magemonkey.enchant;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import studio.magemonkey.enchant.active.*;
import studio.magemonkey.enchant.passive.*;
import studio.magemonkey.enchant.potion.damaged.absorb.Adrenaline;
import studio.magemonkey.enchant.potion.damaged.absorb.Lively;
import studio.magemonkey.enchant.potion.damaged.absorb.Phantom;
import studio.magemonkey.enchant.potion.damaged.reflect.*;
import studio.magemonkey.enchant.potion.hit.inflict.*;
import studio.magemonkey.enchant.potion.hit.steal.Berserking;
import studio.magemonkey.enchant.potion.hit.steal.Distortion;
import studio.magemonkey.enchant.potion.hit.steal.Fervor;
import studio.magemonkey.enchant.potion.passive.Gears;
import studio.magemonkey.enchant.potion.passive.Jump;
import studio.magemonkey.enchant.potion.passive.NightVision;
import studio.magemonkey.enchant.trap.enchant.*;
import studio.magemonkey.fabled.enchant.active.Angler;
import studio.magemonkey.fabled.enchants.api.EnchantPlugin;
import studio.magemonkey.fabled.enchants.api.EnchantmentRegistry;
import studio.magemonkey.fabled.enchants.api.Tasks;

/**
 * EnchantmentPack © 2017
 * studio.magemonkey.enchant.EnchantmentPack
 */
public class EnchantmentPack extends JavaPlugin implements EnchantPlugin {
    private BukkitTask trapTask;

    @Override
    public void onEnable() {
        new EnchantsListener(this);
        trapTask = Tasks.schedule(Trap::tickTraps, 2, 2);
    }

    @Override
    public void onDisable() {
        trapTask.cancel();
        Trap.clearTraps();
        HandlerList.unregisterAll(this);
    }

    @Override
    public void registerEnchantments(final EnchantmentRegistry enchantmentRegistry) {
        enchantmentRegistry.register(
                // Active
                new Angler(),
                new Dash(),
                new Fireball(),
                new Fried(),
                new Grapple(),
                new Heal(),
                new Pull(),
                new Rejuvenating(),
                new Repulse(),
                new Toss(),
                new Vortex(),

                // Passive
                new Forceful(),
                new Gravity(),
                new Knockup(),
                new Life(),
                new Lifesteal(),
                new Lightning(),
                new Rapid(),
                new Regenerative(),
                new ShadowShift(),

                // Potion Absorb
                new Adrenaline(),
                new Lively(),
                new Phantom(),

                // Potion Reflect
                new Brilliance(),
                new Cursed(),
                new Demoralizing(),
                new Frost(),
                new Molten(),
                new Toxic(),

                // Potion Inflict
                new Blind(),
                new Poison(),
                new Slow(),
                new Weakness(),
                new Wither(),

                // Potion Steal
                new Berserking(),
                new Distortion(),
                new Fervor(),

                // Potion Passive
                new Gears(),
                new Jump(),
                new NightVision(),

                // Traps
                new BarrierTrap(),
                new BombTrap(),
                new FireTrap(),
                new IceTrap(),
                new LaunchTrap(),
                new LightningTrap(),
                new PoisonTrap(),
                new SlowTrap(),
                new WeaknessTrap(),
                new WebTrap()
        );
    }
}
