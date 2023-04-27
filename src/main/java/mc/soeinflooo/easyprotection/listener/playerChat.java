package mc.soeinflooo.easyprotection.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import mc.soeinflooo.easyprotection.main.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class playerChat implements Listener {

    @EventHandler
    public void onChatMessageSend(AsyncChatEvent e) {
        Player p = e.getPlayer();
        Component m = e.message();
        String plain = PlainTextComponentSerializer.plainText().serialize(m);
        PersistentDataContainer pdc = p.getPersistentDataContainer();
        NamespacedKey pdc_key = new NamespacedKey(Main.getPlugin(), "whitelist");
        //FileConfiguration config = Main.getPlugin().getConfig();
        if (!(Objects.equals(pdc.get(pdc_key, PersistentDataType.STRING), "yes"))) {
            e.setCancelled(true);
            if (plain.equals(Main.password)) {
                String whitelist = "yes";
                pdc.set(pdc_key, PersistentDataType.STRING, whitelist);
                p.setInvulnerable(false);

                Bukkit.getScheduler().cancelTasks(Main.getPlugin());
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(Component.newline().content(Main.prefix+"You've successfully entered the right Password! Have Fun!"));
                }, 1L);
                e.setCancelled(true);
            }else Bukkit.getLogger().info("[EasyProtection] Player tried using the wrong Password to get Server access:"+"'"+plain+"'");
        }
    }
}
