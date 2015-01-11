package io.github.nickstern.adminchatplus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminChatForce implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("cforce")) {
			if(sender.hasPermission("AdminChat.channel.force")) {
				if(args.length != 2) {
					sender.sendMessage(ChatColor.DARK_RED + "Insufficient Parameters! Usage: /cforce [player] [channel]");
					return false;
				} else {
					Player target = (Bukkit.getServer().getPlayer(args[0]));
					if(target == null){
						sender.sendMessage(ChatColor.DARK_RED + "Error! Player " + ChatColor.DARK_RED + args[0] + ChatColor.DARK_RED + " if not a valid, online player!");
					} else {
						String targetn = target.getName();
						if(args[1].equalsIgnoreCase(AdminChatMain.chatting.get(targetn))) {
							sender.sendMessage(ChatColor.DARK_RED + "This user is already in that channel!");
							return false;
						} else if(args[1].equalsIgnoreCase("srmod")) {
							AdminChatMain.chatting.put(targetn, "SrMod");
							sender.sendMessage(ChatColor.GREEN + "Moved " + targetn + " to Sr. Mod channel!");
							target.sendMessage(ChatColor.GREEN + "You were moved to the Sr. Mod channel");
							return true;
						} else if(args[1].equalsIgnoreCase("mod")) {
							AdminChatMain.chatting.put(targetn, "Mod");
							sender.sendMessage(ChatColor.GREEN + "Moved " + targetn + " to Mod channel!");
							target.sendMessage(ChatColor.GREEN + "You were moved to the Mod channel");
							return true;
						} else if(args[1].equalsIgnoreCase("helper")) {
							AdminChatMain.chatting.put(targetn, "Helper");
							sender.sendMessage(ChatColor.GREEN + "Moved " + targetn + " to Helper channel!");
							target.sendMessage(ChatColor.GREEN + "You were moved to the Helper channel");
							return true;
						} else if(args[1].equalsIgnoreCase("reporter")) {
							AdminChatMain.chatting.put(targetn, "Reporter");
							sender.sendMessage(ChatColor.GREEN + "Moved " + targetn + " to Reporter channel!");
							target.sendMessage(ChatColor.GREEN + "You were moved to the Reporter channel");
							return true;
						} else if(args[1].equalsIgnoreCase("admin")) {
							AdminChatMain.chatting.put(targetn, "Admin");
							sender.sendMessage(ChatColor.GREEN + "Moved " + targetn + " to Admin channel!");
							target.sendMessage(ChatColor.GREEN + "You were moved to the Admin channel");
							return true;
						} else {
							sender.sendMessage(ChatColor.DARK_RED + args[1] + " is not a valid chat channel!");
						}
					}
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "You do not have permission.");
			}
		}
		return false;
	}

}
