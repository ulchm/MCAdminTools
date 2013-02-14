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
		String newMaterialName = cfg.getString(((Player)sender).getItemInHand().getType().name());
		if (newMaterialName == null) {
			((Player)sender).sendMessage(plugin.getMsg("errors.cant-cook-item"));
			return true;
		}
		Material newMat = null;
		if (newMaterialName.contains(":")) {
			String[] materials = newMaterialName.split(":"); 
			newMat = Material.getMaterial(materials[0]);
			((Player)sender).getItemInHand().setType(newMat);
			((Player)sender).getItemInHand().setDurability(Short.parseShort(materials[1]));
			
		} else {
			newMat = Material.getMaterial(newMaterialName);
			((Player)sender).getItemInHand().setType(newMat);
		}
		((Player)sender).sendMessage(plugin.getMsg("cooked"));
		return true;
	}

}
