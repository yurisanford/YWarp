package me.br.warp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class RemoveWarpCommand implements CommandExecutor {
	
	private Warps warps;
	
	public RemoveWarpCommand(Warps warps) {
		this.warps = warps;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player)sender;
			if (!p.hasPermission("sc.removewarp")) {
				p.sendMessage("§4[!] Você não tem permissão !");
			} else {
				FileConfiguration config;
				if (args.length >= 1) {
					String name = args[0].toLowerCase();
					 config = warps.getConfig();
					if (config.contains(name)) {
						config.set(name, null);
						warps.saveConfig();
						p.sendMessage("§a[!] O Warp: §7" + name + " §aFoi removido com sucesso !");
						
					} else {
						p.sendMessage("§4[!] Não foi encontrado nenhum warp chamado: §f" + name);
					}
				} else {
					p.sendMessage("§a[!] Use: /delwarp [Nome]");
				}
			}
		}
		return false;
	}

}
