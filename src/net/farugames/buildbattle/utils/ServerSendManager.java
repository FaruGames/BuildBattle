package net.farugames.buildbattle.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.bukkit.entity.Player;

import net.farugames.buildbattle.Main;

public class ServerSendManager {

	public static void sendToServer(Player p, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(server);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		p.sendPluginMessage(Main.instance, "BungeeCord", b.toByteArray());
	}

}
