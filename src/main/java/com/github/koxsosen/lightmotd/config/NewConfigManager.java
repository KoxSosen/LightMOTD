package com.github.koxsosen.lightmotd.config;


import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import org.slf4j.Logger;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class NewConfigManager {

    // Root configuration node used to access and modify the config.
    static CommentedConfigurationNode root;
    static File conf;


    public static void loadconfig(Path configdirectory) {

        if (!configdirectory.toFile().exists()) {
            configdirectory.toFile().mkdir();
        }

        conf = new File(configdirectory.toFile(), "LightMOTD.conf");
        final HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
                .setFile(conf)
                .build();
        try {
            root = loader.load();
            values();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        try {
            loader.save(root);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void values() {
        final ConfigurationNode playercount = root.getNode("LightMOTD", "playercount", "max-players")
                .setComment("The max amount of players which will be shown.")
                .setValue(0);

        final ConfigurationNode currentplayers = root.getNode("LightMOTD", "playercount", "current-players")
                .setComment("The amount of players to add to your current players.")
                .setValue(0);

        final ConfigurationNode justonemore = root.getNode("LightMOTD", "playercount", "justonemore")
                .setComment("If this is set to true, the max playercount will always be as many players as you have + 1.")
                .setValue(false);

        final ConfigurationNode hiddenplayers = root.getNode("LightMOTD", "playercount", "hiddenplayers")
                .setComment("If this is set to true, the player count will show up as question marks.")
                .setValue(false);

        final ConfigurationNode motdtext = root.getNode("LightMOTD", "text")
                .setComment("This is where you can set the MOTD. Set it to empty to disable it." +
                        "\n It uses the MiniMessage format. You can do <green>, or <#00ff00>R G B!." +
                        "\n It also parses the MARKDOWN syntax.")
                .setValue("<white>This is the default MOTD of </white><#b02e26>Light<#825432>M<#80c71f>O<#b02e26>T<#825432>D<white>." +
                        "\n<reset><gray>This is a new line :P </gray><#b02e26>R <#825432>G <#80c71f>B<reset>!.");

    }

    public static int getPlayercount() {
        return root.getNode("LightMOTD", "playercount", "max-players").getInt();
    }

    public static int getCurrentplayers() {
        return root.getNode("LightMOTD", "playercount", "current-players").getInt();
    }

    public static boolean getJustOneMore() {
        return root.getNode("LightMOTD", "playercount", "justonemore").getBoolean();
    }

    public static boolean getHiddenplayers() {
        return root.getNode("LightMOTD", "playercount", "hiddenplayers").getBoolean();
    }

    public static String getMOTD() {
        return root.getNode("LightMOTD", "text").getString();
    }

}
