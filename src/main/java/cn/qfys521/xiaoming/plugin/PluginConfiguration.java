package cn.qfys521.xiaoming.plugin;

import cn.chuanwise.xiaoming.preservable.SimplePreservable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
public class PluginConfiguration extends SimplePreservable<PluginMain> {

    HashMap<String , String[]> serverList = new HashMap<>();

}
