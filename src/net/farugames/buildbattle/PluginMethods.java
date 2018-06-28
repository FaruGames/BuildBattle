package net.farugames.buildbattle;

public class PluginMethods {
	private static String PREFIX = "§a§lBuildBattle§r";
	private static String CHAT_PREFIX = PREFIX + " §f❙ ";
	private static String RIGHTCLICK = "§7 (Clique droit)";

	public static String getPrefix() {
		return PREFIX;
	}

	public static String getChatPrefix() {
		return CHAT_PREFIX;
	}

	public static String getRightClick() {
		return RIGHTCLICK;
	}
}
