package com.norcode.bukkit.mcadmintools.commands;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.norcode.bukkit.mcadmintools.MCAdminTools;

public class CookCommand extends BaseCommand {

	public CookCommand(MCAdminTools plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		ConfigurationSection cfg = plugin.getConfig().getConfigurationSection("cook-mappings");
		String key = ((Player)sender).getItemInHand().getType().name();
		String newMaterialName = cfg.getString(key);
		if (newMaterialName == null) {
			((Player)sender).sendMessage(plugin.getMsg("errors.cant-cook-item"));
			return true;
		}
		Material newMat = null;
		Short dur = null;
		if (newMaterialName.contains(":")) {
			String[] materials = newMaterialName.split(":"); 
			newMat = Material.getMaterial(materials[0]);
			dur = Short.parseShort(materials[1]);
		} else {
			newMat = Material.getMaterial(newMaterialName);
		}
		
		if (newMat == null) {
			sender.sendMessage(plugin.getMsg("errors.cant-cook-item"));
			return true;
		}
		((Player)sender).getItemInHand().setType(newMat);
		if (dur != null) {
			((Player)sender).getItemInHand().setDurability(dur);
		}
		((Player)sender).sendMessage(plugin.getMsg("cooked"));
		return true;
	}

}
