package com.promcteam.fabled.enchants.data;

import com.promcteam.codex.mccore.config.CommentedConfig;
import com.promcteam.codex.mccore.config.parse.DataSection;
import com.promcteam.fabled.enchants.FabledEnchants;

/**
 * FabledEnchants © 2024 ProMCTeam
 * data.com.promcteam.fabled.enchants.Configuration
 */
public class Configuration {

    private static DataSection data;

    public static void reload(final FabledEnchants plugin) {
        final CommentedConfig config = new CommentedConfig(plugin, "config");
        config.saveDefaultConfig();
        config.checkDefaults();
        config.trim();
        config.save();
        data = config.getConfig();
    }

    public static boolean using(final ConfigKey configKey) {
        return data.getBoolean(configKey.getKey());
    }

    public static int amount(final ConfigKey configKey) {
        return data.getInt(configKey.getKey());
    }
}
