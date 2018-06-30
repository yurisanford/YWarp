package me.br.warp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.connorlinfoot.titleapi.TitleAPI;

public class WarpCommand implements CommandExecutor{
	
	private Warps warps;
	
	public WarpCommand(Warps warps) {
		this.warps = warps;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player)sender;
			if (!p.hasPermission("sc.warp")) {
				p.sendMessage("�4[!] Voc� n�o possui permiss�o !");
			} else {
				if (args.length >= 1) {
					String nome = args[0].toLowerCase();
					FileConfiguration config = warps.getConfig();
					if (config.contains(nome)) {
						ConfigurationSection warp = config.getConfigurationSection(nome);
						if (Bukkit.getWorld(warp.getString("world")) == null) {
							p.sendMessage("�4[!] N�o encontramos Warps nesse Mundo !");
							return true;
						}
						World w = Bukkit.getWorld(warp.getString("world"));
						double x = warp.getDouble("x");
						double y = warp.getDouble("y");
						double z = warp.getDouble("z");
						float yaw = (float) warp.getDouble("yaw");
						float pitch = (float) warp.getDouble("pitch");
						Location loc = new Location(w, x, y, z, yaw, pitch);
						p.teleport(loc);
						p.sendMessage("�a[!] Voc� foi teleportado para: " + nome);
						TitleAPI.sendTitle(p, 10, 5 * 20, 10, "�6�l" + nome , "�aTeleportado para " + nome);
					} else {
						p.sendMessage("�4[!] Warp n�o encontrado !");
					}
				} else {
					StringBuilder sb = new StringBuilder();
					for(String s : warps.getConfig().getKeys(false)) {
						sb.append(s + ",");
					}
					sender.sendMessage(sb.toString());
					sender.sendMessage("�a[!] Use: /warp [Nome]");
				}
			}
		}
		return false;
	}
}
