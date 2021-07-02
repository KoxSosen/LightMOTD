<div>
<center>
<h2> ⚡️ LightMOTD ⚡ ️</h2>

![image](https://user-images.githubusercontent.com/67807644/124130126-bad22200-da7e-11eb-85e6-9786fbf26662.png)

[![GitHub release (latest by date)](https://img.shields.io/github/v/release/KoxSosen/LightMOTD?label=Latest%20Release)](https://github.com/KoxSosen/LightMOTD/releases/latest/)

</center>
</div>
 
**A MOTD plugin for Velocity.**

[![Latest Build](https://github.com/KoxSosen/LightMOTD/actions/workflows/gradle.yml/badge.svg?label=Get&20the&20latest)](https://github.com/KoxSosen/LightMOTD/actions)

#### Features:

- Change player cap.
- Change current player count.
- JustOneMore
- Null the playercount, so the current players won't show up.
- MiniMessage support.
- Configurate hocon config.
- Very lightweight: No bloat features.
- Only shade in MiniMessage, nothing else.

#### Config:

```hocon
LightMOTD {
    playercount {
        # The amount of players to add to your current players. 
        # Set it to 0 to disable.
        current-players=0
        # If this is set to true, the player count will show up as question marks.
        hiddenplayers=false
        # If this is set to true, the max playercount will always be as many players as you have + 1.
        # Set it to false to disable.
        justonemore=false
        # The max amount of players which will be shown. Set it to 0 to disable.
        max-players=0
    }
    # This is where you can set the MOTD. Set it to empty to disable it.
    # It uses the MiniMessage format. You can do <green>, or <#00ff00>R G B!.
    # It also parses the MARKDOWN syntax.
    text="<white>This is the default MOTD of </white><#b02e26>Light<#825432>M<#80c71f>O<#b02e26>T<#825432>D<white>.\n<reset><gray>This is a new line :P </gray><#b02e26>R <#825432>G <#80c71f>B<reset>!."
}
```
**Null the playercount:**

![image](https://user-images.githubusercontent.com/67807644/124133637-5022e580-da82-11eb-97a4-e7ee0d419552.png)

**JustOneMore:** 

![image](https://user-images.githubusercontent.com/67807644/124133930-9aa46200-da82-11eb-88b1-64e0142accc7.png)

#### Upcoming Features:

- An option to play around with math to define the amount of players.

#### Planned Features:

- Random favicon each refresh. (can be  disabled)
- Random MOTD each refresh.
- Retrive images from TheCatApi to set a random cat image as the favicon.

  
