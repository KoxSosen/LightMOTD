package com.github.koxsosen.lightmotd;

import com.github.koxsosen.lightmotd.config.NewConfigManager;
import com.github.koxsosen.lightmotd.serburping.ServerPinger;
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
        description = "Lightweight MOTD plugin for Velocity.",
        authors = {"KoxSosen"}
)
public class LightMOTD {

    private final ProxyServer server;
    private final Logger logger;
    private final Path configDirectory;

    @Inject
    public LightMOTD(ProxyServer server, Logger logger, @DataDirectory Path configDirectory) {
        this.logger = logger;
        this.server = server;
        this.configDirectory = configDirectory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getEventManager().register(this, new ServerPinger());
        NewConfigManager.loadconfig(configDirectory);
        logger.info("Successfully loaded LightMOTD.");
    }
}

