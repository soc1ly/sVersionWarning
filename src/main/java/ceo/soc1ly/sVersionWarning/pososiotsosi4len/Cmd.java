package ceo.soc1ly.sVersionWarning.pososiotsosi4len;

import ceo.soc1ly.sVersionWarning.Main;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public final class Cmd implements CommandExecutor, TabCompleter {
   private final Main plugin;

   public Cmd(Main plugin) {
      this.plugin = plugin;
   }

   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
         if (!sender.hasPermission("sversionwarning.reload")) {
            sender.sendMessage(this.plugin.getMsg().text("messages.no-permission"));
            return true;
         } else {
            this.plugin.reloadPlugin();
            sender.sendMessage(this.plugin.getMsg().text("messages.reload"));
            return true;
         }
      } else {
         sender.sendMessage(this.plugin.getMsg().text("messages.usage"));
         return true;
      }
   }

   public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
      return args.length == 1 ? Collections.singletonList("reload") : Collections.emptyList();
   }
}
