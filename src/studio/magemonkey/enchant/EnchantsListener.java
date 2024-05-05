package studio.magemonkey.enchant;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import studio.magemonkey.enchant.active.Fried;
import studio.magemonkey.enchant.active.Grapple;
import studio.magemonkey.enchant.active.Rejuvenating;
import studio.magemonkey.enchant.trap.enchant.Trap;
import studio.magemonkey.fabled.enchants.api.CustomEnchantment;
import studio.magemonkey.fabled.enchants.api.Enchantments;

import java.util.Map;

/**
 * Listener for the EnchantmentPack
 */
public class EnchantsListener implements Listener {

    EnchantsListener(final Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Remove passive abilities when players leave
     *
     * @param event event details
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDisconnect(final PlayerQuitEvent event) {
        if (event.getPlayer().isInsideVehicle()) {
            event.getPlayer().getVehicle().eject();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFish(final PlayerFishEvent event) {
        final Map<CustomEnchantment, Integer> enchants = Enchantments.getEnchantments(event.getPlayer());
        if (event.getState() == PlayerFishEvent.State.IN_GROUND && enchants.containsKey(Grapple.instance)) {
            Grapple.instance.apply(event.getPlayer(), event.getHook(), enchants.get(Grapple.instance));
        } else if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            final ItemStack caught = ((Item) event.getCaught()).getItemStack();
            if (caught.getType() == Material.COD) {
                caught.setAmount(enchants.getOrDefault(studio.magemonkey.fabled.enchant.active.Angler.instance, 0) + 1);
                if (enchants.containsKey(Fried.instance)) {
                    caught.setType(Material.COOKED_COD);
                }
            } else if (caught.getType() == Material.SALMON) {
                caught.setAmount(enchants.getOrDefault(studio.magemonkey.fabled.enchant.active.Angler.instance, 0) + 1);
                if (enchants.containsKey(Fried.instance)) {
                    caught.setType(Material.COOKED_SALMON);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onConsume(final PlayerItemConsumeEvent event) {
        final Map<CustomEnchantment, Integer> enchants = Enchantments.getEnchantments(event.getPlayer());
        if (enchants.containsKey(Rejuvenating.instance)) {
            Rejuvenating.instance.apply(event.getPlayer(), enchants.get(Rejuvenating.instance));
        }
    }

    /**
     * Prevent traps from being broken
     *
     * @param event event details
     */
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreak(final BlockBreakEvent event) {
        for (final Trap trap : Trap.getTraps()) {
            if (trap.contains(event.getBlock().getLocation())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("This block is protected by a magical spell...");
            }
        }
    }
}