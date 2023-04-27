package mc.soeinflooo.easyprotection.listener;

import mc.soeinflooo.easyprotection.main.Main;
import org.bukkit.NamespacedKey;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class playerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        PersistentDataContainer pdc = p.getPersistentDataContainer();
        NamespacedKey pdc_key = new NamespacedKey(Main.getPlugin(), "whitelist");
        if (!(Objects.equals(pdc.get(pdc_key, PersistentDataType.STRING), "yes"))) {
            e.setCancelled(true);
        }
    }
}
