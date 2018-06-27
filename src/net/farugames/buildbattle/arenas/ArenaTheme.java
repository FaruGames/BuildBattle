package net.farugames.buildbattle.arenas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArenaTheme {
	
	private List<String> themes = new ArrayList<String>();
	public static String theme;
	
	public ArenaTheme() {
		themes.add("Gâteau");
		themes.add("Arbre");
		themes.add("Hamburger");
		themes.add("Disney");
		themes.add("Snowman");
		themes.add("Banane");
		themes.add("Escargot");
		themes.add("Football");
		themes.add("Ville");
		themes.add("Plage");
		themes.add("Galaxie");
		themes.add("Dragon");
		themes.add("Laboratoire");
		themes.add("Serpent");
		
		/* RANDOM */
		Random random = new Random();
		if(theme != null) return;
		theme = themes.get(random.nextInt(themes.size()));
	}
	
	public static String getTheme() {
		return theme;
	}
}
