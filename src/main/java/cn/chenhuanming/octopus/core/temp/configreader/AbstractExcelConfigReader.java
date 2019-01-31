package cn.chenhuanming.octopus.core.temp.configreader;

import cn.chenhuanming.octopus.core.temp.DefaultExcelConfig;
import cn.chenhuanming.octopus.core.temp.ExcelConfig;
import cn.chenhuanming.octopus.core.temp.reader.DefaultReadExcelConfig;
import cn.chenhuanming.octopus.core.temp.reader.ReadExcelConfig;
import cn.chenhuanming.octopus.core.temp.writer.DefaultWriteExcelConfig;
import cn.chenhuanming.octopus.core.temp.writer.WriteExcelConfig;

import java.io.IOException;

/**
 * @author zhuangzf
 * @date 2019/1/16 10:35
 */
public abstract class AbstractExcelConfigReader implements ExcelConfigReader {

    private ExcelConfig excelConfig;

    public AbstractExcelConfigReader() {
        this.excelConfig = readConfig();
    }

    /**
     * 子类定义读取配置文件
     *
     * @return
     */
    public abstract ExcelConfig readConfig();

    @Override
    public ReadExcelConfig loadReadExcelConfig() {
        return new DefaultReadExcelConfig(excelConfig);
    }

    @Override
    public WriteExcelConfig loadWriteExcelConfig() throws IOException {
        return new DefaultWriteExcelConfig(excelConfig);
    }
}
