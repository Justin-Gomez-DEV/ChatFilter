package dev.gomez.chatfilter;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.Locale;

public class CFEvent implements Listener {

    private List<String> badWords;

    private ChatFilter chatFilter;

    public CFEvent(ChatFilter chatFilter, List<String> badWords) {
        this.chatFilter = chatFilter;
        this.badWords = badWords;
    }


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        String message = event.getMessage().toLowerCase(Locale.ROOT);

        for(String badWord : badWords){
            if (message.contains(badWord.toLowerCase(Locale.ROOT))) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("[" + ChatColor.RED + "!" + ChatColor.GRAY + "] Your message wasn't sent because it contained a blacklisted word: " + ChatColor.RED + badWord);
                break;
            }
        }

    }

}