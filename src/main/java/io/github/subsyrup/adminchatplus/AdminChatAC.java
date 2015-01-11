package io.github.nickstern.adminchatplus;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminChatAC implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("AdminChat")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Silly console, you're not a player!");
				return false;
			} else {
				Player player = (Player) sender;
				sender.sendMessage(ChatColor.GOLD + "Available channels:");
				if(player.hasPermission("AdminChat.channel.admin")) {
					sender.sendMessage(ChatColor.DARK_PURPLE + "Admin (/a)");
				}
				if(player.hasPermission("AdminChat.channel.seniormod")) {
					sender.sendMessage(ChatColor.GREEN + "Senior Mod (/srmod)");
				}
				if(player.hasPermission("AdminChat.channel.moderator")) {
					sender.sendMessage(ChatColor.YELLOW + "Moderator (/mod)");
				}
				if(player.hasPermission("AdminChat.channel.helper")) {
					sender.sendMessage(ChatColor.DARK_GREEN + "Helper (/helper)");
				}
				if(player.hasPermission("AdminChat.channel.reporter")) {
					sender.sendMessage(ChatColor.AQUA + "Reporter (/reporter)");
				}
				return true;
			}
		}
		return false;
	}
}
