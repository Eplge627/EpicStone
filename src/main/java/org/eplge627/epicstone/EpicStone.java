package org.eplge627.epicstone;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import org.eplge627.epicstone.QuXie.CheckHH;
import org.eplge627.epicstone.QuXie.QuXieClose;
import org.eplge627.epicstone.QuXie.QuXieGui;
import org.eplge627.epicstone.Xiru.XiruGui;
import org.eplge627.epicstone.Xiru.XiruGuiClose;
import org.eplge627.epicstone.Xiru.XiruGuiEvent;

import net.milkbowl.vault.economy.Economy;
import org.eplge627.epicstone.QuXie.QuXieEvent;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class EpicStone extends JavaPlugin {
    public static EpicStone plugin;
    public static Economy economy = null;
    public static String prefix = "§f[§c系统§f] §a";
    public static HashMap<String, String> getQxHH_Key = new HashMap();
    public static List<String> HH_Name = new ArrayList();
    public static List<String> Kong_Lore = new ArrayList();
    public static List<String> qcsuoyin = new ArrayList();
    public static List<String> kongsuoyin = new ArrayList();
    public static List<String> kongLoresuoyin = new ArrayList();
    public static File Quxiefile = new File("plugins/EpicStone", "QuXie.yml");
    public static FileConfiguration QuxieG;

    public EpicStone() {
    }

    public static void saveQuXieConfig() {
        try {
            QuxieG.save(Quxiefile);
        } catch (IOException var1) {
            var1.printStackTrace();
        }

    }

    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        String key;
        String keyLore;
        Iterator var3;
        String suoyin2;
        String name2;
        if (this.getConfig().getString("BS_Items") != null) {
            var3 = this.getConfig().getConfigurationSection("BS_Items").getKeys(false).iterator();

            while (var3.hasNext()) {
                key = (String) var3.next();
                keyLore = "BS_Items." + key + ".";
                suoyin2 = this.getConfig().getString(keyLore + "BSName").replace("&", "§");
                name2 = this.getConfig().getString(keyLore + "BSSuoYin").replace("&", "§");
                String BSXQLore = this.getConfig().getString(keyLore + "BSXQLore").replace("&", "§");
                HH_Name.add(suoyin2);
                qcsuoyin.add(name2);
                kongLoresuoyin.add(BSXQLore);
            }
        }

        if (this.getConfig().getString("EmptyLore") != null) {
            var3 = this.getConfig().getConfigurationSection("EmptyLore").getKeys(false).iterator();

            while (var3.hasNext()) {
                key = (String) var3.next();
                keyLore = "EmptyLore." + key + ".";
                suoyin2 = this.getConfig().getString(keyLore + "KongSuoYin").replace("&", "§");
                name2 = this.getConfig().getString(keyLore + "KongLore").replace("&", "§");
                Kong_Lore.add(name2);
                kongsuoyin.add(suoyin2);
            }
        }

        if (QuxieG.getString("QxSettings") != null) {
            var3 = QuxieG.getConfigurationSection("QxSettings").getKeys(false).iterator();

            while (var3.hasNext()) {
                key = (String) var3.next();
                keyLore = QuxieG.getString("QxSettings." + key + ".keyLore").replace("&", "§");
                getQxHH_Key.put(keyLore, key);
            }
        }

        this.getServer().getPluginManager().registerEvents(new XiruGuiEvent(), plugin);
        this.getServer().getPluginManager().registerEvents(new XiruGuiClose(), plugin);
        this.getServer().getPluginManager().registerEvents(new QuXieEvent(), plugin);
        this.getServer().getPluginManager().registerEvents(new QuXieClose(), plugin);
        Bukkit.getConsoleSender().sendMessage("§f--------------------------------");
        Bukkit.getConsoleSender().sendMessage("§b___ ___");
        Bukkit.getConsoleSender().sendMessage("§b|__ |__) §6Epic&fStone §81.0.2");
        Bukkit.getConsoleSender().sendMessage("§b|__ |    §aHello World！");
        Bukkit.getConsoleSender().sendMessage("§b");
        Bukkit.getConsoleSender().sendMessage("§a该插件永久免费");
        Bukkit.getConsoleSender().sendMessage("§f作者QQ：§b2194849262");
        Bukkit.getConsoleSender().sendMessage("§f注意！本插件为 §bPerpetualWorld &f定制版");
        Bukkit.getConsoleSender().sendMessage("§a一切准备就绪，插件启动成功！");
        Bukkit.getConsoleSender().sendMessage("§f--------------------------------");
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§f--------------------------------");
        Bukkit.getConsoleSender().sendMessage("§c___ ___");
        Bukkit.getConsoleSender().sendMessage("§c|__ |__) §6Epic&fStone §81.0.2");
        Bukkit.getConsoleSender().sendMessage("§c|__ |    §1GoodBye!");
        Bukkit.getConsoleSender().sendMessage("§b");
        Bukkit.getConsoleSender().sendMessage("§a该插件永久免费");
        Bukkit.getConsoleSender().sendMessage("§f作者QQ：§b2194849262");
        Bukkit.getConsoleSender().sendMessage("§f注意！本插件为 §bPerpetualWorld &f定制版");
        Bukkit.getConsoleSender().sendMessage("§a插件已卸载，愿我们还能再度相见！");
        Bukkit.getConsoleSender().sendMessage("§f--------------------------------");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String Command, String[] args) {
        Player p;
        ItemStack item;
        String name;
        if (!Command.equalsIgnoreCase("jbsqc")) {
            if (Command.equalsIgnoreCase("jbsxr")) {
                if (args.length == 0) {
                    XiruGui.OpenGui((Player) sender);
                }

                if (args.length == 1 && args[0].equalsIgnoreCase("help") && sender.isOp()) {
                    sender.sendMessage(this.getConfig().getString("Messages.JbsxrMessageBiaoTi"));
                    sender.sendMessage(this.getConfig().getString("Messages.FirstjbsxrMessage"));
                    sender.sendMessage(this.getConfig().getString("Messages.SecondbsxrMessage"));
                    sender.sendMessage(this.getConfig().getString("Messages.JbsxrMessageWeiBu"));
                }

                ItemMeta meta;
                ItemStack items;
                ArrayList lore;
                if (args.length == 2 && args[0].equalsIgnoreCase("add") && sender.isOp()) {
                    p = (Player) sender;
                    item = p.getInventory().getItemInMainHand();
                    if (!itemIsNull(item)) {
                        if (kongsuoyin.contains(args[1])) {
                            items = item.clone();
                            meta = items.getItemMeta();
                            lore = new ArrayList();
                            if (itemIsLore(items)) {
                                lore.add(this.getConfig().getString("EmptyLore." + args[1] + ".KongLore"));
                            } else {
                                lore.addAll(items.getItemMeta().getLore());
                                lore.add(this.getConfig().getString("EmptyLore." + args[1] + ".KongLore"));
                            }

                            meta.setLore(lore);
                            items.setItemMeta(meta);
                            p.getInventory().setItemInMainHand(items);
                            sender.sendMessage(this.getConfig().getString("Messages.AddEmptyLore"));
                        } else {
                            sender.sendMessage(this.getConfig().getString("Messages.NotHaveKong"));
                        }
                    }
                }

                if (args.length == 2 && args[0].equalsIgnoreCase("set") && sender.isOp()) {
                    p = (Player) sender;
                    item = p.getInventory().getItemInMainHand();
                    if (!itemIsNull(item)) {
                        if (qcsuoyin.contains(args[1])) {
                            items = item.clone();
                            meta = items.getItemMeta();
                            lore = new ArrayList();
                            name = null;
                            if (itemIsLore(items)) {
                                lore.add(this.getConfig().getString("BS_Items." + args[1] + ".BSLore"));
                                name = this.getConfig().getString("BS_Items." + args[1] + ".BSName");
                            } else {
                                lore.addAll(items.getItemMeta().getLore());
                                lore.add(this.getConfig().getString("BS_Items." + args[1] + ".BSLore"));
                                name = this.getConfig().getString("BS_Items." + args[1] + ".BSName");
                            }

                            meta.setLore(lore);
                            meta.setDisplayName(name);
                            items.setItemMeta(meta);
                            p.getInventory().setItemInMainHand(items);
                            p.sendMessage("基尼太没,成功设置魂环");
                        } else {
                            sender.sendMessage(this.getConfig().getString("Messages.WrongSuoYin"));
                        }
                    }
                }
            }

            return false;
        } else {
            if (args.length == 0) {
                QuXieGui.OpenGui((Player) sender);
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("help") && sender.isOp()) {
                sender.sendMessage(this.getConfig().getString("Messages.JbsqcMessageBiaoTi"));
                sender.sendMessage(this.getConfig().getString("Messages.FirstjbsqcMessage"));
                sender.sendMessage(this.getConfig().getString("Messages.SecondbsqcMessage"));
                sender.sendMessage(this.getConfig().getString("Messages.JbsqcMessageWeiBu"));
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("create") && sender.isOp()) {
                p = (Player) sender;
                item = p.getInventory().getItemInMainHand();
                boolean BianJieSetPrefixEnabled = this.getConfig().getBoolean("BianJieSetPrefixEnabled");
                name = this.getConfig().getString("prefix");
                if (!itemIsNull(item) && !itemIsLore(item)) {
                    if (qcsuoyin.contains(args[1])) {
                        String item_Key = args[1];
                        List<String> SetSXLore = this.getConfig().getStringList("BS_Items." + args[1] + ".Lore");
                        String XrKong = this.getConfig().getString("BS_Items." + args[1] + ".LimitKong");
                        int money = this.getConfig().getInt("BS_Items." + args[1] + ".QCMoney");
                        FileConfiguration yml = QuxieG;
                        yml.set("QxSettings." + item_Key + ".money", money);
                        if (BianJieSetPrefixEnabled) {
                            yml.set("QxSettings." + item_Key + ".keyLore", name + item.getItemMeta().getDisplayName());
                        } else {
                            yml.set("QxSettings." + item_Key + ".keyLore", "XXXXXXXXXXXXXXX");
                        }

                        yml.set("QxSettings." + item_Key + ".Xrkong", XrKong);
                        yml.set("QxSettings." + item_Key + ".SXLore", SetSXLore);
                        yml.set("QxSettings." + item_Key + ".displayItem", item);
                        saveQuXieConfig();
                        sender.sendMessage(this.getConfig().getString("Messages.CreateSuccess"));
                    } else {
                        sender.sendMessage(this.getConfig().getString("Messages.WrongSuoYin"));
                    }
                } else {
                    sender.sendMessage(this.getConfig().getString("Messages.NullItem"));
                }
            }
            return true;
        }
    }

    public static boolean itemIsNull(ItemStack item) {
        return item == null || item.getType() == Material.AIR;
    }

    public static boolean itemIsLore(ItemStack item) {
        return item.getItemMeta().getLore() == null;
    }

    public static int getRandomInt1(int max, int mix) {
        return (new Random()).nextInt(max) + mix;
    }

    public static int getRandomInt2(int max, int mix) {
        return (new Random()).nextInt(max) + mix;
    }

    static {
        QuxieG = YamlConfiguration.loadConfiguration(Quxiefile);
    }
}
