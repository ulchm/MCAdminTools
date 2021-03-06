package com.norcode.bukkit.mcadmintools.commands;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import com.norcode.bukkit.mcadmintools.exceptions.AmbiguousPlayerName;
import com.norcode.bukkit.mcadmintools.exceptions.PlayerNotFound;

public class ViewInventoryCommand extends BaseCommand {

	public ViewInventoryCommand(MCAdminTools plugin) {
		super(plugin);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		if (args.size() != 1) return false;
		Player target = null;
		try {
			target = parsePlayer(args.peek());
		} catch (AmbiguousPlayerName ex) {
			sender.sendMessage(plugin.getMsg("errors.ambiguous-player", args.peek()));
			return true;
		} catch (PlayerNotFound ex) {
			sender.sendMessage(plugin.getMsg("errors.player-not-found", args.peek()));
			return true;
		}
		((Player)sender).openInventory(target.getInventory());
		return true;
	}

}
