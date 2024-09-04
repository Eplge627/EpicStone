package org.eplge627.epicstone.Xiru;

import org.eplge627.epicstone.EpicStone;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class XiruGuiClose implements Listener {
    public XiruGuiClose() {
    }

    @EventHandler
    public void onclose(InventoryCloseEvent e) {
        Player p = (Player)e.getPlayer();
        if (e.getInventory() != null && EpicStone.plugin.getConfig().getString("Messages.XrGuiBt").equalsIgnoreCase(e.getInventory().getTitle())) {
            Inventory inv = e.getInventory();
            if (!EpicStone.itemIsNull(inv.getItem(11)) && !EpicStone.itemIsLore(inv.getItem(11))) {
                p.getInventory().addItem(new ItemStack[]{inv.getItem(11)});
            }

            if (!EpicStone.itemIsNull(inv.getItem(15)) && !EpicStone.itemIsLore(inv.getItem(15))) {
                p.getInventory().addItem(new ItemStack[]{inv.getItem(15)});
            }

            if (!EpicStone.itemIsNull(inv.getItem(13)) && !EpicStone.itemIsLore(inv.getItem(13))) {
                p.getInventory().addItem(new ItemStack[]{inv.getItem(13)});
            }
        }

    }
}