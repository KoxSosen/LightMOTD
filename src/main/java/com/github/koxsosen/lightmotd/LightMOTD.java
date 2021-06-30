package com.github.koxsosen.lightmotd;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "lightmotd",
        name = "LightMOTD",
        version = "@version@",
        description = "Lightweight motd plugin for Velocity.",
        authors = {"KoxSosen"}
)
public class LightMOTD {

    private final ProxyServer server;
    private final Logger logger;
    private final Path configDirectory;
    private ConfigManager config;

    @Inject
    public LightMOTD(ProxyServer server, Logger logger, @DataDirectory Path configDirectory) {
        this.logger = logger;
        this.server = server;
        this.configDirectory = configDirectory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        logger.info("Loading LightMOTD");
        server.getEventManager().register(this, new ServerPinger(LightMOTD.this));
        logger.info("Registered ServerPinger");
        logger.info("Loading config");
        this.config = new ConfigManager(configDirectory, "LightMOTD.conf", logger, server);
        logger.info("Config loaded");
    }

    public ConfigManager getConfig() {
        return config;
    }
    public void doReload() {
        try {
            getConfig().saveConfig();
            this.config = new ConfigManager(configDirectory, "LightMOTD.conf", logger, server);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
