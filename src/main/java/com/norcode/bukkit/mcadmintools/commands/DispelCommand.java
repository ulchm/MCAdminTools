package com.norcode.bukkit.mcadmintools.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;


import com.norcode.bukkit.mcadmintools.MCAdminTools;
import com.norcode.bukkit.mcadmintools.exceptions.AmbiguousPlayerName;
import com.norcode.bukkit.mcadmintools.exceptions.PlayerNotFound;

public class DispelCommand extends BaseCommand {

	public DispelCommand(MCAdminTools plugin) {
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
			target = (Player)sender;
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
		Iterator<PotionEffect> effectIt = target.getActivePotionEffects().iterator();
		PotionEffect eff;
		while (effectIt.hasNext()) {
			eff = effectIt.next();
			target.removePotionEffect(eff.getType());
		}
		sender.sendMessage(plugin.getMsg("player-dispelled", target.getName()));
		return true;
	}
}
