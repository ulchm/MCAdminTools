package com.norcode.bukkit.mcadmintools;
import java.text.MessageFormat;

import com.norcode.bukkit.mcadmintools.commands.*;
import org.bukkit.plugin.java.JavaPlugin;


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
		saveConfig();
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
		getServer().getPluginCommand("smite").setExecutor(new SmiteCommand(this));
		getServer().getPluginCommand("gamemode").setExecutor(new GamemodeCommand(this));
		getServer().getPluginCommand("repair").setExecutor(new RepairCommand(this));
		getServer().getPluginCommand("jump").setExecutor(new JumpCommand(this));
		getServer().getPluginCommand("more").setExecutor(new MoreCommand(this));
		getServer().getPluginCommand("give").setExecutor(new GiveCommand(this));
		getServer().getPluginCommand("cook").setExecutor(new CookCommand(this));
		getServer().getPluginCommand("setspawn").setExecutor(new SetSpawnCommand(this));
		getServer().getPluginCommand("spawn").setExecutor(new SpawnCommand(this));
		getServer().getPluginCommand("enchant").setExecutor(new EnchantCommand(this));
        getServer().getPluginCommand("fly").setExecutor(new FlyCommand(this));
        getServer().getPluginCommand("flyspeed").setExecutor(new FlySpeedCommand(this));
        getServer().getPluginCommand("broadcast").setExecutor(new BroadcastCommand(this));
        getServer().getPluginCommand("playerhead").setExecutor(new PlayerHeadCommand(this));
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
