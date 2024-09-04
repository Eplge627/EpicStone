package org.eplge627.epicstone.QuXie;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.eplge627.epicstone.EpicStone;

import java.util.ArrayList;
import java.util.List;

public class CheckHH {
    public CheckHH() {
    }

    public static void HHItemsSet(ItemStack item, Inventory inv) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        List<String> HH_key = new ArrayList();

        int i;
        String key;
        for(i = 0; i < lore.size(); ++i) {
            key = (String)lore.get(i);
            if (EpicStone.getQxHH_Key.get(key) != null) {
                HH_key.add(EpicStone.getQxHH_Key.get(key));
            }
        }

        for(i = 9; i < HH_key.size() + 9; ++i) {
            if (i < 36) {
                key = (String)HH_key.get(i - 9);
                double money = EpicStone.QuxieG.getDouble("QxSettings." + key + ".money");
                ItemStack itemStack = EpicStone.QuxieG.getItemStack("QxSettings." + key + ".displayItem");
                ItemStack items = InfoItems(itemStack, money);
                inv.setItem(i, items);
            }
        }

    }

    public static ItemStack InfoItems(ItemStack items, double money) {
        ItemStack item = items.clone();
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList(items.getItemMeta().getLore());
        lore.add("§7-------------------------");
        lore.add(EpicStone.plugin.getConfig().getString("Massages.QXGuiItemLore"));
        lore.add("§a§l★§7所需费用: §6" + money + "金币");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}