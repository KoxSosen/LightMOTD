package com.github.koxsosen.lightmotd.serburping;

import com.github.koxsosen.lightmotd.LightMOTD;
import com.github.koxsosen.lightmotd.parse.AdventureParse;
import com.velocitypowered.api.event.EventTask;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;


public class ServerPinger {

    @Subscribe
    public EventTask onProxyPingEvent(ProxyPingEvent e) {
        return EventTask.async(() -> this.format(e));
    }

    private void format(ProxyPingEvent e) {

        final ServerPing.Builder ping = e.getPing().asBuilder();

        final int currentplayers = LightMOTD.getConfig().currentplayers();

        final int maxplayers = LightMOTD.getConfig().maxplayers();

        Boolean onemorejust = LightMOTD.getConfig().onejustmore();

        Boolean nulltheplayers = LightMOTD.getConfig().hiddentheplayers();

        final String motd = LightMOTD.getConfig().textmotd();

        try {

            if (currentplayers > 0) {
            ping.onlinePlayers(currentplayers);
            }

            if (maxplayers > 0) {
            ping.maximumPlayers(maxplayers);
            }

            if (!motd.isEmpty()) {
            ping.description(AdventureParse.formatted(motd));
            }

            if (onemorejust) {
            ping.maximumPlayers(1 + ping.getOnlinePlayers());
            }

            if (nulltheplayers) {
            ping.nullPlayers();
            }

            } finally {
            e.setPing(ping.build());
        }
    }
}


