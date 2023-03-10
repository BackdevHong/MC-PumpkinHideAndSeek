package pumpkinhideandseek.pumpkinhas;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pumpkinhideandseek.pumpkinhas.command.game.gameCommand;
import pumpkinhideandseek.pumpkinhas.event.WorldCheck;
import pumpkinhideandseek.pumpkinhas.event.WorldRemove;

public final class PumpkinHAS extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("호박 숨바꼭질 플러그인이 로딩되었습니다. 링딩동 링딩동 링디기디기 링딩동");
        getCommand("game").setExecutor(new gameCommand());
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        event();
    }

    @Override
    public void onDisable() {
        getLogger().info("호박 숨바꼭질 플러그인이 꺼졌습니다.");
    }

    public void event() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new WorldCheck(), this);
        pm.registerEvents(new WorldRemove(), this);
    }
}
