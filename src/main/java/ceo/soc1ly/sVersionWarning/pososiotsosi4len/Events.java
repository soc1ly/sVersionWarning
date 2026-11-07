package ceo.soc1ly.sVersionWarning.pososiotsosi4len;

import ceo.soc1ly.sVersionWarning.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class Events implements Listener {
   private final Main plugin;
   private final Check check;

   public Events(Main plugin) {
      this.plugin = plugin;
      this.check = new Check(plugin);
   }

   @EventHandler
   public void onJoin(PlayerJoinEvent event) {
      Player player = event.getPlayer();
      FileConfiguration config = this.plugin.getConfig();
      if (config.getBoolean("check.enabled")) {
         if (this.check.isOld(player)) {
            this.sendMessage(player);
            this.sendTitle(player);
         }
      }
   }

   private void sendMessage(Player player) {
      if (this.plugin.getConfig().getBoolean("message.enabled")) {
         for(String line : this.plugin.getConfig().getStringList("message.lines")) {
            player.sendMessage(this.plugin.getMsg().parse(player, line));
         }

      }
   }

   private void sendTitle(Player player) {
      if (this.plugin.getConfig().getBoolean("title.enabled")) {
         String title = this.plugin.getConfig().getString("title.title", "");
         String subtitle = this.plugin.getConfig().getString("title.subtitle", "");
         int fadeIn = this.plugin.getConfig().getInt("title.fade-in", 10);
         int stay = this.plugin.getConfig().getInt("title.stay", 60);
         int fadeOut = this.plugin.getConfig().getInt("title.fade-out", 10);
         player.sendTitle(this.plugin.getMsg().parse(player, title), this.plugin.getMsg().parse(player, subtitle), fadeIn, stay, fadeOut);
      }
   }
}
