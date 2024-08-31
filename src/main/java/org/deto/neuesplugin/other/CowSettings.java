package org.deto.neuesplugin.other;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.deto.neuesplugin.NeuesPlugin;

import java.io.File;

public class CowSettings {

    private final static CowSettings instance = new CowSettings();

    private File file;
    private YamlConfiguration config;

    private EntityType explodingType;

    private CowSettings() {

    }

    public void load() {
        file = new File(NeuesPlugin.getInstance().getDataFolder(), "settings.yml");

        if (!file.exists()) {
            NeuesPlugin.getInstance().saveResource("settings.yml", false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        explodingType = EntityType.valueOf(config.getString("Explosion.Entity_Type"));
    }


    public void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void set(String path, Object value) {
        config.set(path, value);
        save();
    }


    public EntityType getExplodingType() {
        return explodingType;
    }

    public void setExplodingType(EntityType explodingType) {
        this.explodingType = explodingType;

        set("Explosion.Entity_Type", explodingType.name());
    }

    public static CowSettings getInstance() {
        return instance;
    }

}