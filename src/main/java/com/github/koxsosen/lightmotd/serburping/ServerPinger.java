package com.github.koxsosen.lightmotd.serburping;

import com.github.koxsosen.lightmotd.config.NewConfigManager;
import com.github.koxsosen.lightmotd.parse.AdventureParse;
import com.velocitypowered.api.event.EventTask;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;

public class ServerPinger {

    @Subscribe
    public EventTask onProxyPingEvent(ProxyPingEvent event) {
        return EventTask.async(() -> this.format(event));
    }

    private void format(ProxyPingEvent event) {

        final ServerPing.Builder ping = event.getPing().asBuilder();
        final int playercount = NewConfigManager.getPlayercount();
        final int maxplayers = NewConfigManager.getCurrentplayers();
        final Boolean onemorejust = NewConfigManager.getJustOneMore();
        final Boolean hiddenplayers = NewConfigManager.getHiddenplayers();
        final String motd = NewConfigManager.getMOTD();

        try {
            if (playercount > 0) {
                ping.onlinePlayers(playercount);
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
                if (hiddenplayers) {
                    ping.nullPlayers();
                }
            } finally {
            event.setPing(ping.build());
        }
    }
}


