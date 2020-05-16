package me.Speretta.SenMisin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.Speretta.SenMisin.GirisAPI.API;
import me.Speretta.SenMisin.GirisAPI.Veri;
import me.Speretta.SenMisin.NMS.NMS.SignListener;
import me.Speretta.SenMisin.NMS.NMSUtil;

public class Event implements Listener {
	

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(API.miKayitSirasinda(e.getPlayer())) {
				acTabela(e.getPlayer());
				new BukkitRunnable() {
					int count = 0;
					@Override
					public void run() {
						if(!Bukkit.getOnlinePlayers().contains(e.getPlayer())) {
							cancel();
						}
						if(count > 29) {
							if(API.miKayitSirasinda(e.getPlayer())) {
								e.getPlayer().kickPlayer(Veri.gec_kaldin);
								cancel();
							}
						}
						count++;
					}
				}.runTaskTimer(Main.getInstance(), 0, 20);
			
	}
}
	
	public void acTabela(Player player) {
		NMSUtil.getNMS().showSign(player, new String[]{Main.getInstance().getConfig().getString("Giris.satir1"),Main.getInstance().getConfig().getString("Giris.satir2"),Main.getInstance().getConfig().getString("Giris.satir3"),Main.getInstance().getConfig().getString("Giris.satir4")+player.getName()}, new SignListener() {
			@Override
			public void onDone(Player p, String[] text) {
				if(API.miSifreDogruMu(p, text[0].replace("§k", ""))) {
					API.zorlaGiris(p);
					int count[] = new int[] {4};
					new BukkitRunnable() {
		                public void run() {
		                	NMSUtil.getNMS().showTitle(p, Veri.title.replace("{0}",String.valueOf((count[0]-1))), Veri.subtitle);
		                	count[0]--;
		                	if(count[0]==0) {
		                		Bukkit.dispatchCommand(p, Main.getInstance().getConfig().getString("lobi-komut"));
		                		cancel();
		                	}
		                }
					}.runTaskTimer(Main.getInstance(), 0, 20);
				}else {
				Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
		            @Override
		            public void run() {
		            	if (Veri.oyuncuhata.containsKey(p.getUniqueId())) {
		            		Veri.oyuncuhata.put(p.getUniqueId(), Veri.oyuncuhata.get(p.getUniqueId())+1);
		            		if(Veri.oyuncuhata.get(p.getUniqueId())>3) {
		            			p.kickPlayer(Veri.sifre_yanlis);
		            			return;
		            		}
		            	}else {
			            	Veri.oyuncuhata.put(p.getUniqueId(), 1);	
		            	}
		            	acTabela(p);
		            	}
		            
		        },8);
				}
		}
		});
	}

}
