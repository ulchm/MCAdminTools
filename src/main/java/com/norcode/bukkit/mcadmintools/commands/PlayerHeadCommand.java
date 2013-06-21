package com.norcode.bukkit.mcadmintools.commands;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.LinkedList;
import java.util.List;

public class PlayerHeadCommand extends BaseCommand {

    public PlayerHeadCommand(MCAdminTools plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, LinkedList<String> args) {
        Player targetPlayer = null;

        if (args.size() == 2) {
            List<Player> matches = plugin.getServer().matchPlayer(args.peekLast());
            if (matches.size() > 1) {
                sender.sendMessage(plugin.getMsg("errors.ambiguous-player"));
                return true;
            } else if (matches.size() < 1) {
                sender.sendMessage(plugin.getMsg("errors.player-not-found"));
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

        OfflinePlayer headOwner = plugin.getServer().getOfflinePlayer(args.peek());
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) plugin.getServer().getItemFactory().getItemMeta(Material.SKULL_ITEM);
        meta.setOwner(headOwner.getName());
        head.setItemMeta(meta);

        targetPlayer.getInventory().addItem(head);
        if (targetPlayer.getName().equals(sender.getName())) {
            sender.sendMessage("Spawning " + headOwner + "'s head.");
        } else {
            sender.sendMessage("Gave " + headOwner + "'s head to " + targetPlayer + ".");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, LinkedList<String> args) {
        return null;
    }

}
