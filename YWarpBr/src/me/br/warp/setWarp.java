package me.br.warp;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.connorlinfoot.titleapi.TitleAPI;

public class setWarp implements CommandExecutor {
	
	private Warps warps;
	
	public setWarp (Warps warps) {
		this.warps = warps;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (sender instanceof Player) {
			if (args.length >= 1) {
				  Player p = (Player)sender;
			        Location loc = p.getLocation();
			        String nome = args[0];
			        FileConfiguration config = warps.getConfig();
				if (!p.hasPermission("sc.setwarp")) {
					p.sendMessage("§4[!] Você não possui permissão");
				} else {
					if (config.contains(nome)) {
						p.sendMessage("§4[!] Já existe um Warp com esse nome !");
						return true;
					} 
			        ConfigurationSection warp = config.createSection(nome);
			        warp.set("world", loc.getWorld().getName());
			        warp.set("x", Double.valueOf(loc.getX()));
			        warp.set("y", Double.valueOf(loc.getY()));
			        warp.set("z", Double.valueOf(loc.getZ()));
			        warp.set("yaw", Float.valueOf(loc.getYaw()));
			        warp.set("pitch", Float.valueOf(loc.getPitch()));
			        
			        warps.saveConfig();
			        TitleAPI.sendTitle(p, 10, 5 * 20, 10, "§6§l" + nome, "§fDefinido! X: §3" + loc.getX() + " §fY: §3" + loc.getBlockY() + " §fZ: §3" + loc.getZ());
			}
				
			  } else {
				sender.sendMessage("§a[!] Use: /setwarp [Nome]");
			}
			
		}
		return false;
	}
	

}
