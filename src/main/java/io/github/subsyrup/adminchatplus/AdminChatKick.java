package io.github.nickstern.adminchatplus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminChatKick implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("ckick")) {
			if(sender.hasPermission("AdminChat.channel.kick")) {
				if(args.length != 1) {
					sender.sendMessage(ChatColor.DARK_RED + "Insufficient Paramaters. Usage: /ckick [player]");
					return false;
				} else {
					Player target = (Bukkit.getServer().getPlayer(args[0]));
					if(target == null) {
						if(args[0].equalsIgnoreCase("@a")) {
							sender.sendMessage(ChatColor.GREEN + "Kicking all players to default chat!");
							for(Player p : Bukkit.getServer().getOnlinePlayers()) {
								if(AdminChatMain.chatting.get(p.getName()) != "Default") {
									if(p.getName() == sender.getName()) {
										AdminChatMain.chatting.put(p.getName(), AdminChatMain.chatting.get(p.getName()));
									} else if(p.hasPermission("AdminChat.kick.exempt")) {
										AdminChatMain.chatting.put(p.getName(), AdminChatMain.chatting.get(p.getName()));
									} else {
										p.sendMessage(ChatColor.DARK_RED + "You were kicked back to default chat!");
										AdminChatMain.chatting.put(p.getName(), "Default");
									}
								}
							}
							return true;
						}
						sender.sendMessage(ChatColor.DARK_RED + args[0] + ChatColor.DARK_RED + " is not a valid, online player!");
						return false;
					} else {
						String targetn = target.getName();
						if(!AdminChatMain.chatting.containsKey(targetn)) {
							sender.sendMessage(ChatColor.DARK_RED + "This player is not in a channel!");
							return false;
						} else if(AdminChatMain.chatting.get(targetn) == "Default") {
							sender.sendMessage(ChatColor.DARK_RED + "This player is not in a channel!");
							return false;
						} else {
							AdminChatMain.chatting.put(targetn, "Default");
							sender.sendMessage(ChatColor.GREEN + "User " + ChatColor.GREEN + targetn + ChatColor.GREEN + " has been kicked to regular chat!");
							target.sendMessage(ChatColor.DARK_RED + "You were kicked from the channel.");
							return true;
						}
					}
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
				return false;
			}
		}
		return false;
	}

}
