package com.norcode.bukkit.mcadmintools.commands;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.norcode.bukkit.mcadmintools.MCAdminTools;

public class JumpCommand extends BaseCommand {

	public JumpCommand(MCAdminTools plugin) {
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
		Player player = (Player)sender;
		Block b = player.getTargetBlock(null, 300);
		Location loc = new Location(b.getLocation().getWorld(), b.getLocation().getX(), b.getLocation().getY()+1, b.getLocation().getZ());
		loc.setPitch(player.getLocation().getPitch());
		loc.setYaw(player.getLocation().getYaw());
		player.teleport(loc, TeleportCause.COMMAND);
		return true;
	}

}
