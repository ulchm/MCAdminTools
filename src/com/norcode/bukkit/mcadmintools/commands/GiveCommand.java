package com.norcode.bukkit.mcadmintools.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import com.norcode.bukkit.mcadmintools.exceptions.AmbiguousPlayerName;
import com.norcode.bukkit.mcadmintools.exceptions.PlayerNotFound;

public class GiveCommand extends BaseCommand {

	public GiveCommand(MCAdminTools plugin) {
		super(plugin);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		if (args.size() == 1) return null;
		List<String> matches = new ArrayList<>();
		if (args.size() == 2) {
			String partial = args.peekLast().toLowerCase();
			for (Material m: Material.values()) {
				matches.add(m.name());
			}
			return matches;
		}
		if (args.size() == 3) {
			if (args.peekLast().equals("")) {
				matches.add(Integer.toString(findMaterial(args.get(1)).getMaxStackSize()));
				return matches;
			}
		}
		return matches;
	}
	
	public Material findMaterial(String partial) {
		int matId;
		try {
			matId = Integer.parseInt(partial);
			Material mat = Material.getMaterial(matId);
			if (mat != null) return mat;
		} catch (IllegalArgumentException ex) {};
		List<Material> matches = new ArrayList<>();
		for (Material m: Material.values()) {
			if (m.name().toLowerCase().startsWith(partial.toLowerCase())) {
				if (m.name().toLowerCase().equals(partial.toLowerCase())) {
					return m;
				}
				matches.add(m);
			}
		}
		if (matches.size() != 1) {
			return null;
		}
		return matches.get(0);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		if (args.size() < 2) {
			return false;
		}
		String playerName = args.pop();
		String itemName = args.pop();
		Player player;
		try {
			player = parsePlayer(playerName);
		} catch (PlayerNotFound e) {
			sender.sendMessage(plugin.getMsg("errors.ambiguous-player", args.peek()));
			return true;
		} catch (AmbiguousPlayerName e) {
			sender.sendMessage(plugin.getMsg("errors.player-not-found", args.peek()));
			return true;
		}
		Material mat = findMaterial(itemName);
		if (mat == null) {
			sender.sendMessage(plugin.getMsg("errors.unknown-material",itemName));
			return true;
		}
		Integer qty = null; 
		if (args.size() > 0) {
			try {
				qty = Integer.parseInt(args.pop());
			} catch (IllegalArgumentException ex) {}
		}
		Short data = 0;
		if (args.size() > 0) {
			data = Short.parseShort(args.pop());
		}
		
		if (qty == null) {
			qty = mat.getMaxStackSize();
		}
		int take = 0;
		
		while (qty > 0) {
			if (qty > mat.getMaxStackSize()) {
				take = mat.getMaxStackSize(); 
			} else {
				take = qty; 
			}
			qty -= take;
			player.getInventory().addItem(new ItemStack(mat, take, data));	
		}
		return true;
	}

}
