package com.github.koxsosen.lightmotd;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.slf4j.Logger;

public class ServerPinger {

    private final LightMOTD lightMOTD;

    private Logger logger;

    public ServerPinger(LightMOTD lightMOTD) {
        this.lightMOTD = lightMOTD;
    }

    @Subscribe
    public void onServerPing(ProxyPingEvent event) { // TODO: Run the code after the first return.

        final ServerPing.Builder ping = event.getPing().asBuilder();
        try {

            ping.onlinePlayers(lightMOTD.getConfig().currentplayers());
            if (lightMOTD.getConfig().currentplayers() == 0) {
                logger.info("Thee value of online players is 0, not changing the default settings.");
                return;
            }
            ping.maximumPlayers(lightMOTD.getConfig().maxplayers());
            if (lightMOTD.getConfig().maxplayers() == 0) {
                logger.info("The max players value is zero, not changing it's value.");
                return;
            }
            ping.description(MiniMessage.markdown().parse(lightMOTD.getConfig().textmotd()));
            if (lightMOTD.getConfig().textmotd() == null) {
                logger.info("The config is empty not setting a MOTD.");
                return;
            }

            // TODO: REWORK THIS PART OF THE CODE!!!!!!!
            if (lightMOTD.getConfig().ishidden() == true) {
                ping.nullPlayers();
            }

        } finally {
            event.setPing(ping.build());
        }
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}