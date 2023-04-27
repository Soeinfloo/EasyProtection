package mc.soeinflooo.easyprotection.listener;

import mc.soeinflooo.easyprotection.main.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;

public class playerJoin implements Listener {

    @EventHandler
    public void onServerJoin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        String whitelist = "no";

        PersistentDataContainer pdc = p.getPersistentDataContainer();
        NamespacedKey pdc_key = new NamespacedKey(Main.getPlugin(), "whitelist");
        //FileConfiguration config = Main.getPlugin().getConfig();
        if (pdc.get(pdc_key, PersistentDataType.STRING) == null) {

            pdc.set(pdc_key, PersistentDataType.STRING, whitelist);
            p.sendMessage(
                    "§e****************************************************************\n" +
                            Main.prefix+"This server runs  'EasyProtection'!\n" +
                            Main.prefix+"Please type the set Password in Chat!\n" +
                            Main.prefix+"or you'll be kicked after 30 seconds!\n" +
                            "§e****************************************************************\n"

            );
            p.setGameMode(GameMode.ADVENTURE);
            p.setInvulnerable(true);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    p.kick(Component.newline().content(Main.prefix+"Time has run out!"));
                }
            }, 600L); //20 Tick (1 Second) delay before run() is called
        } else if (pdc.get(pdc_key, PersistentDataType.STRING).equals("no")) {
            p.sendMessage(
                    "§e****************************************************************\n" +
                            Main.prefix+"This server runs  'EasyProtection'!\n" +
                            Main.prefix+"you have 30 Seconds to write\n" +
                            Main.prefix+"the set Password in Chat!\n" +
                            "§e****************************************************************\n"

            );
            p.setGameMode(GameMode.ADVENTURE);
            p.setInvulnerable(true);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    p.kick(Component.newline().content(Main.prefix+"Time has run out!"));
                }
            }, 600L); //20 Tick (1 Second) delay before run() is called
        }
    }
}
