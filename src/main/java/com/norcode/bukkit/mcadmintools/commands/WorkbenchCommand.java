package com.norcode.bukkit.mcadmintools.commands;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.norcode.bukkit.mcadmintools.MCAdminTools;

public class WorkbenchCommand extends BaseCommand {

	public WorkbenchCommand(MCAdminTools plugin) {
		super(plugin);
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
		// TODO Auto-generated method stub
		((Player)sender).openWorkbench(null, true);
		return true;
	}

}
