package com.norcode.bukkit.mcadmintools.commands;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FlySpeedCommand extends BaseCommand {

    public FlySpeedCommand(MCAdminTools plugin) {
        super(plugin);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, LinkedList<String> args) {
        return new ArrayList<String>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, LinkedList<String> args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getMsg("Command cannot be run from the console."));
            return true;
        }
        if (args.size() == 0) {
            sender.sendMessage(String.format("Current Flight Speed: %.1f", ((Player) sender).getFlySpeed() * 10));
            return true;
        }
        float speed = 1;
        try {
            speed = Float.parseFloat(args.peek());
        } catch (IllegalArgumentException ex) {
            sender.sendMessage(plugin.getMsg("invalid-number", args.peek()));
            return true;
        }
        if (speed > 5) {
            speed = 5;
        }

        ((Player) sender).setFlySpeed(speed/10.0f);
        return true;
    }
}
