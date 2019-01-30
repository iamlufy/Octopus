package cn.chenhuanming.octopus.core.temp.configreader;

import cn.chenhuanming.octopus.core.temp.DefaultExcelConfig;
import cn.chenhuanming.octopus.core.temp.reader.DefaultReadExcelConfig;
import cn.chenhuanming.octopus.core.temp.reader.ReadExcelConfig;
import cn.chenhuanming.octopus.core.temp.writer.DefaultWriteExcelConfig;
import cn.chenhuanming.octopus.core.temp.writer.WriteExcelConfig;

import java.io.IOException;

/**
 * @author zhuangzf
 * @date 2019/1/16 10:35
 */
public abstract class AbstractCellDefinitionReader implements CellDefinitionReader {


    /**
     * 子类定义读取配置文件
     *
     * @return
     */
    public abstract DefaultExcelConfig readConfig();

    @Override
    public ReadExcelConfig loadReadExcelConfig() {
        return new DefaultReadExcelConfig(readConfig());
    }

    @Override
    public WriteExcelConfig loadWriteExcelConfig() throws IOException {
        return new DefaultWriteExcelConfig(readConfig());
    }
}
