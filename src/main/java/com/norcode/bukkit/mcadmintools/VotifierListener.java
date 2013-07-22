package com.norcode.bukkit.mcadmintools;

import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class VotifierListener implements Listener {

    MCAdminTools plugin;

    public VotifierListener(MCAdminTools mcAdminTools) {
        this.plugin = mcAdminTools;
    }

    @EventHandler
    public void onVote(VotifierEvent event) {
        String username = event.getVote().getUsername();
        OfflinePlayer op = plugin.getServer().getOfflinePlayer(username);
        if (op.hasPlayedBefore()) {
            for (String cmd: plugin.getConfig().getStringList("votifier-commands")) {
                cmd = cmd.replace("__name__", op.getName());
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), cmd);
            }
        }
    }
}
