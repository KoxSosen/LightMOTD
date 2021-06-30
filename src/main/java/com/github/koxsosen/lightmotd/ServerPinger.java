package com.github.koxsosen.lightmotd;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ServerPinger {

    private final LightMOTD lightMOTD;

    public ServerPinger(LightMOTD lightMOTD) {
        this.lightMOTD = lightMOTD;
    }

    @Subscribe
    public void onServerPing(ProxyPingEvent event) { // TODO: Run the code after the first return.

        final ServerPing.Builder ping = event.getPing().asBuilder();
        try {
            ping.onlinePlayers(lightMOTD.getConfig().currentplayers());
            if (lightMOTD.getConfig().currentplayers() == 0) {
                return;
            }
            ping.maximumPlayers(lightMOTD.getConfig().maxplayers());
            if (lightMOTD.getConfig().maxplayers() == 0) {
                return;
            }
            ping.description(MiniMessage.markdown().parse(lightMOTD.getConfig().textmotd()));
            if (lightMOTD.getConfig().textmotd() == null) {
                return;
            }
        } finally {
            event.setPing(ping.build());
        }
    }
}