package com.github.koxsosen.lightmotd.config;

import com.velocitypowered.api.proxy.ProxyServer;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigManager {

    private final Logger logger;

    Path defaultConfig;
    File defaultConf;
    CommentedConfigurationNode configNode;
    ConfigurationLoader <CommentedConfigurationNode> configManager;

    public ConfigManager(Path defaultConfig, String configName, Logger logger, ProxyServer proxyServer) {
        this.defaultConfig = defaultConfig;
        this.logger = logger;

        if (!defaultConfig.toFile().exists()) defaultConfig.toFile().mkdir();

        defaultConf = new File(defaultConfig.toFile(), configName);

        configManager = HoconConfigurationLoader.builder().setFile(defaultConf).build();

        try {
            configNode = configManager.load();
            pluginConf(configName);
            logger.info("Loading the config..");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            saveConfig();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        save(defaultConf);
    }

    public void saveConfig() {
        try {
            configManager.save(configNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(File input) {
        configManager = HoconConfigurationLoader.builder().setFile(input).build();
    }

    private void pluginConf(String fileName) {
        if (configNode.getNode("LightMOTD", "playercount").isVirtual()) {

            configNode.getNode("LightMOTD", "playercount", "max-players")
                    .setValue(0)
                    .setComment("The max amount of players which will be shown. Set it to 0 to disable");

            configNode.getNode("LightMOTD", "playercount", "current-players")
                    .setValue(0)
                    .setComment("The current player count which will be shown. Set it to 0 to disable.");
        }

        if (configNode.getNode("LightMOTD", "text").isVirtual()) {

            configNode.getNode("LightMOTD", "text")
                    .setValue("<gray>This is the default MOTD of LightMOTD</gray>" +
                            "\n<reset><white>This is a new line :P</white>")
                    .setComment("This is where you can set the MOTD. Set it to empty to disable it." +
                            "\nIt uses the MiniMessage format. You can do <green>, or <#00ff00>R G B!." +
                            "\n It also parses the MARKDOWN syntax.");
        }

    }

    public int maxplayers() {

        int maxpl = 0;
        try {
            maxpl = configNode.getNode("LightMOTD", "playercount", "max-players").getInt();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return maxpl;

    }

    public int currentplayers() {

        int currpl = 0;
        try {
            currpl = configNode.getNode("LightMOTD", "playercount", "current-players").getInt();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return currpl;

    }

    public String textmotd() {

        String txtmotd = "";
        try {
            txtmotd = configNode.getNode("LightMOTD", "text").getString();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return txtmotd;

    }

    public Logger getLogger() {
        return logger;
    }

}
