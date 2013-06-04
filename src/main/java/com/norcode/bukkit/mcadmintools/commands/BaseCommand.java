package com.norcode.bukkit.mcadmintools.commands;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import com.norcode.bukkit.mcadmintools.exceptions.AmbiguousPlayerName;
import com.norcode.bukkit.mcadmintools.exceptions.PlayerNotFound;

public abstract class BaseCommand implements TabExecutor {
	protected MCAdminTools plugin;
	
	public BaseCommand(MCAdminTools plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command,
			String alias, String[] args) {
		return onTabComplete(sender, command, alias, new LinkedList<String>(Arrays.asList(args)));
	}

	public abstract List<String> onTabComplete(CommandSender sender, Command command, String alias, LinkedList<String> args);
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		LinkedList<String> params = new LinkedList<String>(Arrays.asList(args));
		return onCommand(sender, command, label, params);
	}
	
	public abstract boolean onCommand(CommandSender sender, Command command, String alias, LinkedList<String> args);
	

	public Player parsePlayer(String s) throws PlayerNotFound, AmbiguousPlayerName {
		List<Player> matches = plugin.getServer().matchPlayer(s);
		if (matches.size() == 0) throw new PlayerNotFound();
		if (matches.size() > 1) throw new AmbiguousPlayerName();
		return matches.get(0);
	}
	
	protected static Boolean parseBoolean(String s) {
		switch (s.toLowerCase()) {
		case "on":
		case "yes":
		case "true":
		case "y":
		case "t":
		case "1":
			return true;
		case "off":
		case "no":
		case "false":
		case "n":
		case "f":
		case "0":
		case "-1":
			return false;
		default:
			return null;
		}
	}
}
