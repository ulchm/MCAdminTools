package com.norcode.bukkit.mcadmintools.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.norcode.bukkit.mcadmintools.MCAdminTools;

public class SetSpawnCommand extends BaseCommand {

    public SetSpawnCommand(MCAdminTools plugin) {
        super(plugin);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command,
            String alias, LinkedList<String> args) {
        return new ArrayList<String>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String alias, LinkedList<String> args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command cannot be run from the console.");
            return true;
        }
        Player player = (Player) sender;
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        player.getWorld().setSpawnLocation(x,y,z);
        player.sendMessage(plugin.getMsg("world-spawn-set", player.getWorld().getName(), x, y, z));
        return true;
    }

}
