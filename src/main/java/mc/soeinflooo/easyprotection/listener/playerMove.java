package mc.soeinflooo.easyprotection.listener;

import mc.soeinflooo.easyprotection.main.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;

public class playerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = (Player) e.getPlayer();
        PersistentDataContainer pdc = p.getPersistentDataContainer();
        NamespacedKey pdc_key = new NamespacedKey(Main.getPlugin(), "whitelist");
        if (!(pdc.get(pdc_key, PersistentDataType.STRING).equals("yes"))) {
            e.setCancelled(true);
        }
    }
}
