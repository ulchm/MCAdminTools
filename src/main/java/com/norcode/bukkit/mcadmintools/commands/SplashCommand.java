package com.norcode.bukkit.mcadmintools.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import com.norcode.bukkit.mcadmintools.exceptions.AmbiguousPlayerName;
import com.norcode.bukkit.mcadmintools.exceptions.PlayerNotFound;

public class SplashCommand extends BaseCommand {

	
	public SplashCommand(MCAdminTools plugin) {
		super(plugin);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		List<String> matches = new ArrayList<>();
		if (args.size() == 1) {
			for (Player p: plugin.getServer().matchPlayer(args.peek())) {
				matches.add(p.getName());
			}
		}
		if (args.size() <= 2) {
			for (PotionEffectType et: PotionEffectType.values()) {
				plugin.getLogger().info("EffectType: " + et);
				if (et == null) continue;
				if (et.getName().toLowerCase().startsWith(args.peekLast().toLowerCase())) {
					matches.add(et.getName());
				}
			}
		}
		return matches;
	
	}

	public boolean isPotionEffect(String s) {
		for (PotionEffectType et: PotionEffectType.values()) {
			if (et == null) continue;
			if (et.getName().equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {

		if (args.size() == 0) {
			return false;
		}
		Player targetPlayer = (Player)sender;
		
		if (!isPotionEffect(args.peek())) {
			try {
				targetPlayer = parsePlayer(args.peek());
				args.pop();
			} catch (AmbiguousPlayerName ex) {
				sender.sendMessage(plugin.getMsg("errors.ambiguous-player", args.peek()));
				return true;
			} catch (PlayerNotFound ex) {
				sender.sendMessage(plugin.getMsg("errors.player-not-found", args.peek()));
				return true;
			}
		}
		
		if (!isPotionEffect(args.peek())) {
			sender.sendMessage(plugin.getMsg("errors.expecting-potion-effect"));
			return true;
		}
		PotionEffectType type = PotionEffectType.getByName(args.pop().toUpperCase());
		int duration = 600;
		if (args.size() >= 1) {
			try {
				duration = Integer.parseInt(args.peek());
			} catch (IllegalArgumentException ex) {
				sender.sendMessage(plugin.getMsg("errors.invalid-number", args.peek()));
				return true;
			}
			args.pop();
		}
		int amplifier = 1;
		if (args.size() >= 1) {
			try {
				amplifier = Integer.parseInt(args.peek());
			} catch (IllegalArgumentException ex) {
				sender.sendMessage(plugin.getMsg("errors.invalid-number", args.peek()));
				return true;
			}
			args.pop();
		}
		PotionEffect effect = new PotionEffect(type, duration, amplifier);
		targetPlayer.addPotionEffect(effect);
		sender.sendMessage(plugin.getMsg("potion-effect-applied", targetPlayer.getName()));
		return true;
	}
}
