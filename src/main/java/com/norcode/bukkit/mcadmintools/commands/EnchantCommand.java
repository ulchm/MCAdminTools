package com.norcode.bukkit.mcadmintools.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.norcode.bukkit.mcadmintools.MCAdminTools;
import com.norcode.bukkit.mcadmintools.RN;

public class EnchantCommand extends BaseCommand {

	private static HashMap<String, Enchantment> enchantmentNames = new HashMap<>();
	private static HashMap<Enchantment, String> enchantmentDisplayNames = new HashMap<>();
	static {
		
		enchantmentNames.put("arrowdamage", Enchantment.ARROW_DAMAGE);
		enchantmentNames.put("power", Enchantment.ARROW_DAMAGE);
		enchantmentDisplayNames.put(Enchantment.ARROW_DAMAGE, "Power");
		
		enchantmentNames.put("arrowfire", Enchantment.ARROW_FIRE);
		enchantmentNames.put("flame", Enchantment.ARROW_FIRE);
		enchantmentDisplayNames.put(Enchantment.ARROW_FIRE, "Flame");
		
		
		enchantmentNames.put("arrowinfinite", Enchantment.ARROW_INFINITE);
		enchantmentNames.put("infinity", Enchantment.ARROW_INFINITE);
		enchantmentDisplayNames.put(Enchantment.ARROW_INFINITE, "Infinity");
		
		enchantmentNames.put("arrowknockback", Enchantment.ARROW_KNOCKBACK);
		enchantmentNames.put("punch", Enchantment.ARROW_KNOCKBACK);
		enchantmentDisplayNames.put(Enchantment.ARROW_KNOCKBACK, "Punch");
		
		enchantmentNames.put("damageall", Enchantment.DAMAGE_ALL);
		enchantmentNames.put("sharpness", Enchantment.DAMAGE_ALL);
		enchantmentDisplayNames.put(Enchantment.DAMAGE_ALL, "Sharpness");
		
		enchantmentNames.put("damagearthropods", Enchantment.DAMAGE_ARTHROPODS);
		enchantmentNames.put("baneofarthropods", Enchantment.DAMAGE_ARTHROPODS);
		enchantmentDisplayNames.put(Enchantment.DAMAGE_ARTHROPODS, "Bane of Arthropods");
		
		enchantmentNames.put("damageundead", Enchantment.DAMAGE_UNDEAD);
		enchantmentNames.put("smite", Enchantment.DAMAGE_UNDEAD);
		enchantmentDisplayNames.put(Enchantment.DAMAGE_UNDEAD, "Smite");
		
		enchantmentNames.put("digspeed", Enchantment.DIG_SPEED);
		enchantmentNames.put("efficiency", Enchantment.DIG_SPEED);
		enchantmentDisplayNames.put(Enchantment.DIG_SPEED, "Efficiency");
		
		
		enchantmentNames.put("durability", Enchantment.DURABILITY);
		enchantmentNames.put("unbreaking", Enchantment.DURABILITY);
		enchantmentDisplayNames.put(Enchantment.DURABILITY, "Unbreaking");
		
		enchantmentNames.put("fireaspect", Enchantment.FIRE_ASPECT);
		enchantmentNames.put("fire", Enchantment.FIRE_ASPECT);
		enchantmentDisplayNames.put(Enchantment.FIRE_ASPECT, "Fire Aspect");
		
		enchantmentNames.put("knockback", Enchantment.KNOCKBACK);
		enchantmentDisplayNames.put(Enchantment.KNOCKBACK, "Knockback");
		
		enchantmentNames.put("lootbonusblocks", Enchantment.LOOT_BONUS_BLOCKS);
		enchantmentNames.put("fortune", Enchantment.LOOT_BONUS_BLOCKS);
		enchantmentDisplayNames.put(Enchantment.LOOT_BONUS_BLOCKS, "Fortune");
		
		enchantmentNames.put("lootbonusmobs", Enchantment.LOOT_BONUS_MOBS);
		enchantmentNames.put("looting", Enchantment.LOOT_BONUS_MOBS);
		enchantmentDisplayNames.put(Enchantment.LOOT_BONUS_MOBS, "Looting");
		
		enchantmentNames.put("oxygen", Enchantment.OXYGEN);
		enchantmentNames.put("respiration", Enchantment.OXYGEN);
		enchantmentDisplayNames.put(Enchantment.OXYGEN, "Respiration");
		
		
		enchantmentNames.put("protectionenvironmental", Enchantment.PROTECTION_ENVIRONMENTAL);
		enchantmentNames.put("protection", Enchantment.PROTECTION_ENVIRONMENTAL);
		enchantmentDisplayNames.put(Enchantment.PROTECTION_ENVIRONMENTAL, "Protection");
		
		enchantmentNames.put("protectionexplosions", Enchantment.PROTECTION_EXPLOSIONS);
		enchantmentNames.put("blastprotection", Enchantment.PROTECTION_EXPLOSIONS);
		enchantmentDisplayNames.put(Enchantment.PROTECTION_EXPLOSIONS, "Blast Protection");
		
		enchantmentNames.put("protectionfall", Enchantment.PROTECTION_FALL);
		enchantmentNames.put("featherfalling", Enchantment.PROTECTION_FALL);
		enchantmentDisplayNames.put(Enchantment.PROTECTION_FALL, "Feather Falling");
		
		enchantmentNames.put("protectionfire", Enchantment.PROTECTION_FIRE);
		enchantmentNames.put("fireprotection", Enchantment.PROTECTION_FIRE);
		enchantmentDisplayNames.put(Enchantment.PROTECTION_FIRE, "Fire Protection");
		
		enchantmentNames.put("protectionprojectile", Enchantment.PROTECTION_PROJECTILE);
		enchantmentNames.put("projectileprotection", Enchantment.PROTECTION_PROJECTILE);
		enchantmentDisplayNames.put(Enchantment.PROTECTION_PROJECTILE, "Projectile Protection");
		
		enchantmentNames.put("silktouch", Enchantment.SILK_TOUCH);
		enchantmentDisplayNames.put(Enchantment.SILK_TOUCH, "Silk Touch");
		
		enchantmentNames.put("thorns", Enchantment.THORNS);
		enchantmentDisplayNames.put(Enchantment.THORNS, "Thorns");
		
		enchantmentNames.put("waterworker", Enchantment.WATER_WORKER);
		enchantmentNames.put("aquaaffinity", Enchantment.WATER_WORKER);
		enchantmentDisplayNames.put(Enchantment.WATER_WORKER, "Aqua Affinity");
		
	}
	public Enchantment getEnchantment(String name) {
		name = name.toLowerCase().replace("_","");
		if (enchantmentNames.containsKey(name)) {
			return enchantmentNames.get(name);
		} else {
			List<String> matches = new ArrayList<String>();
			for (String k: enchantmentNames.keySet()) {
				if (k.startsWith(name)) {
					matches.add(k);
				}
			}
			if (matches.size() == 1) {
				return enchantmentNames.get(matches.get(0));
			}
		}
		return null;
	}
	
