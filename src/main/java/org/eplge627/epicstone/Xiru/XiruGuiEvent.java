package org.eplge627.epicstone.Xiru;

import org.eplge627.epicstone.EpicStone;
import org.eplge627.epicstone.Xiru.XiruGui;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XiruGuiEvent implements Listener {
    public XiruGuiEvent() {
    }

    @EventHandler
    public void onclick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        boolean item = false;
        if (e.getInventory() != null && EpicStone.plugin.getConfig().getString("Messages.XrGuiBt").equalsIgnoreCase(e.getInventory().getTitle()) && e.getRawSlot() >= 0 && e.getRawSlot() <= 35) {
            if (e.getRawSlot() != 11) {
                if (e.getRawSlot() == 15) {
                    if (e.getCurrentItem().getTypeId() == 160) {
                        if (!EpicStone.itemIsNull(e.getCursor())) {
                            if (!EpicStone.itemIsLore(e.getCursor()) && EpicStone.HH_Name.contains(e.getCursor().getItemMeta().getDisplayName())) {
                                if (e.getCursor().getAmount() == 1) {
                                    e.getInventory().setItem(15, e.getCursor());
                                    e.setCursor(new ItemStack(Material.AIR));
                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.6F, 1.8F);
                                } else {
                                    p.sendMessage(EpicStone.plugin.getConfig().getString("Messages.PutManyBS"));
                                }
                            } else {
                                p.sendMessage(EpicStone.plugin.getConfig().getString("Messages.PutNotBS"));
                            }
                        }
                    } else {
                        p.getInventory().addItem(new ItemStack[]{e.getCurrentItem()});
                        e.getInventory().setItem(15, org.eplge627.epicstone.Xiru.XiruGui.glass(0, EpicStone.plugin.getConfig().getString("Messages.XrGuiItemLore1")));
                        p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 0.6F, 1.8F);
                    }
                } else if (e.getRawSlot() == 13) {
                    if (e.getCurrentItem().getTypeId() == 160) {
                        if (!EpicStone.itemIsNull(e.getCursor())) {
                            if (!EpicStone.itemIsLore(e.getCursor()) && e.getCursor().getItemMeta().getDisplayName().contains(EpicStone.plugin.getConfig().getString("BHFName")) && e.getCursor().getItemMeta().getLore().contains(EpicStone.plugin.getConfig().getString("BHFLore"))) {
                                if (e.getCursor().getAmount() == 1) {
                                    e.getInventory().setItem(13, e.getCursor());
                                    e.setCursor(new ItemStack(Material.AIR));
                                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.6F, 1.8F);
                                } else {
                                    p.sendMessage(EpicStone.plugin.getConfig().getString("Messages.PutManyBHF"));
                                }
                            } else {
                                p.sendMessage(EpicStone.plugin.getConfig().getString("Messages.PutNotBHF"));
                            }
                        }
                    } else {
                        p.getInventory().addItem(new ItemStack[]{e.getCurrentItem()});
                        e.getInventory().setItem(13, org.eplge627.epicstone.Xiru.XiruGui.glass(0, "§7« §d请放入保护符 §7»"));
                        p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 0.6F, 1.8F);
                    }
                } else if (e.getRawSlot() == 31 && e.getClick() == ClickType.LEFT) {
                    p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0F, 2.0F);
                    Inventory inv = e.getInventory();
                    if (!EpicStone.itemIsLore(inv.getItem(11)) && !EpicStone.itemIsLore(inv.getItem(15))) {
                        AddHHItems(p, inv, inv.getItem(11), inv.getItem(15), inv.getItem(13));
                    } else {
                        p.sendMessage(EpicStone.plugin.getConfig().getString("Messages.NotItems"));
                    }
                }
            } else if (e.getCurrentItem().getTypeId() != 160) {
                p.getInventory().addItem(new ItemStack[]{e.getCurrentItem()});
                e.getInventory().setItem(11, org.eplge627.epicstone.Xiru.XiruGui.glass(0, "§7« §a请放入装备 §7»"));
                p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 0.6F, 1.8F);
            } else if (!EpicStone.itemIsNull(e.getCursor()) && !EpicStone.itemIsLore(e.getCursor())) {
                for(int x = 0; x < e.getCursor().getItemMeta().getLore().size(); ++x) {
                    if (EpicStone.Kong_Lore.contains(e.getCursor().getItemMeta().getLore().get(x))) {
                        item = true;
                        break;
                    }
                }

                if (item) {
                    if (e.getCursor().getAmount() == 1) {
                        e.getInventory().setItem(11, e.getCursor());
                        e.setCursor(new ItemStack(Material.AIR));
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.6F, 1.8F);
                    } else {
                        p.sendMessage(EpicStone.plugin.getConfig().getString("Messages.PutManyItem"));
                    }
                } else {
                    p.sendMessage(EpicStone.plugin.getConfig().getString("Messages.PutNotItem"));
                }
            }

            e.setCancelled(true);
        }

    }

    public static void AddHHItems(Player p, Inventory inv, ItemStack item, ItemStack HHItems, ItemStack BHFItems) {
        String new_HHName = null;
        String YiXQ = null;
        String XrKong = null;
        String XrKongLore = null;
        int Kongnubm = 0;
        int Attributeline = 0;
        boolean EnabledBHPermission = false;
        boolean BooleanAnnouncement = false;
        boolean OpAnnouncement = false;
        List<String> replore = new ArrayList();
        double XiRuchance = 20.0;
        double BaoHuRuchance = 20.0;
        FileConfiguration getconfig = EpicStone.plugin.getConfig();
        Iterator var9 = getconfig.getConfigurationSection("BS_Items").getKeys(false).iterator();

        String s1;
        while(var9.hasNext()) {
            s1 = (String)var9.next();
            s1 = "BS_Items." + s1 + ".";
            String HHName = getconfig.getString(s1 + "BSName");
            String HHLore = getconfig.getString(s1 + "BSLore");
            if (HHItems.getItemMeta().getDisplayName().equalsIgnoreCase(HHName) && HHItems.getItemMeta().getLore().contains(HHLore)) {
                replore = getconfig.getStringList(s1 + "Lore");
                XiRuchance = getconfig.getDouble(s1 + "XiRuchance");
                BaoHuRuchance = getconfig.getDouble(s1 + "BaoHuRuchance");
                XrKong = getconfig.getString(s1 + "LimitKong");
                YiXQ = getconfig.getString(s1 + "BSXQLore");
                BooleanAnnouncement = getconfig.getBoolean(s1 + "EnabledAnnouncement");
                EnabledBHPermission = getconfig.getBoolean(s1 + "EnabledBHPermission");
                OpAnnouncement = getconfig.getBoolean(s1 + "OpAnnouncement");
                new_HHName = HHName;
                XrKongLore = getconfig.getString("EmptyLore." + XrKong + ".KongLore");
            }
        }

        int i;
        for(i = 0; i < HHItems.getItemMeta().getLore().size(); ++i) {
            s1 = (String)HHItems.getItemMeta().getLore().get(i);
            List<String> list = getconfig.getStringList("CheckAttribute.Attribute");

            for(int a = 0; a < list.size(); ++a) {
                if (s1.contains((CharSequence)list.get(a))) {
                    ++Attributeline;
                }
            }
        }

        for(i = 0; i < item.getItemMeta().getLore().size(); ++i) {
            s1 = (String)item.getItemMeta().getLore().get(i);
            if (EpicStone.kongLoresuoyin.contains(s1)) {
                ++Kongnubm;
            }
        }

        s1 = String.valueOf(Kongnubm + 1);
        int XrKongnumsbLevel = getconfig.getInt("LimitKongLevel." + s1 + ".KongLevel");
        if (replore == null) {
            p.sendMessage(EpicStone.plugin.getConfig().getString("Messages.WrongBS"));
        } else {
            ItemMeta meta = item.getItemMeta();
            if (meta.getLore().contains(XrKongLore)) {
                if ((double)EpicStone.getRandomInt1(100, 1) < XiRuchance) {
                    inv.setItem(15, new ItemStack(Material.AIR));
                    inv.setItem(15, org.eplge627.epicstone.Xiru.XiruGui.glass(0, EpicStone.plugin.getConfig().getString("Messages.XrGuiItemLore1")));
                    inv.setItem(11, AddHHItemsEvant(item, YiXQ, XrKongLore, (List)replore));
                    p.sendMessage(EpicStone.plugin.getConfig().getString("Messages.SuccessBS"));
                    if (BooleanAnnouncement && !p.isOp()) {
                        Bukkit.broadcastMessage(EpicStone.plugin.getConfig().getString("Messages.Announcement").replace("%p%", p.getName()).replace("%BSName%", new_HHName));
                    } else if (BooleanAnnouncement && OpAnnouncement) {
                        Bukkit.broadcastMessage(EpicStone.plugin.getConfig().getString("Messages.Announcement").replace("%p%", p.getName()).replace("%BSName%", new_HHName));
                    }

                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 2.0F);
                } else if (BHFItems.getItemMeta().getDisplayName().contains(EpicStone.plugin.getConfig().getString("BHFName")) && BHFItems.getItemMeta().getLore().contains(EpicStone.plugin.getConfig().getString("BHFLore"))) {
                    inv.setItem(13, new ItemStack(Material.AIR));
                    inv.setItem(13, org.eplge627.epicstone.Xiru.XiruGui.glass(2, "§7« §d请放入保护符 §7»"));
                    p.sendMessage(EpicStone.plugin.getConfig().getString("Messages.BHFMessage"));
                } else if ((double)EpicStone.getRandomInt2(100, 1) < BaoHuRuchance && EnabledBHPermission && p.hasPermission(EpicStone.plugin.getConfig().getString("Messages.BHPermission"))) {
                    p.sendMessage(EpicStone.plugin.getConfig().getString("Messages.BHFMessage"));
                    p.closeInventory();
                } else {
                    inv.setItem(15, new ItemStack(Material.AIR));
                    inv.setItem(15, XiruGui.glass(0, EpicStone.plugin.getConfig().getString("Messages.XrGuiItemLore1")));
                    p.sendMessage(EpicStone.plugin.getConfig().getString("Messages.CrushBS"));
                }
            } else {
                p.sendMessage(EpicStone.plugin.getConfig().getString("Messages.NotKong").replace("%kong%", XrKongLore));
            }
        }

    }

    public static ItemStack AddHHItemsEvant(ItemStack item, String YiXQ, String XrKongLore, List<String> replore) {
        ItemStack items = item.clone();
        ItemMeta meta = items.getItemMeta();
        List<String> lore = meta.getLore();

        for(int i = 0; i < lore.size(); ++i) {
            String s = (String)lore.get(i);
            if (s.contains(XrKongLore)) {
                lore.set(i, YiXQ);
                lore.addAll(i + 1, replore);
                meta.setLore(lore);
                items.setItemMeta(meta);
                break;
            }
        }

        return items;
    }
}