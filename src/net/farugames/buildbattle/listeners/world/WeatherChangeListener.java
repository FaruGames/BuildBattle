package net.farugames.buildbattle.listeners.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {
	
	@EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (event.toWeatherState()) { //Rain is trying to turn on
            //Cancel the event if denyRain is set to true
            event.setCancelled(true);
        }
    }

}
