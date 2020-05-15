package me.Speretta.SenMisin.GirisAPI;



import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.Speretta.SenMisin.Main;

public class API {
	Main plugin;
	public API() {
		plugin = Main.getInstance();
		Bukkit.getPluginManager().registerEvents(new Event(), Main.getInstance());
		
	}
	
	
	public static boolean miKayitli(Player p) {
		if(!Veri.get(p).equalsIgnoreCase("Hata!")) {
			return true;
		}
		return false;
	}
	
	public static boolean miKayitSirasinda(Player p) {
		if(Veri.oyuncudurum.containsKey(p.getUniqueId())) {
			return true;
		}
		return false;
	}
	
	public static boolean miSifreDogruMu(Player p, String s) {
		if (Veri.oyuncudurum.containsKey(p.getUniqueId())) {
			if(!Veri.oyuncudurum.get(p.getUniqueId()).equalsIgnoreCase("Hata!")) {
				String hamhal = Veri.oyuncudurum.get(p.getUniqueId());
				String salt = hamhal.substring(0, 16);
				Bukkit.broadcastMessage("Tabela hal:" + salt+"$"+Sifrele.sifrele(Sifrele.sifrele(s)+salt));
				Bukkit.broadcastMessage("Ham hal: "+hamhal);
				if(hamhal.equalsIgnoreCase(salt+"$"+Sifrele.sifrele(Sifrele.sifrele(s)+salt))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void zorlaGiris(Player p) {
		if(Veri.oyuncudurum.containsKey(p.getUniqueId())) {
			Veri.oyuncudurum.remove(p.getUniqueId());
			Veri.oyuncuhata.remove(p.getUniqueId());
		}
	}

}
