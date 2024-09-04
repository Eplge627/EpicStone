package org.eplge627.epicstone.QuXie;

import org.eplge627.epicstone.EpicStone;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class QuXieGui implements Listener {
    public QuXieGui() {
    }

    public static void OpenGui(Player p) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 36, EpicStone.plugin.getConfig().getString("Messages.QxGuiBt"));

        int i;
        for(i = 9; i < 36; ++i) {
            inv.setItem(i, glass(0, EpicStone.plugin.getConfig().getString("Messages.XrGuiItemLore1")));
        }

        for(i = 0; i < 9; ++i) {
            inv.setItem(i, glass(15, "§7[§a■□■§7]"));
        }

        inv.setItem(3, glass(11, "§7[§a装备槽§7]"));
        inv.setItem(4, glass(1, "§7« §a请放入物品 §7»"));
        inv.setItem(5, glass(11, "§7[§a装备槽§7]"));
        p.openInventory(inv);
    }

    public static ItemStack glass(int data, String name) {
        ItemStack item = new ItemStack(160, 1, (short)data);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}
