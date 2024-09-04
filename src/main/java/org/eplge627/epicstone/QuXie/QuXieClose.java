package org.eplge627.epicstone.QuXie;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import org.eplge627.epicstone.EpicStone;

public class QuXieClose implements Listener {
    public QuXieClose() {
    }

    @EventHandler
    public void onclose(InventoryCloseEvent e) {
        Player p = (Player)e.getPlayer();
        if (e.getInventory() != null && e.getInventory().getTitle().contains(EpicStone.plugin.getConfig().getString("Massages.QxGuiBt"))) {
            Inventory inv = e.getInventory();
            if (!EpicStone.itemIsNull(inv.getItem(4)) && !EpicStone.itemIsLore(inv.getItem(4))) {
                p.getInventory().addItem(new ItemStack[]{inv.getItem(4)});
            }
        }

    }
}
