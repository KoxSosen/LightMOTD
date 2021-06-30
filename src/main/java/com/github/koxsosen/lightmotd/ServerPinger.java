package com.github.koxsosen.lightmotd;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;

public class ServerPinger {

    private final LightMOTD lightMOTD;

    public ServerPinger(LightMOTD lightMOTD) {
        this.lightMOTD = lightMOTD;
    }

    @Subscribe
    public void onServerPing(ProxyPingEvent event) {

        final ServerPing.Builder ping = event.getPing().asBuilder();

        ping.onlinePlayers(lightMOTD.getConfig().currentplayers()); // set from config
        ping.maximumPlayers(lightMOTD.getConfig().maxplayers()); // set from config

        event.setPing(ping.build());

    }

}
