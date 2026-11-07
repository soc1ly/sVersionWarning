package ceo.soc1ly.sVersionWarning.pososiotsosi4len;

import ceo.soc1ly.sVersionWarning.Main;
import com.viaversion.viaversion.api.Via;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public final class Check {
   private final Main plugin;

   public Check(Main plugin) {
      this.plugin = plugin;
   }

   public boolean isOld(Player player) {
      FileConfiguration config = this.plugin.getConfig();
      int protocol = Via.getAPI().getPlayerVersion(player);
      return protocol < config.getInt("check.min-protocol-version");
   }
}
