package org.eplge627.epicstone.Xiru;

import org.eplge627.epicstone.EpicStone;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class XiruGui {
    public XiruGui() {
    }

    public static void OpenGui(Player p) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 36, EpicStone.plugin.getConfig().getString("Messages.XrGuiBt"));

        for(int i = 0; i < 36; ++i) {
            inv.setItem(i, glass(15, "§7"));
        }

        SetGlassZb(inv);
        SetGlassHH(inv);
        inv.setItem(11, glass(0, "§7« §a请放入装备 §7»"));
        inv.setItem(15, glass(0, EpicStone.plugin.getConfig().getString("Messages.XrGuiItemLore")));
        inv.setItem(13, glass(2, "§7« §d请放入保护符 §7»"));
        inv.setItem(31, yesbutt());
        inv.setItem(4, Bszhinan());
        p.openInventory(inv);
    }

    public static void SetGlassZb(Inventory inv) {
        inv.setItem(1, glass(11, "§7[§a装备槽§7]"));
        inv.setItem(2, glass(4, "§7[§a装备槽§7]"));
        inv.setItem(3, glass(11, "§7[§a装备槽§7]"));
        inv.setItem(10, glass(4, "§7[§a装备槽§7]"));
        inv.setItem(12, glass(4, "§7[§a装备槽§7]"));
        inv.setItem(19, glass(11, "§7[§a装备槽§7]"));
        inv.setItem(20, glass(4, "§7[§a装备槽§7]"));
        inv.setItem(21, glass(11, "§7[§a装备槽§7]"));
    }

    public static void SetGlassHH(Inventory inv) {
        inv.setItem(5, glass(14, EpicStone.plugin.getConfig().getString("Messages.XrGuiItemLore1")));
        inv.setItem(6, glass(4, EpicStone.plugin.getConfig().getString("Messages.XrGuiItemLore1")));
        inv.setItem(7, glass(14, EpicStone.plugin.getConfig().getString("Messages.XrGuiItemLore1")));
        inv.setItem(14, glass(4, EpicStone.plugin.getConfig().getString("Messages.XrGuiItemLore1")));
        inv.setItem(16, glass(4, EpicStone.plugin.getConfig().getString("Messages.XrGuiItemLore1")));
        inv.setItem(23, glass(14, EpicStone.plugin.getConfig().getString("Messages.XrGuiItemLore1")));
        inv.setItem(24, glass(4, EpicStone.plugin.getConfig().getString("Messages.XrGuiItemLore1")));
        inv.setItem(25, glass(14, EpicStone.plugin.getConfig().getString("Messages.XrGuiItemLore1")));
    }

    public static ItemStack glass(int data, String name) {
        ItemStack item = new ItemStack(160, 1, (short)data);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack yesbutt() {
        ItemStack item = new ItemStack(145);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6§l确认镶嵌");
        List<String> lore = new ArrayList();
        lore.add("§a§l★§7点击镶嵌");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack Bszhinan() {
        ItemStack item = new ItemStack(340);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6§l宝石指南");
        List<String> lore = new ArrayList();
        lore.add("§a§l★§7左边放装备-右边放宝石§a§l★");
        lore.add("§b§l★§7本子下方可选择性放保护符§b§l★");
        lore.add("§6§l★§7如有保护符可保护宝石不碎§6§l★");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
