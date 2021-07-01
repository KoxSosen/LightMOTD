package com.github.koxsosen.lightmotd.serburping;

import com.github.koxsosen.lightmotd.LightMOTD;
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

        final ServerPing.Builder ping = event.getPing().asBuilder();

        final int currentplayers = lightMOTD.getConfig().currentplayers();

        final int maxplayers = lightMOTD.getConfig().maxplayers();

        Boolean onemorejust = lightMOTD.getConfig().onejustmore();

        Boolean nulltheplayers = lightMOTD.getConfig().hiddentheplayers();

        final String motd = lightMOTD.getConfig().textmotd();

        final Component parsed = MiniMessage.markdown().parse(motd);

        try {

           if (currentplayers > 0) {
               ping.onlinePlayers(currentplayers);
            }

           if (maxplayers > 0) {
               ping.maximumPlayers(maxplayers);
           }

           if (!motd.isEmpty()) {
               ping.description(parsed);
           }

           if (onemorejust) {
             ping.maximumPlayers(1 + ping.getOnlinePlayers());
           }

           if (nulltheplayers) {
               ping.nullPlayers();
           }

        } finally {
            event.setPing(ping.build());
        }
    }

}