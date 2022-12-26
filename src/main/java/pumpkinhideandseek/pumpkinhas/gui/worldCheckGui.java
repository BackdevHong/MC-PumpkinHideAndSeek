package pumpkinhideandseek.pumpkinhas.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import pumpkinhideandseek.pumpkinhas.PumpkinHAS;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class worldCheckGui {
    private final File fileDir = PumpkinHAS.getPlugin(PumpkinHAS.class).getDataFolder();
    private final String[] fileList = fileDir.list();
    public void itemSet(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv) {
        ItemStack item = new MaterialData(ID, (byte) data).toItemStack(stack);
        ItemMeta items = item.getItemMeta();
        items.setDisplayName(display);
        items.setLore(Lore);
        item.setItemMeta(items);
        inv.setItem(loc, item);
    }
    public void open(Player p) {
        int idx = 0;
        Inventory inv = Bukkit.createInventory(null, 9, "worldCheck");
        for(String filename : fileList) {
            int fileIndex = filename.lastIndexOf(".");
            String newFilename = filename.substring(0, fileIndex);
            itemSet(newFilename, Material.BOOK, 0, 1, Arrays.asList("좌클릭하시면 해당 월드로 이동합니다!"), idx, inv);
            idx++;
        }
        p.openInventory(inv);
    }
}
