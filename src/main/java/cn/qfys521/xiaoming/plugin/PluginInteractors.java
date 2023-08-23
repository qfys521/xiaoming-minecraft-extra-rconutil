package cn.qfys521.xiaoming.plugin;

import cn.chuanwise.xiaoming.annotation.Filter;
import cn.chuanwise.xiaoming.annotation.FilterParameter;
import cn.chuanwise.xiaoming.annotation.Required;
import cn.chuanwise.xiaoming.interactor.SimpleInteractors;
import cn.chuanwise.xiaoming.user.XiaoMingUser;
import net.kronos.rkon.core.Rcon;
import net.kronos.rkon.core.ex.AuthenticationException;

import java.io.IOException;
import java.util.Objects;

public class PluginInteractors extends SimpleInteractors<PluginMain> {
    @Filter("添加RCON远程服务器")
    @Required("XMMC.RCONServer.add")
    public void addRCONServer(XiaoMingUser user){
        user.sendMessage("请告诉小明您想要添加的服务器名称吧!");
        while (true){
            try {
                String serverName = user.nextMessage().toString();
                if (Objects.equals(serverName, "退出")) break;
                user.sendMessage("接下来请**私信**告诉小明你的RCON服务器ip吧！请按照以下的格式发送。\n" + "exampleServerName:port");
                String serverip = user.nextPrivateMessage().toString();
                if (Objects.equals(serverip, "退出"))break;
                user.sendMessage("接下来请**私信**小明发送您的RCON服务器密码吧！");
                String serverPassword = user.nextPrivateMessage().toString();
                plugin.pluginConfiguration.serverList.put(serverName,new String[]{serverip, serverPassword});
                break;
            } catch (Exception e) {
                user.sendError("发生了异常！");
                getLogger().error(e.toString(),e);
                break;
            }

        }
        user.sendMessage("已结束添加流程。");
    }
    @Filter("删除RCON服务器 {服务器名称}")
    @Required("XMMC.RCONServer.remove")
    public void removeRCONServer(XiaoMingUser user , @FilterParameter("服务器名称")String serverName){
        try {
            plugin.pluginConfiguration.serverList.remove(serverName);
            user.sendMessage("已尝试删除。");
        }catch (NullPointerException e){
            getLogger().error(e.toString(),e);
        }
    }

    @Filter("发送命令")
    @Required("XMMC.RCONServer.sendCommand")
    public void sendCommand(XiaoMingUser user){
        user.sendMessage("请告诉小明你想要往哪个服务器发送命令呢?");
        String serverName = user.nextMessageOrExit().serialize();
        user.sendMessage("请告诉小明你要发送的命令吧(一行一条)！");
        String[] commands = user.nextMessageOrExit().serialize().split("\n");
        try {

            Rcon rcon = new Rcon(plugin.pluginConfiguration.getServerList().get(serverName)[0].split(":")[0],
                    Integer.parseInt(plugin.pluginConfiguration.getServerList().get(serverName)[0].split(":")[1]),
                    plugin.pluginConfiguration.getServerList().get(serverName)[1].getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (String command : commands) {
                stringBuffer.append(rcon.command(command)).append("\n");
            }
            user.sendMessage("发送结果:");
            user.sendMessage(stringBuffer.toString());
        } catch (Exception e) {
            getLogger().error(e.toString(),e);
        }

    }
}
