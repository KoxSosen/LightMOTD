package com.github.koxsosen.lightmotd;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;

public class ServerPinger {

    @Subscribe
    public void onServerPing(ProxyPingEvent event) {

        final ServerPing.Builder ping = event.getPing().asBuilder();

        ping.onlinePlayers(new LightMOTD().getConfig().maxplayers()); // set from config
        ping.maximumPlayers(10); // set from config

        event.setPing(ping.build());

    }

}
