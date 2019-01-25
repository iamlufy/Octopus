package cn.chenhuanming.octopus.core.temp;

import cn.chenhuanming.octopus.core.config.Config;
import cn.chenhuanming.octopus.core.config.ConfigReader;

/**
 * @author zhuangzf
 * @date 2019/1/22 9:12
 */
public class MyConfigReader implements ConfigReader {
    private Config config;
    public MyConfigReader(Config config) {
        this.config = config;
    }
    @Override
    public Config getConfig() {
        return config;
    }
}
