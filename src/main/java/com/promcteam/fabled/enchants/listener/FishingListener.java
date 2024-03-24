package com.promcteam.fabled.enchants.listener;

import com.promcteam.fabled.enchants.api.Enchantments;
import com.promcteam.fabled.enchants.data.ConfigKey;
import com.promcteam.fabled.enchants.data.Configuration;
import com.promcteam.fabled.enchants.mechanics.EnchantingMechanics;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

/**
 * FabledEnchants © 2024 ProMCTeam
 * listener.com.promcteam.fabled.enchants.FishingListener
 */
public class FishingListener extends BaseListener {

    private final Random random = new Random();

    private final EnchantingMechanics mechanics = new EnchantingMechanics();

    private final int bookLevel = Configuration.amount(ConfigKey.FISHING_ENCHANTING_LEVEL);

    @EventHandler
    public void onCatch(final PlayerFishEvent event) {
        if (!(event.getCaught() instanceof Item))
            return;

        final Item      caught = (Item) event.getCaught();
        final ItemStack item   = caught.getItemStack();
        if (!Enchantments.getAllEnchantments(item).isEmpty()) {
            Enchantments.removeAllEnchantments(item);
            mechanics.generateEnchantments(event.getPlayer(), item, bookLevel, false, random.nextLong())
                    .getEnchantments().forEach((enchant, level) -> enchant.addToItem(item, level));
            caught.setItemStack(item);
        }
    }
}
