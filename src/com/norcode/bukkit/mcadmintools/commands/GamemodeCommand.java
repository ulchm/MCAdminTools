package com.norcode.bukkit.mcadmintools.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import com.norcode.bukkit.mcadmintools.exceptions.AmbiguousPlayerName;
import com.norcode.bukkit.mcadmintools.exceptions.PlayerNotFound;

public class GamemodeCommand extends BaseCommand {

	public GamemodeCommand(MCAdminTools plugin) {
		super(plugin);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		List<String> matches = new ArrayList<>();
		boolean hasMode = false;
		switch (alias.toLowerCase()) {
		case "gmc":
		case "gms":
			hasMode = true;
			break;
		}
		if (!hasMode) {
			if (args.size() == 1) {
				if ("survival".startsWith(args.peek().toLowerCase())) matches.add("survival");
				if ("creative".startsWith(args.peek().toLowerCase())) matches.add("creative");
				if ("adventure".startsWith(args.peek().toLowerCase())) matches.add("adventure");
			} else if (args.size() == 2) {
				for (Player p: plugin.getServer().matchPlayer(args.get(1))) {
					if (p.getName().toLowerCase().startsWith(args.get(1).toLowerCase())) {
						matches.add(p.getName());
					}
				}
			}
		}
		if (hasMode && args.size() == 1) {
			for (Player p: plugin.getServer().matchPlayer(args.peek())) {
				if (p.getName().toLowerCase().startsWith(args.peek().toLowerCase())) {
					matches.add(p.getName());
				}
			}
		} 
		
		return matches;
	}

	public static Integer parseMode(String s) {
		Integer val = null;
		try {
			val = Integer.parseInt(s);
			if (val < 0 || val > 2) {
				val = null;
			}
		} catch (IllegalArgumentException ex) {
			if ("survival".startsWith(s.toLowerCase())) {
				val = 0;
			} else if ("creative".startsWith(s.toLowerCase())) {
				val = 1;
			} else if ("adventure".startsWith(s.toLowerCase())) {
				val = 2;
			}
		}
		return val;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		Integer mode = null; 
		switch (alias.toLowerCase()) {
		case "gmc":
			mode = 1;
			break;
		case "gms":
			mode = 0;
			break;
		default:
			mode = parseMode(args.pop());
			if (mode == null) {
				sender.sendMessage(plugin.getMsg("errors.unknown-gamemode", args.peek()));
				return true;
			}
		}
		Player target = (Player)sender;
		if (args.size() == 1) {
			try {
				target = parsePlayer(args.peek());
			} catch (AmbiguousPlayerName ex) {
				sender.sendMessage(plugin.getMsg("errors.ambiguous-player", args.peek()));
				return true;
			} catch (PlayerNotFound ex) {
				sender.sendMessage(plugin.getMsg("errors.player-not-found", args.peek()));
				return true;
			}
		}
		target.setGameMode(GameMode.getByValue(mode));
		return true;
	}

}
