package cn.chenhuanming.octopus.core.temp.writer;

import cn.chenhuanming.octopus.core.temp.ExcelConfig;
import cn.chenhuanming.octopus.core.temp.reader.ReadExcelConfig;

/**
 * 默认写Excel配置类
 *
 * @author zhuangzf
 * @date 2019/1/16 19:20
 */
public class DefaultWriteExcelConfig implements WriteExcelConfig {
    private ExcelConfig excelConfig;

    public DefaultWriteExcelConfig(ExcelConfig excelConfig) {
        this.excelConfig = excelConfig;
    }


    @Override
    public ExcelConfig getExcelConfig() {
        return excelConfig;
    }

}
