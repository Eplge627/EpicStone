package org.eplge627.epicstone.QuXie;

import org.eplge627.epicstone.EpicStone;
import org.eplge627.epicstone.QuXie.CheckHH;
import org.eplge627.epicstone.QuXie.QuXieGui;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuXieEvent implements Listener {
    public QuXieEvent() {
    }

    @EventHandler
    public void onclick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory inv = e.getInventory();
        if (e.getInventory() != null && inv.getTitle().contains(EpicStone.plugin.getConfig().getString("Massages.QxGuiBt"))) {
            if (e.getRawSlot() >= 0 && e.getRawSlot() < 9) {
                e.setCancelled(true);
                if (e.getRawSlot() == 4) {
                    if (e.getCurrentItem().getTypeId() == 160) {
                        if (!EpicStone.itemIsNull(e.getCursor())) {
                            if (!EpicStone.itemIsLore(e.getCursor())) {
                                e.getInventory().setItem(4, e.getCursor());
                                e.setCursor(new ItemStack(Material.AIR));
                                CheckHH.HHItemsSet(e.getInventory().getItem(4), e.getInventory());
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.6F, 1.8F);
                            } else {
                                p.sendMessage(EpicStone.plugin.getConfig().getString("Massages.QCNotHaveBS"));
                            }
                        }
                    } else {
                        if (!EpicStone.itemIsNull(e.getCursor())) {
                            p.sendMessage(EpicStone.prefix + "请先将装备取出再尝试放上去.");
                            return;
                        }

                        for(int i = 9; i < 36; ++i) {
                            inv.setItem(i, QuXieGui.glass(0, EpicStone.plugin.getConfig().getString("Massages.XrGuiItemLore1")));
                        }

                        e.setCursor(e.getCurrentItem());
                        e.getInventory().setItem(4, QuXieGui.glass(1, "§7« §a请放入装备 §7»"));
                        p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 0.6F, 1.8F);
                    }
                }
            }

            if (e.getRawSlot() >= 9 && e.getRawSlot() < 36) {
                e.setCancelled(true);
                ItemStack item = e.getCurrentItem();
                if (!EpicStone.itemIsNull(item) && !EpicStone.itemIsLore(item)) {
                    int money = 0;
                    List<String> SXLoreSize = new ArrayList();
                    String XrKong = null;
                    String XrKongLore = null;
                    boolean butt = false;
                    String HHkey = null;
                    FileConfiguration config = EpicStone.QuxieG;
                    Iterator var9 = config.getConfigurationSection("QxSettings").getKeys(false).iterator();

                    ItemStack hhItems;
                    while(var9.hasNext()) {
                        String key = (String)var9.next();
                        hhItems = config.getItemStack("QxSettings." + key + ".displayItem");
                        if (hhItems.getItemMeta().getDisplayName().equalsIgnoreCase(item.getItemMeta().getDisplayName()) && hhItems.getTypeId() == item.getTypeId()) {
                            HHkey = key;
                            money = config.getInt("QxSettings." + key + ".money");
                            SXLoreSize = config.getStringList("QxSettings." + key + ".SXLore");
                            XrKong = config.getString("QxSettings." + key + ".Xrkong");
                            XrKongLore = EpicStone.plugin.getConfig().getString("EmptyLore." + XrKong + ".KongLore");
                            butt = true;
                            break;
                        }
                    }

                    if (butt) {
                        RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
                        if (economyProvider != null) {
                            EpicStone.economy = (Economy)economyProvider.getProvider();
                        }

                        if (EpicStone.economy.getBalance(p) >= (double)money) {
                            EpicStone.economy.withdrawPlayer(p, (double)money);
                            hhItems = config.getItemStack("QxSettings." + HHkey + ".displayItem");
                            String keyLore = config.getString("QxSettings." + HHkey + ".keyLore");
                            this.RemoveHHLore(e.getInventory(), keyLore, XrKongLore, (List)SXLoreSize);
                            p.getInventory().addItem(new ItemStack[]{hhItems});
                            e.setCurrentItem(QuXieGui.glass(0, EpicStone.plugin.getConfig().getString("Massages.XrGuiItemLore1")));
                            p.sendMessage(EpicStone.plugin.getConfig().getString("Massages.QXSuc"));
                            p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0F, 2.0F);
                        } else {
                            p.sendMessage(EpicStone.prefix + "你没有足够的金币无法取出.");
                        }
                    } else {
                        p.sendMessage(EpicStone.plugin.getConfig().getString("Massages.QXWrong"));
                    }
                }
            }
        }

    }

    public void RemoveHHLore(Inventory inv, String keyLore, String XrKongLore, List<String> SXLoreSize) {
        ItemStack item = inv.getItem(4).clone();
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();

        label23:
        for(int i = 0; i < lore.size(); ++i) {
            String s = (String)lore.get(i);
            if (s.startsWith(keyLore)) {
                lore.set(i, XrKongLore.replace("&", "§"));
                int a = 0;

                while(true) {
                    if (a >= SXLoreSize.size()) {
                        break label23;
                    }

                    lore.remove(i + 1);
                    ++a;
                }
            }
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(4, item);
    }
}
