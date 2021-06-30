package com.github.koxsosen.lightmotd;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.File;
import java.nio.file.Paths;

public class ConfigManager {

    public ConfigurationLoader <CommentedConfigurationNode> initializeConfigurationLoader() {
        String pluginId = "lightmotd";
        File configFilesLocation = Paths.get("plugins/" + pluginId).toFile();
        if (!configFilesLocation.exists()) {
            if (!configFilesLocation.mkdirs()) {
                throw new IllegalStateException("Unable to create config directory");
            }
        }
        return HoconConfigurationLoader.builder().setPath(Paths.get(configFilesLocation + "/" + pluginId + ".conf")).build();
    }

}