	public EnchantCommand(MCAdminTools plugin) {
		super(plugin);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
		List<String> matches = new ArrayList<String>();
		if (args.size()%2!=0) {
			String p = args.peekLast().toLowerCase();
			for (String s: enchantmentNames.keySet()) {
				if (s.startsWith(p)) {
					matches.add(s);
				}
			}
		} 
		return matches;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String alias, LinkedList<String> args) {
			ItemStack s = ((Player)sender).getItemInHand();
			HashMap<Enchantment, Integer> enchantsToApply = new HashMap<>();
			List<Enchantment> enchantsToRemove = new ArrayList<>();
			String description = "";
			while (args.size() >= 2) {
				String ename = args.pop();
				Enchantment e = getEnchantment(ename);
				if (e == null) {
					sender.sendMessage(plugin.getMsg("errors.unknown-enchantment",ename));
					return true;
				}
				if (!e.getItemTarget().includes(s)) {
					sender.sendMessage(plugin.getMsg("errors.illegal-enchantment-target", enchantmentDisplayNames.get(e), s));
					return true;
				}
				String num = args.pop();
				int level = -1;
				try {
					level = Integer.parseInt(num);
				} catch (IllegalArgumentException ex) {
					sender.sendMessage(plugin.getMsg("errors.illegal-enchantment-level", num, enchantmentDisplayNames.get(e)));
					return true;
				}
				if (level > e.getMaxLevel()) {
					sender.sendMessage(plugin.getMsg("errors.illegal-enchantment-level", num, enchantmentDisplayNames.get(e)));
					return true;
				}
				if (level == 0) {
					enchantsToRemove.add(e);
				} else {
					enchantsToApply.put(e, level);
					description += ChatColor.AQUA + enchantmentDisplayNames.get(e) + " " + RN.roman(level) + ChatColor.WHITE + ", ";
				}
				
			}
			if (description.endsWith(", ")) {
				description = description.substring(0, description.length()-2) + ".";
			}
			for (Enchantment ench: enchantsToRemove) {
				s.removeEnchantment(ench);
			}
			s.addEnchantments(enchantsToApply);
			
			sender.sendMessage(plugin.getMsg("enchant-success", description));

		return true;
	}

}
