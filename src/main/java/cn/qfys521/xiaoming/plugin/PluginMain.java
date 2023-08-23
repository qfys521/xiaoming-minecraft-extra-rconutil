package cn.qfys521.xiaoming.plugin;

import cn.chuanwise.xiaoming.plugin.JavaPlugin;
import lombok.Getter;

import java.io.File;

public class PluginMain extends JavaPlugin {
    @Getter
    private final static PluginMain INSTANCE = new PluginMain();

    protected PluginConfiguration pluginConfiguration;

    @Override
    public void onLoad(){
        getLogger().info("插件xiaoming-minecraft-RCON正在启动中");
        getLogger().info("作者:qfys521");

        final File dataFolder = getDataFolder();
        dataFolder.mkdirs();
        pluginConfiguration = setupConfiguration(PluginConfiguration.class , PluginConfiguration::new);
    }



}