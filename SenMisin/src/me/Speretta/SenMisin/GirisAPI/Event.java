package me.Speretta.SenMisin.GirisAPI;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;



public class Event implements Listener{

	

	@EventHandler
	public void onJoin(PlayerLoginEvent e) {
		if (!Veri.oyuncudurum.containsKey(e.getPlayer().getUniqueId())) {
			Veri.oyuncudurum.put(e.getPlayer().getUniqueId(),Veri.get(e.getPlayer()).replace("$SHA$", ""));
			e.getPlayer().setFlying(true);
			if(!API.miKayitli(e.getPlayer())) {
				Veri.oyuncudurum.remove(e.getPlayer().getUniqueId());
				Veri.oyuncuhata.remove(e.getPlayer().getUniqueId());
				e.setKickMessage(Veri.kayitli_degil);
				e.setResult(Result.KICK_WHITELIST);
			}
		}
	}

		@EventHandler
		public void onLeave(PlayerQuitEvent e) {
			if (Veri.oyuncuhata.containsKey(e.getPlayer().getUniqueId())) {
				Veri.oyuncuhata.remove(e.getPlayer().getUniqueId());
				Veri.oyuncudurum.remove(e.getPlayer().getUniqueId());
			}
	}
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(Veri.oyuncudurum.containsKey(e.getPlayer().getUniqueId())) {
			if(e.getFrom().getX() != e.getTo().getX() || e.getFrom().getY() != e.getTo().getY() || e.getFrom().getZ() != e.getTo().getZ()) {
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(Veri.oyuncudurum.containsKey(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			
		}
	}
	@EventHandler
	public void onPickUp(EntityPickupItemEvent e) {
		if (e.getEntity() instanceof Player) {
			if(Veri.oyuncudurum.containsKey(e.getEntity().getUniqueId())) {
				e.setCancelled(true);
				
			}
		}
	}

	@EventHandler
	public void onDamagge(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			if(Veri.oyuncudurum.containsKey(e.getDamager().getUniqueId())) {
				e.setCancelled(true);
				
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			if(Veri.oyuncudurum.containsKey(e.getEntity().getUniqueId())) {
				e.setCancelled(true);
				
			}
		}
	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if(Veri.oyuncudurum.containsKey(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(Veri.oyuncudurum.containsKey(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			
		}
	}
}
