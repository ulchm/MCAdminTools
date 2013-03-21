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

public class XPCommand extends BaseCommand {

	public XPCommand(MCAdminTools plugin) {
		super(plugin);
	}

	protected int xpToLevel(int lvl) {
		int xp = 0;
		for (int i=1; i<=lvl; i++) {
			if (i <= 16) xp += 17;
			else if (i <= 31) xp += (i-16) * 3 + 17;
			else xp += (i-31) * 7 + 62;
		}
		return xp;
	}


    // Sets given player's XP
	protected void setXP(Player p, int xp) {
		p.setExp(0);
		p.setTotalExperience(0);
		p.setLevel(0);
		p.giveExp(xp);
		// wierd fix
		if (xpToLevel(p.getLevel() + 1) == p.getTotalExperience()) {
			p.setLevel(p.getLevel() + 1);
			p.setExp(0);
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		// TODO Auto-generated method stub
		List<String> matches = new ArrayList<>();
		if (args.size() == 1) {
			if ("give".startsWith(args.peek().toLowerCase())) matches.add("give");
			if ("set".startsWith(args.peek().toLowerCase())) matches.add("set");
			if ("take".startsWith(args.peek().toLowerCase())) matches.add("take");
		} else if (args.size() == 2) {
			return null; // default online player match
		}
		return matches;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		
		if (args.size() < 2) {
			return false;
		}
		String action = args.pop().toLowerCase();		
		if (!sender.hasPermission("mcadmintools.xp." + action)) {
			sender.sendMessage(plugin.getMsg("errors.no-permission"));
			return true;
		}
		
		Player target = (Player)sender;
		if (args.size() == 2) {
			if (!sender.hasPermission("mcadmintools.xp." + action + ".others")) {
				sender.sendMessage(plugin.getMsg("errors.no-permission"));
				return true;
			}
			try {
				target = parsePlayer(args.peek());
			} catch (AmbiguousPlayerName ex) {
				sender.sendMessage(plugin.getMsg("errors.ambiguous-player", args.peek()));
				return true;
			}
			catch (PlayerNotFound ex) {
				sender.sendMessage(plugin.getMsg("errors.player-not-found", args.peek()));
				return true;
			}
			args.pop();
		}
		Integer amount = null;
		boolean levels = false;
		String arg = args.pop();
		if (arg.toLowerCase().contains("l")) {
			levels = true;
			arg = arg.toLowerCase().replace("l", "");
		}
		try {
			amount = Integer.parseInt(arg);
		} catch (IllegalArgumentException ex) {
			sender.sendMessage(plugin.getMsg("errors.invalid-amount", arg));
			return true;
		}
		// Thanks to XPBank for this.
		switch (action) {
		case "give":
			if (levels) {
				int xpToAdd;
				int xpNextLevel = xpToLevel(target.getLevel() + 1);
				int xpTNL = xpNextLevel - target.getTotalExperience();
				int futureLevel = target.getLevel() + amount;
				if (xpTNL == 0) {
					xpToAdd = xpToLevel(futureLevel) - xpToLevel(target.getLevel());
				} else {
					xpToAdd = xpToLevel(futureLevel) - xpNextLevel;
				}
				xpToAdd += xpTNL;
				setXP(target, target.getTotalExperience() + xpToAdd);
			} else {
				setXP(target, target.getTotalExperience() + amount);
			}
			break;
		case "take":
			if (levels) {
				setXP(target, xpToLevel(target.getLevel()-amount));
			} else {
				setXP(target, target.getTotalExperience() + amount);
			}
			break;
		case "set":
			if (levels) {
				setXP(target, xpToLevel(amount));
			} else {
				setXP(target, amount);
			}
			break;
		default:
			return false;
		}
		sender.sendMessage(plugin.getMsg("xp-set", target.getName()));
		return true;
	}

}
