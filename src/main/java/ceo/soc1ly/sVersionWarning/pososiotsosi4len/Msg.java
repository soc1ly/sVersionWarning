package ceo.soc1ly.sVersionWarning.pososiotsosi4len;

import ceo.soc1ly.sVersionWarning.Main;
import ceo.soc1ly.sVersionWarning.chlen.osloeb;
import com.viaversion.viaversion.api.Via;
import org.bukkit.entity.Player;

public final class Msg {
   private final Main plugin;

   public Msg(Main plugin) {
      this.plugin = plugin;
   }

   public String text(String path) {
      return this.parse(this.plugin.getConfig().getString(path, ""));
   }

   public String text(Player player, String path) {
      return this.parse(this.raw(player, this.plugin.getConfig().getString(path, "")));
   }

   public String parse(String text) {
      return osloeb.color(this.raw(text));
   }

   public String parse(Player player, String text) {
      return osloeb.color(this.raw(player, text));
   }

   public String raw(String text) {
      return text == null ? "" : text.replace("%prefix%", this.plugin.getConfig().getString("settings.prefix", "")).replace("%min_version%", this.plugin.getConfig().getString("check.min-version-name", "unknown")).replace("%min_protocol%", String.valueOf(this.plugin.getConfig().getInt("check.min-protocol-version")));
   }

   public String raw(Player player, String text) {
      return this.raw(text).replace("%player_protocol%", String.valueOf(Via.getAPI().getPlayerVersion(player)));
   }
}
