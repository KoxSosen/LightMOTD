package com.github.koxsosen.lightmotd.serburping;

import com.github.koxsosen.lightmotd.LightMOTD;
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
    public void onServerPing(ProxyPingEvent event) {

       final int currentplayers = lightMOTD.getConfig().currentplayers();

       final int maxplayers = lightMOTD.getConfig().maxplayers();

       final String motd = lightMOTD.getConfig().textmotd();

       final ServerPing.Builder ping = event.getPing().asBuilder();

        try {

           if (currentplayers > 0) {
               ping.onlinePlayers(currentplayers);
            }

           if (maxplayers > 0) {
               ping.maximumPlayers(currentplayers);
           }

           if (!motd.isEmpty()) {
               ping.description(MiniMessage.markdown().parse(motd));
           }

        } finally {
            event.setPing(ping.build());
        }
    }

}