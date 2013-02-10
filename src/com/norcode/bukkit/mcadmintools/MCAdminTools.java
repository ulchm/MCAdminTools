package com.norcode.bukkit.mcadmintools;
import java.text.MessageFormat;

import org.bukkit.plugin.java.JavaPlugin;

import com.norcode.bukkit.mcadmintools.commands.DispelCommand;
import com.norcode.bukkit.mcadmintools.commands.FeedCommand;
import com.norcode.bukkit.mcadmintools.commands.HealCommand;
import com.norcode.bukkit.mcadmintools.commands.KillCommand;
import com.norcode.bukkit.mcadmintools.commands.SplashCommand;
import com.norcode.bukkit.mcadmintools.commands.SuicideCommand;
import com.norcode.bukkit.mcadmintools.commands.ViewEnderchestCommand;
import com.norcode.bukkit.mcadmintools.commands.ViewInventoryCommand;
import com.norcode.bukkit.mcadmintools.commands.WorkbenchCommand;
import com.norcode.bukkit.mcadmintools.commands.XPCommand;


public class MCAdminTools extends JavaPlugin {
	ConfigAccessor messages;
	
	@Override
	public void onLoad() {
		super.onLoad();
		messages = new ConfigAccessor(this, "messages.yml");
	}
	
	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		reloadConfig();
		messages.getConfig().options().copyDefaults(true);
		messages.saveDefaultConfig();
		messages.reloadConfig();
		
		getServer().getPluginCommand("heal").setExecutor(new HealCommand(this));
		getServer().getPluginCommand("feed").setExecutor(new FeedCommand(this));
		getServer().getPluginCommand("kill").setExecutor(new KillCommand(this));
		getServer().getPluginCommand("dispel").setExecutor(new DispelCommand(this));
		getServer().getPluginCommand("suicide").setExecutor(new SuicideCommand(this));
		getServer().getPluginCommand("invsee").setExecutor(new ViewInventoryCommand(this));
		getServer().getPluginCommand("enderchest").setExecutor(new ViewEnderchestCommand(this));
		getServer().getPluginCommand("xp").setExecutor(new XPCommand(this));
		getServer().getPluginCommand("workbench").setExecutor(new WorkbenchCommand(this));
		getServer().getPluginCommand("splash").setExecutor(new SplashCommand(this));

	}

	public String getMsg(String key, Object... args) {
		String tpl = messages.getConfig().getString(key);
		if (tpl == null) {
			tpl = "[" + key + "] ";
			for (int i=0;i<args.length;i++) {
				tpl += "{"+i+"}, ";
			}
		}
		return MessageFormat.format(tpl,args);
	}
}
