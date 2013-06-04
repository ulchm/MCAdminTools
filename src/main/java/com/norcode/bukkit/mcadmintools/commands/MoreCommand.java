package com.norcode.bukkit.mcadmintools.commands;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.norcode.bukkit.mcadmintools.MCAdminTools;

public class MoreCommand extends BaseCommand {

	public MoreCommand(MCAdminTools plugin) {
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
		if (args.size() == 0)
		{
			Player player =(Player)sender; 
			ItemStack s = player.getItemInHand();
			int max = s.getType().getMaxStackSize();
			player.getItemInHand().setAmount(max);
			player.sendMessage(plugin.getMsg("stack-filled"));
		}
		return false;
	}

}
