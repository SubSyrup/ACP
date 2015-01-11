package io.github.nickstern.adminchatplus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminChatBan implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("Cban")) {
			if(sender.hasPermission("AdminChat.channel.ban")) {
				Player player = (Player) sender;
				if(args.length == 2) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target == null) {
						sender.sendMessage(ChatColor.DARK_RED + args[0] + " is not online.");
						return false;
					} else {
						String channel = args[1];
						if(channel.equalsIgnoreCase("Admin")) {
							String tkey = target.getName() + "a"; 
							if(AdminChatMain.cbanned.containsKey(tkey)) {
								if(AdminChatMain.cbanned.get(tkey) == true) {
									AdminChatMain.cbanned.put(tkey, false);
									sender.sendMessage(ChatColor.GREEN + "Player unbanned from Admin.");
									return true;
								} else {
									AdminChatMain.cbanned.put(tkey, true);
									sender.sendMessage(ChatColor.GREEN + "Player banned from Admin.");
									return true;
								}
							} else {
								AdminChatMain.cbanned.put(tkey, true);
								sender.sendMessage(ChatColor.GREEN + "Player banned from Admin.");
								return true;
							}
						} else if(channel.equalsIgnoreCase("SrMod")) {
							String tkey = target.getName() + "sm";
							if(AdminChatMain.cbanned.containsKey(tkey)) {
								if(AdminChatMain.cbanned.get(tkey) == true) {
									AdminChatMain.cbanned.put(tkey, false);
									sender.sendMessage(ChatColor.GREEN + "Player unbanned from SrMod.");
									return true;
								} else {
									AdminChatMain.cbanned.put(tkey, true);
									sender.sendMessage(ChatColor.GREEN + "Player banned from SrMod.");
									return true;
								}
							} else {
								AdminChatMain.cbanned.put(tkey, true);
								sender.sendMessage(ChatColor.GREEN + "Player banned from SrMod.");
								return true;
							}
						} else if(channel.equalsIgnoreCase("Mod")) {
							String tkey = target.getName() + "m";
							if(AdminChatMain.cbanned.containsKey(tkey)) {
								if(AdminChatMain.cbanned.get(tkey) == true) {
									AdminChatMain.cbanned.put(tkey, false);
									sender.sendMessage(ChatColor.GREEN + "Player unbanned from Mod.");
									return true;
								} else {
									AdminChatMain.cbanned.put(tkey, true);
									sender.sendMessage(ChatColor.GREEN + "Player banned from Mod.");
									return true;
								}
							} else {
								AdminChatMain.cbanned.put(tkey, true);
								sender.sendMessage(ChatColor.GREEN + "Player banned from Mod.");
								return true;
							}
						} else if(channel.equalsIgnoreCase("Helper")) {
							String tkey = target.getName() + "h";
							if(AdminChatMain.cbanned.containsKey(tkey)) {
								if(AdminChatMain.cbanned.get(tkey) == true) {
									AdminChatMain.cbanned.put(tkey, false);
									sender.sendMessage(ChatColor.GREEN + "Player unbanned from Helper.");
									return true;
								} else {
									AdminChatMain.cbanned.put(tkey, true);
									sender.sendMessage(ChatColor.GREEN + "Player banned from Helper.");
									return true;
								}
							} else {
								AdminChatMain.cbanned.put(tkey, true);
								sender.sendMessage(ChatColor.GREEN + "Player banned from Helper.");
								return true;
							}
						} else if(channel.equalsIgnoreCase("Reporter")) {
							String tkey = target.getName() + "r";
							if(AdminChatMain.cbanned.containsKey(tkey)) {
								if(AdminChatMain.cbanned.get(tkey) == true) {
									AdminChatMain.cbanned.put(tkey, false);
									sender.sendMessage(ChatColor.GREEN + "Player unbanned from Reporter.");
									return true;
								} else {
									AdminChatMain.cbanned.put(tkey, true);
									sender.sendMessage(ChatColor.GREEN + "Player banned from Reporter.");
									return true;
								}
							} else {
								AdminChatMain.cbanned.put(tkey, true);
								sender.sendMessage(ChatColor.GREEN + "Player banned from Reporter.");
								return true;
							}
						} else if(channel.equalsIgnoreCase("@a")) {
							String tkey = target.getName() + "@";
							if(AdminChatMain.cbanned.containsKey(tkey)) {
								if(AdminChatMain.cbanned.get(tkey) == true) {
									AdminChatMain.cbanned.put(tkey, false);
									sender.sendMessage(ChatColor.GREEN + "Player unbanned from all Channels.");
									return true;
								} else {
									AdminChatMain.cbanned.put(tkey, true);
									sender.sendMessage(ChatColor.GREEN + "Player banned from all Channels.");
									return true;
								}
							} else {
								AdminChatMain.cbanned.put(tkey, true);
								sender.sendMessage(ChatColor.GREEN + "Player banned from all Channels.");
								return true;
							}
						}
					}
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "You do not have permission.");
				return false;
			}
		}
		return false;
	}

}
