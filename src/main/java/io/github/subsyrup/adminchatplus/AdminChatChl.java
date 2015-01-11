package io.github.nickstern.adminchatplus;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminChatChl implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("chl")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Silly console, you're not a player!");
				return false;
			} else {
				Player player = (Player) sender;
				String pName = player.getName();
				if(player.hasPermission("AdminChat.channel.chl")) {
					if(AdminChatMain.chatting.get(pName) != "Default") {
						AdminChatMain.chatting.put(pName, "Default");
						sender.sendMessage(ChatColor.RED + "Returning to regular chat!");
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "You're not currently in a channel!");
						return false;
					}
				} else {
					sender.sendMessage("You do not have permission");
					return false;
				}
			}
		}
		return false;
	}
}
