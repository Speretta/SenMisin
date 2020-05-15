package me.Speretta.SenMisin.NMS;


import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import me.Speretta.SenMisin.GirisAPI.API;
import net.minecraft.server.v1_15_R1.BlockPosition;
import net.minecraft.server.v1_15_R1.Blocks;
import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import net.minecraft.server.v1_15_R1.PacketPlayInUpdateSign;
import net.minecraft.server.v1_15_R1.PacketPlayOutBlockChange;
import net.minecraft.server.v1_15_R1.PacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_15_R1.PacketPlayOutTileEntityData;
import net.minecraft.server.v1_15_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_15_R1.PacketPlayOutTitle.EnumTitleAction;

public class CB_1_15_R1 implements NMS{


	public void showSign(Player p, String[] text, SignListener listener) {
		Location loc = new Location(p.getWorld(), p.getLocation().getBlockX(), 0, p.getLocation().getBlockZ());
		BlockPosition bp = new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		PacketPlayOutBlockChange bc = new PacketPlayOutBlockChange(((CraftWorld) p.getWorld()).getHandle(), bp);
		bc.block = Blocks.OAK_SIGN.getBlockData();           
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		nbtTagCompound.setString("id", "sign");
		nbtTagCompound.setInt("x", loc.getBlockX());
		nbtTagCompound.setInt("y", loc.getBlockY());
		nbtTagCompound.setInt("z", loc.getBlockZ());
		if(text != null) {
			for(int i = 1; i < 5; i++) {
				nbtTagCompound.setString("Text"+i, "{\"text\":\""+text[i-1]+"\"}");
			}
		}
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(bc);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutTileEntityData(bp, 9, nbtTagCompound));
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutOpenSignEditor(bp));
        ChannelDuplexHandler cdh = new ChannelDuplexHandler() {
        	@Override
        	public void channelRead(ChannelHandlerContext context, Object packet) throws Exception{
        		if(packet instanceof PacketPlayInUpdateSign && API.miKayitSirasinda(p)) {
        				p.getWorld().getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()).getState().update();
        				String[] satirlar = ((PacketPlayInUpdateSign) packet).c();
        				Channel channel = ((CraftPlayer) p).getHandle().playerConnection.networkManager.channel;
        			   channel.eventLoop().submit(() -> {
        			           channel.pipeline().remove(p.getName());
        			           return null;
        			       });
        				listener.onDone(p,satirlar);
        			}else {
        			super.channelRead(context, packet);
        			}
        		}
        };
        ChannelPipeline pipeline = ((CraftPlayer)p).getHandle().playerConnection.networkManager.channel.pipeline();
        pipeline.addBefore("packet_handler", p.getName(), cdh);
         
	}
	
	public void showTitle(Player p, String title, String subtitle, int[] a) {
		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\":\"" + title + "\"}");
		IChatBaseComponent subTitle = ChatSerializer.a("{\"text\":\"" + title + "\"}");

	    PacketPlayOutTitle Title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
	    PacketPlayOutTitle SubTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subTitle);
	    PacketPlayOutTitle length = new PacketPlayOutTitle(a[0], a[1], a[2]);

	((CraftPlayer) p).getHandle().playerConnection.sendPacket(Title);
	((CraftPlayer) p).getHandle().playerConnection.sendPacket(SubTitle);
	((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);
	}

}
