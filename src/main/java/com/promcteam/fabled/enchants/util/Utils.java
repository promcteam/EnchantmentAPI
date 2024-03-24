package com.promcteam.fabled.enchants.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * FabledEnchants © 2024 ProMCTeam
 * util.com.promcteam.fabled.enchants.Utils
 */
public class Utils {

    /**
     * @param item item to check
     * @return true if isPresent, false otherwise
     */
    public static boolean isPresent(final ItemStack item) {
        return item != null && item.getType() != Material.AIR;
    }
}
