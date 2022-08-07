package com.github.koxsosen.lightmotd.command;



import com.github.koxsosen.lightmotd.config.NewConfigManager;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.plugin.annotation.DataDirectory;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ReloadCommand implements SimpleCommand {

    @Override
    public void execute(final Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();

        if (args[0].equals("reload")) {
            source.sendMessage(Component.text("Reloading LightMOTD...").color(NamedTextColor.AQUA));
            // TODO: Fix path execution
            NewConfigManager.loadconfig(Path.of("."));
                    source.sendMessage(Component.text("Sucessfully reloaded LightMOTD!").color(NamedTextColor.GREEN));
        }
    }

    @Override
    public boolean hasPermission(final Invocation invocation) {
        return invocation.source().hasPermission("lightmotd.reload");
    }

    @Override
    public CompletableFuture <List<String>> suggestAsync(final Invocation invocation) {
        return CompletableFuture.completedFuture(List.of("reload"));
    }



}
