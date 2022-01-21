package com.github.koxsosen.lightmotd.config;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigManager {

    Path defaultConfig;
    File defaultConf;
    CommentedConfigurationNode configNode;
    ConfigurationLoader <CommentedConfigurationNode> configManager;

    public ConfigManager(Path defaultConfig, String configName, Logger logger) {
        this.defaultConfig = defaultConfig;

        if (!defaultConfig.toFile().exists()) defaultConfig.toFile().mkdir();

        defaultConf = new File(defaultConfig.toFile(), configName);

        configManager = HoconConfigurationLoader.builder().setFile(defaultConf).build();

        try {
            configNode = configManager.load();
            pluginConf();
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

    private void pluginConf() {
        if (configNode.getNode("LightMOTD", "playercount").isVirtual()) {

            configNode.getNode("LightMOTD", "playercount", "max-players")
                    .setComment("The max amount of players which will be shown. " +
                            "Set it to 0 to disable.")
                    .setValue(0);

            configNode.getNode("LightMOTD", "playercount", "current-players")
                    .setComment("The amount of players to add to your current players. " +
                            "\nSet it to 0 to disable.")
                    .setValue(0);

            configNode.getNode("LightMOTD", "playercount", "justonemore")
                    .setComment("If this is set to true, the max playercount will always be as many players as you have + 1." +
                            "\nSet it to false to disable.")
                    .setValue(false);

            configNode.getNode("LightMOTD", "playercount", "hiddenplayers")
                    .setComment("If this is set to true, the player count will show up as question marks.")
                    .setValue(false);
        }

        if (configNode.getNode("LightMOTD", "text").isVirtual()) {

            configNode.getNode("LightMOTD", "text")
                    .setValue("<white>This is the default MOTD of </white><#b02e26>Light<#825432>M<#80c71f>O<#b02e26>T<#825432>D<white>." +
                            "\n<reset><gray>This is a new line :P </gray><#b02e26>R <#825432>G <#80c71f>B<reset>!.")
                    .setComment("""
                            This is where you can set the MOTD. Set it to empty to disable it.
                            It uses the MiniMessage format. You can do <green>, or <#00ff00>R G B!.
                             It also parses the MARKDOWN syntax.""");
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


    public Boolean onejustmore() {

        boolean onemore = false;
        try {
            onemore = configNode.getNode("LightMOTD", "playercount", "justonemore").getBoolean();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return onemore;

    }

    public Boolean hiddentheplayers() {

        boolean areplayersnull = false;
        try {
            areplayersnull = configNode.getNode("LightMOTD", "playercount", "hiddenplayers").getBoolean();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return areplayersnull;

    }


}
