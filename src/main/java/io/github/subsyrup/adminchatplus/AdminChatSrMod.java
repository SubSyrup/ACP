package io.github.nickstern.adminchatplus;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminChatSrMod implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("SrMod")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Silly console, you're not a player!");
				return false;
			} else {
				Player player = (Player) sender;
				String pName = player.getName();
				if (player.hasPermission("AdminChat.channel.seniormod")) {
					if (!AdminChatMain.chatting.containsKey(pName)) {
						AdminChatMain.chatting.put(pName, "SrMod");
						player.sendMessage(ChatColor.GREEN + "Joined Sr. Mod Channel!");
						return true;
					} else if (AdminChatMain.chatting.get(pName) == "SrMod") {
                        AdminChatMain.chatting.put(pName, "Default");
                        player.sendMessage(ChatColor.RED + "Left Sr. Mod Channel!");
                        return true;
                    } else {
                        AdminChatMain.chatting.put(pName, "SrMod");
                        player.sendMessage(ChatColor.GREEN + "Joined Sr. Mod Channel!");
                        return true;
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
