package com.norcode.bukkit.mcadmintools.commands;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.LinkedList;
import java.util.List;

public class MCATCommand extends BaseCommand {
    public MCATCommand(MCAdminTools plugin) {
        super(plugin);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, LinkedList<String> args) {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, LinkedList<String> args) {
        if (args.size() == 1) {
            if (args.peek().equalsIgnoreCase("reloadconfig")) {
                plugin.reloadConfig();
                sender.sendMessage("Configuration Reloaded.");
                return true;
            }
        }
        return false;
    }
}
