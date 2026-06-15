package dev.gomez;

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

    private String normalize(String input) {
        input = input.toLowerCase(Locale.ROOT);

        StringBuilder result = new StringBuilder();
        char previous = '\0';

        for (char c : input.toCharArray()) {

            if (c == previous) {
                continue;
            }

            result.append(c);
            previous = c;
        }

        return result.toString();
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = normalize(event.getMessage());

        for (String badWord : badWords) {
            if (message.contains(normalize(badWord))) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("[" + ChatColor.RED + "!" + ChatColor.GRAY + "] Your message wasn't sent because it contained a blacklisted word: " + ChatColor.RED + badWord);
                break;
            }
        }
    }
}
