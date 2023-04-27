package mc.soeinflooo.easyprotection.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import mc.soeinflooo.easyprotection.main.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.LogRecord;

public class playerChat implements Listener {

    @EventHandler
    public void onChatMessageSend(AsyncChatEvent e) {
        Player p = e.getPlayer();
        Component m = e.message();
        String plain = PlainTextComponentSerializer.plainText().serialize(m);
        PersistentDataContainer pdc = p.getPersistentDataContainer();
        NamespacedKey pdc_key = new NamespacedKey(Main.getPlugin(), "whitelist");
        //FileConfiguration config = Main.getPlugin().getConfig();
        if (!(pdc.get(pdc_key, PersistentDataType.STRING).equals("yes"))) {
            e.setCancelled(true);
            if (plain.equals(Main.password)) {
                String whitelist = "yes";
                pdc.set(pdc_key, PersistentDataType.STRING, whitelist);
                p.setInvulnerable(false);

                Bukkit.getScheduler().cancelTasks(Main.getPlugin());
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(Component.newline().content(Main.prefix+"You've successfully entered the right Password! Have Fun!"));
                    }
                }, 1L);
                e.setCancelled(true);
            }else Bukkit.getLogger().info("[EasyProtection] Player tried using the wrong Password to get Server access:"+"'"+plain+"'");
        }
    }
}
