package com.github.koxsosen.lightmotd;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

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

    @Inject
    public LightMOTD(ProxyServer server, Logger logger) {
        this.logger = logger;
        this.server = server;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        logger.info("Loading LightMOTD");
        server.getEventManager().register(this, new ServerPinger());
        logger.info("Registered ServerPinger");
        logger.info("Loading config");
        server.getEventManager().register(this, new ConfigManager());
        logger.info("Config loaded");
    }

}
