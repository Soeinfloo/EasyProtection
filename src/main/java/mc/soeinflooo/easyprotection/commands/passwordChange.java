package mc.soeinflooo.easyprotection.commands;

import mc.soeinflooo.easyprotection.main.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import java.io.IOException;

public class passwordChange implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.isOp()) {
                if (args.length == 0) {
                    sender.sendMessage("Usage: /ep <password");
                }else{
                    StringBuilder result = new StringBuilder(args[0]);
                    for (int i = 1; i < args.length; i++) {
                        result.append(" ").append(args[i]);
                    }
                    Main.cfg.set("Password", result.toString());
                    try {
                        Main.cfg.save(Main.f);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    p.sendMessage(Component.newline().content(Main.prefix+"Password has been set to: "+ result));
                }
                return true;

            }
        }
        return false;
    }
}
