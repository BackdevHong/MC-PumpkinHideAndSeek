package pumpkinhideandseek.pumpkinhas.event;

import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pumpkinhideandseek.pumpkinhas.PumpkinHAS;

import java.io.File;
import java.util.Locale;

public class worldCheck implements Listener {
    private final File fileDir = PumpkinHAS.getPlugin(PumpkinHAS.class).getDataFolder();
    private YamlConfiguration config;
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("worldCheck")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || !e.getCurrentItem().hasItemMeta()) {
                e.setCancelled(false);
            } else {
                config = YamlConfiguration.loadConfiguration(new File(fileDir, e.getCurrentItem().getItemMeta().getDisplayName() + ".yml"));
                double getX = config.getDouble("defaultLoc.x");
                double getY = config.getDouble("defaultLoc.y");
                double getZ = config.getDouble("defaultLoc.z");
                Location loc = new Location(Bukkit.getWorld(e.getCurrentItem().getItemMeta().getDisplayName()), getX, getY, getZ);
                p.teleport(loc);
            }
        }
    }
}
