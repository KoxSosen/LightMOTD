package com.github.koxsosen.lightmotd;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ServerPinger {

    private final LightMOTD lightMOTD;

    public ServerPinger(LightMOTD lightMOTD) {
        this.lightMOTD = lightMOTD;
    }

    @Subscribe
    public void onServerPing(ProxyPingEvent event) {

       final int currentplayers = lightMOTD.getConfig().currentplayers();

       final int maxplayers = lightMOTD.getConfig().maxplayers();

       final Component motd = MiniMessage.markdown().parse(lightMOTD.getConfig().textmotd());

       final ServerPing.Builder ping = event.getPing().asBuilder();

        try {

           if (currentplayers > 0) {
               ping.onlinePlayers(currentplayers);
            }

           if (maxplayers > 0) {
               ping.maximumPlayers(currentplayers);
           }

           if (motd.contains(motd)) {
                ping.description(motd);
           }

            if (lightMOTD.getConfig().ishidden()) {
                ping.nullPlayers();
            }

        } finally {
            event.setPing(ping.build());
        }
    }

}