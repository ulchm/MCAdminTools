package com.norcode.bukkit.mcadmintools.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import com.norcode.bukkit.mcadmintools.MCAdminTools;
import com.norcode.bukkit.mcadmintools.exceptions.AmbiguousPlayerName;
import com.norcode.bukkit.mcadmintools.exceptions.PlayerNotFound;

public class KillCommand extends BaseCommand {

	public KillCommand(MCAdminTools plugin) {
		super(plugin);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		List<String> matches = new ArrayList<>();
		if (args.size() == 1) {
			for (Player p: plugin.getServer().matchPlayer(args.peek())) {
				if ((sender instanceof Player) && ((Player)sender).canSee(p)) {
					matches.add(p.getName());
				}
			}
		}
		return matches;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		Player target = null;
		if (args.size() == 0) {
			return false;
		} else {
			try {
				target = parsePlayer(args.peek());
			} catch (AmbiguousPlayerName ex) {
				sender.sendMessage(plugin.getMsg("errors.ambiguous-player", args.peek()));
			} catch (PlayerNotFound ex) {
				sender.sendMessage(plugin.getMsg("errors.player-not-found", args.peek()));
			}
			if (target == null) return true;
		}
		target.setHealth(0);
		return true;
	}


	
}
