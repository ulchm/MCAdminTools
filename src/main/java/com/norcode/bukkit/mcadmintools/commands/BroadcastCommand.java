package com.norcode.bukkit.mcadmintools.commands;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andre
 * Date: 6/18/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class BroadcastCommand extends BaseCommand {

    public BroadcastCommand(MCAdminTools plugin) {
        super(plugin);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, LinkedList<String> args) {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, LinkedList<String> args) {
        String msg = "";
        for (String a: args) {
            msg += a + " ";
        }
        if (msg.endsWith(" ")) {
            msg = msg.substring(0, msg.length()-1);
        }
        plugin.getServer().broadcastMessage(msg);
        return true;
    }
}
