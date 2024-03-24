package com.promcteam.fabled.enchants.api;

/**
 * FabledEnchants © 2024 ProMCTeam
 * api.com.promcteam.fabled.enchants.EnchantmentRegistry
 */
public interface EnchantmentRegistry {

    /**
     * Registers enchantments with the API, allowing them to be available
     * in the enchanting tables, commands, and anvils.
     *
     * @param enchantments enchantments to register
     */
    void register(CustomEnchantment... enchantments);
}
