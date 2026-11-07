package ceo.soc1ly.sVersionWarning;

import ceo.soc1ly.sVersionWarning.pososiotsosi4len.Cmd;
import ceo.soc1ly.sVersionWarning.pososiotsosi4len.Events;
import ceo.soc1ly.sVersionWarning.pososiotsosi4len.Msg;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
   private Msg msg;

   public void onEnable() {
      this.saveDefaultConfig();
      this.reloadPlugin();
      Bukkit.getPluginManager().registerEvents(new Events(this), this);
      PluginCommand command = this.getCommand("sversionwarning");
      if (command != null) {
         Cmd cmd = new Cmd(this);
         command.setExecutor(cmd);
         command.setTabCompleter(cmd);
      }

   }

   public void reloadPlugin() {
      this.reloadConfig();
      this.msg = new Msg(this);
   }

   public Msg getMsg() {
      return this.msg;
   }
}
