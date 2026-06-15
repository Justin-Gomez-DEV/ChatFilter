package dev.gomez.java;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;

public final class ChatFilter extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("ChatFilter is running");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        List<String> badWords = getConfig().getStringList("Blacklisted-Words");

        getServer().getPluginManager().registerEvents(new CFEvent(this, badWords), this);
    }

    @Override
    public void onDisable() {
        System.out.println("ChatFilter is shutting down");
    }
}
