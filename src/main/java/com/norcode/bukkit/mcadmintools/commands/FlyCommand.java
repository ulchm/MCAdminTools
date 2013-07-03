package com.norcode.bukkit.mcadmintools.commands;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andre
 * Date: 03/07/13
 * Time: 8:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class FlyCommand extends BaseCommand {

    public FlyCommand(MCAdminTools plugin) {
        super(plugin);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, LinkedList<String> args) {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, LinkedList<String> args) {
        Player targetPlayer = null;
        if (args.size() == 1) {
            if (!sender.hasPermission("mcadmintools.fly.others")) {
                sender.sendMessage("You don't have permission to give flight to other people.");
                return true;
            }
            List<Player> matches = plugin.getServer().matchPlayer(args.peek());
            if (matches.size() != 1) {
                sender.sendMessage("Unknown player: " + args.peek());
                return true;
            }
            targetPlayer = matches.get(0);
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command must be run by a player.");
                return true;
            }
            targetPlayer = (Player) sender;
        }
        boolean state = !targetPlayer.getAllowFlight();
        if (state) {
            sender.sendMessage("Flight enabled for " + targetPlayer.getName() + ".");
        } else {
            sender.sendMessage("Flight disabled for " + targetPlayer.getName() + ".");
        }
        targetPlayer.setAllowFlight(state);
        return true;
    }
}
