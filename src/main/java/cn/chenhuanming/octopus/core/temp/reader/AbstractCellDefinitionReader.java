package cn.chenhuanming.octopus.core.temp.reader;

import cn.chenhuanming.octopus.core.config.Config;
import cn.chenhuanming.octopus.core.temp.CellDefinitionReader;
import cn.chenhuanming.octopus.core.temp.ExcelConfig;
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
    public abstract Config readConfig();

    @Override
    public ReadExcelConfig loadReadExcelConfig() throws IOException {
//        ReadExcelConfig readExcelConfig = new DefaultReadExcelConfig(readConfig());
//        return readExcelConfig;
        return null;
    }

    @Override
    public WriteExcelConfig loadWriteExcelConfig() throws IOException {
//        WriteExcelConfig writeExcelConfig = new DefaultWriteExcelConfig(readConfig());
//        return writeExcelConfig;
        return null;
    }
}
