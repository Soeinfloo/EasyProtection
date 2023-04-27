package mc.soeinflooo.easyprotection.main;

import mc.soeinflooo.easyprotection.commands.passwordChange;
import mc.soeinflooo.easyprotection.listener.playerChat;
import mc.soeinflooo.easyprotection.listener.playerJoin;
import mc.soeinflooo.easyprotection.listener.playerMove;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    FileConfiguration config = getConfig();
    private static Main plugin;
    public static File f;
    public static FileConfiguration cfg;
    public static String prefix;
    public static String password;


    @Override
    public void onLoad() {
        Bukkit.getLogger().info("EasyProtection successfully loaded!");
    }

    @Override
    public void onEnable() {
        plugin = this;
        addCommands();
        addEvents();
        config();




    }

    @Override
    public void onDisable() {
        Main.getPlugin().saveConfig();
    }

    public static void addEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new playerJoin(), Main.getPlugin());
        pluginManager.registerEvents(new playerChat(), Main.getPlugin());
        pluginManager.registerEvents(new playerMove(), Main.getPlugin());



    }

    private void addCommands() {
        getCommand("easyprotect").setExecutor(new passwordChange());

    }
    private void config() {
        f = new File("plugins/EasyProtection", "easyconfig.yml");
        cfg = YamlConfiguration.loadConfiguration(f);
        cfg.addDefault("Prefix", "§8§l﴾§6EasyProtection§8§l﴿ §7");
        cfg.addDefault("Password",Math.random());
        cfg.addDefault("Settings"+".Add-Players-To-Whitelist",false);
        cfg.addDefault("Settings"+".Automatically-Turn-Whitelist-Off",false);
        cfg.addDefault("Settings"+".Wipe-Whitelist-When-Password-Changes",false);
        cfg.addDefault("Settings"+".Wipe-Whitelist-When-Password-Changes",false);
        cfg.addDefault("Settings"+".Remove-All-Players-From-List-If-Password-Changes",false);
        cfg.options().copyDefaults(true);
        try {
            cfg.save(f);
        } catch (IOException ex) {
        };

        prefix = cfg.getString("Prefix").replaceAll("&", "§");
        password = cfg.getString("Password");
    }
    public static Main getPlugin() {return plugin;}
}
