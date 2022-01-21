package com.github.koxsosen.lightmotd.parse;

import com.github.koxsosen.lightmotd.LightMOTD;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class AdventureParse {


    static String text = LightMOTD.getConfig().textmotd();

    public static Component formatted() {

        return MiniMessage.markdown().parse(text);

    }

}
