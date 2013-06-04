package com.norcode.bukkit.mcadmintools.commands;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import com.norcode.bukkit.mcadmintools.commands.BaseCommand;


public class RepairCommand extends BaseCommand {

	public RepairCommand(MCAdminTools plugin) {
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
		Player player = (Player)sender;
		ItemStack s = player.getItemInHand();
		if (s == null || s.getType().isBlock() || s.getDurability() == 0) {
			sender.sendMessage(plugin.getMsg("errors.cannot-repair"));
			return true;
		}
		s.setDurability((short)0);
		sender.sendMessage(plugin.getMsg("item-repaired"));
		return true;
	}

}
