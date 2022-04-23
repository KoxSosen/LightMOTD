package com.github.koxsosen.lightmotd.parse;

import com.github.koxsosen.lightmotd.LightMOTD;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class AdventureParse {

    public static Component formatted(String str) {

        return MiniMessage.miniMessage().deserialize(str);

    }

}
