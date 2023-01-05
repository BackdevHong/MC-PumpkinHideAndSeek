package pumpkinhideandseek.pumpkinhas.command.game;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pumpkinhideandseek.pumpkinhas.PumpkinHAS;
import pumpkinhideandseek.pumpkinhas.gui.worldCheckGui;

import java.io.File;
import java.io.IOException;


public class gameCommand implements CommandExecutor {
    private final String message = format("&e&l[ &c&lPumpkin &e&l]&f&l");
    private final File fileDir = PumpkinHAS.getPlugin(PumpkinHAS.class).getDataFolder();
    private YamlConfiguration config;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                if (args.length < 1) {
                    player.sendMessage(message + " 호박 숨바꼭질 명령어 리스트입니다!\n" +
                            message + " /game start - 게임을 시작합니다.\n" +
                            message + " /game stop - 게임을 강제로 종료합니다. \n" +
                            message + " /game settings - 게임 설정을 변경합니다. \n");
                } else {
                    if (args[0].equalsIgnoreCase("settings")) {
                        if (args.length < 2) {
                            player.sendMessage(message + " 호박 숨바꼭질 설정 명령어 리스트입니다!\n" +
                                    message + " /game settings world ( add / remove / check )\n" +
                                    message + " 월드리스트를 설정합니다.\n" +
                                    message + " /game settings randomLocation ( add / remove / check )\n" +
                                    message + " 월드에 대한 랜덤 위치를 설정합니다.\n" +
                                    message + " /game settings escapeLocation ( add / remove / check )\n" +
                                    message + " 월드에 대한 탈출구를 설정합니다.\n");
                        } else {
                            if (args[1].equalsIgnoreCase("world")) {
                                if (args.length < 3) {
                                    player.sendMessage(message + " add, remove, check 3개중 하나를 적어주세요.");
                                } else if (args[2].equalsIgnoreCase("add")) {
                                    String worldName = player.getWorld().getName();
                                    File worldfile = new File(fileDir, worldName + ".yml");
                                    try {
                                        if (worldfile.createNewFile()) {
                                            player.sendMessage(message + " 월드 추가가 성공적으로 이루어졌습니다.");
                                            config = YamlConfiguration.loadConfiguration(worldfile);
                                            config.set("defaultLoc.x", player.getLocation().getX());
                                            config.set("defaultLoc.y", player.getLocation().getY());
                                            config.set("defaultLoc.z", player.getLocation().getZ());
                                            config.save(worldfile);
                                        } else {
                                            player.sendMessage(message + " 이미 존재하는 월드입니다.");
                                        }
                                    } catch (IOException e) {
                                       throw new RuntimeException(e);
                                    }
                                } else if (args[2].equalsIgnoreCase("check")) {
                                    worldCheckGui worldCheckGui = new worldCheckGui();
                                    worldCheckGui.open((Player) sender);
                                }
                            }
                        }
                    }
                }
            } else {
                player.sendMessage(message + " 권한이 없습니다.");
            }
        } else {
            System.out.println("해당 명령어는 콘솔로 실행할 수 없습니다.");
            return false;
        }
        return false;
    }

    public String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
