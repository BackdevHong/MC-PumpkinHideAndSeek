package pumpkinhideandseek.pumpkinhas.event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pumpkinhideandseek.pumpkinhas.PumpkinHAS;

import java.io.File;

public class WorldRemove implements Listener {
    private final File fileDir = PumpkinHAS.getPlugin(PumpkinHAS.class).getDataFolder();
    private final String message = format("&e&l[ &c&lPumpkin &e&l]&f&l");
    private YamlConfiguration config;
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("worldRemove")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || !e.getCurrentItem().hasItemMeta()) {
                e.setCancelled(false);
            } else {
                File file = new File(fileDir, e.getCurrentItem().getItemMeta().getDisplayName() + ".yml");
                String fileName = file.getName();
                int fileIndex = fileName.lastIndexOf(".");
                String newFilename = fileName.substring(0, fileIndex);
                p.sendMessage(message + " " + newFilename + " 월드가 삭제되었습니다.");
                file.delete();
                p.closeInventory();
            }
        }
    }
    public String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
