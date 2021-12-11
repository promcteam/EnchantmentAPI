package com.sucy.enchant.passive;

import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;
import com.sucy.skill.SkillAPI;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Life extends CustomEnchantment {

    private static final String HEALTH = "health";
    private static final String ATTRIBUTE_NAME = "enchantmentpack:life";

    public Life() {
        super("Life", "Grants bonus health when equipped");

        setMaxLevel(6);
        setWeight(2);
        addNaturalItems(ItemSet.CHESTPLATES.getItems());

        settings.set(HEALTH, 5, 5);
    }

    @Override
    public void applyEquip(final LivingEntity user, final int level) {
        final double health = settings.get(HEALTH, level);
        user.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(new AttributeModifier("enchantmentpack:life", health, AttributeModifier.Operation.ADD_NUMBER));
        if (Bukkit.getPluginManager().isPluginEnabled("SkillAPI") && user instanceof Player) {
            SkillAPI.getPlayerData((Player) user).updateHealth((Player) user);
        }
    }

    @Override
    public void applyUnequip(LivingEntity user, int level) {
        AttributeInstance attribute = user.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        for (AttributeModifier modifier : attribute.getModifiers()) {
            if (modifier.getName().equals(ATTRIBUTE_NAME)) {
                attribute.removeModifier(modifier);
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("SkillAPI") && user instanceof Player) {
            SkillAPI.getPlayerData((Player) user).updateHealth((Player) user);
        }
    }
}
