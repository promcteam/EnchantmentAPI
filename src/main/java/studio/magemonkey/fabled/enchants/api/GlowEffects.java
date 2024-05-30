package studio.magemonkey.fabled.enchants.api;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * FabledEnchants © 2024 MageMonkeyStudio
 * api.studio.magemonkey.fabled.enchants.GlowEffects
 */
public class GlowEffects {

    /**
     * Ensures an item has a glowing effect if it has any enchantments, custom or not.
     *
     * @param item item to give glowing effects to
     * @return item with the glowing effect
     */
    public static ItemStack finalize(final ItemStack item) {
        // Enchanted books always glow
        if (item.getType() == Material.ENCHANTED_BOOK) return item;

        final ItemMeta meta = item.getItemMeta();

        // Hide enchantments and add a non-impactful enchantment to get the glowing effect
        if (item.getEnchantments().isEmpty() && !Enchantments.getCustomEnchantments(item).isEmpty()) {
            meta.addEnchant(chooseHidden(item), 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        // Stop hiding enchantments if a normal vanilla enchantment was added
        else if (meta.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
            final Enchantment hidden = chooseHidden(item);
            if (meta.getEnchants().getOrDefault(hidden, 0) == 1 && meta.getEnchants().size() > 1) {
                meta.removeEnchant(hidden);
            }
            if (!meta.getEnchants().containsKey(hidden)) {
                meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
        }

        item.setItemMeta(meta);
        return item;
    }

    private static Enchantment chooseHidden(final ItemStack item) {
        if (item.getType() == Material.ENCHANTED_BOOK) {
            return Enchantment.BINDING_CURSE;
        } else if (item.getType() == Material.BOW) {
            Enchantment ench = Enchantment.getByKey(NamespacedKey.minecraft("fortune")); // LUCK/FORTUNE
            if (ench == null) ench = Enchantment.getByKey(NamespacedKey.minecraft("luck"));
            return ench;
        } else {
            return Enchantment.getByKey(NamespacedKey.minecraft("infinity")); // ARROW_INFINITE/INFINITY
        }
    }
}
