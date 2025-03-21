package studio.magemonkey.fabled.enchants.data;

import com.google.common.collect.ImmutableList;
import org.bukkit.Material;
import studio.magemonkey.codex.mccore.config.CommentedConfig;
import studio.magemonkey.codex.mccore.config.parse.DataSection;
import studio.magemonkey.fabled.enchants.FabledEnchants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * FabledEnchants © 2024 MageMonkeyStudio
 * data.studio.magemonkey.fabled.enchants.Enchantability
 */
public class Enchantability {

    private static final String TYPES          = "types";
    private static final String ENCHANTABILITY = "enchantability";
    private static final String DEFAULT        = "default";

    private static final Map<Material, Integer> VALUES = new HashMap<>();

    private static int defaultValue = 10;

    public static int determine(final Material material) {
        return VALUES.getOrDefault(material, defaultValue);
    }

    public static void init(final FabledEnchants fabledEnchants) {
        final CommentedConfig config = new CommentedConfig(fabledEnchants, "enchantability");
        checkDefaults(config);

        final DataSection data = config.getConfig();
        defaultValue = data.getInt(DEFAULT, 10);
        for (final String key : data.keys()) {
            if (data.isSection(key)) {
                final DataSection section = data.getSection(key);
                if (!section.has(TYPES) || !section.has(ENCHANTABILITY)) {
                    fabledEnchants.getLogger().warning(key + " in enchantability.yml is missing required values");
                } else {
                    final int value = section.getInt(ENCHANTABILITY, 0);
                    section.getList(TYPES).forEach(type -> {
                        final Material mat = Material.matchMaterial(type);
                        if (mat == null) {
                            fabledEnchants.getLogger()
                                    .warning(type + " is not a valid material (under " + key
                                            + " in enchantability.yml)");
                        } else {
                            VALUES.put(mat, value);
                        }
                    });
                }
            } else if (!key.equals(DEFAULT)) {
                fabledEnchants.getLogger().warning(key + " in enchantability.yml is not formatted properly");
            }
        }
    }

    private static void checkDefaults(final CommentedConfig config) {
        final DataSection data = config.getConfig();
        if (!config.getConfigFile().exists()) {
            for (final MaterialClass materialClass : MaterialClass.values()) {
                populate(data, ARMOR, materialClass.name, "armor", materialClass.armor);
                populate(data, WEAPON, materialClass.name, "tool", materialClass.weapon);
            }
        }
        if (!data.has(DEFAULT)) {
            data.set(DEFAULT, 1);
            config.save();
        }
    }

    private static void populate(
            final DataSection data,
            final List<String> names,
            final String material,
            final String category,
            final int value) {

        if (value == 0) {
            return;
        }

        final List<String> types   = names.stream().map(type -> material + "_" + type).collect(Collectors.toList());
        final DataSection  section = data.createSection(material.toLowerCase() + "-" + category);
        section.set(TYPES, types);
        section.set(ENCHANTABILITY, value);
    }

    private static final List<String> ARMOR = ImmutableList.<String>builder()
            .add("BOOTS")
            .add("CHESTPLATE")
            .add("HELMET")
            .add("LEGGINGS")
            .build();

    private static final List<String> WEAPON = ImmutableList.<String>builder()
            .add("AXE")
            .add("HOE")
            .add("PICKAXE")
            .add("SHOVEL")
            .add("SWORD")
            .build();

    private enum MaterialClass {
        WOOD(0, 15, "WOODEN"),
        STONE(0, 5),
        IRON(9, 14),
        GOLD(25, 22, "GOLDEN"),
        DIAMOND(10, 10),
        NETHERITE(15, 15),
        LEATHER(15, 0),
        CHAINMAIL(12, 0);

        private final int    armor;
        private final int    weapon;
        private final String name;

        MaterialClass(final int armor, final int weapon) {
            this.armor = armor;
            this.weapon = weapon;
            this.name = this.name();
        }

        MaterialClass(final int armor, final int weapon, final String name) {
            this.armor = armor;
            this.weapon = weapon;
            this.name = name;
        }
    }
}
