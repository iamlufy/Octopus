package cn.chenhuanming.octopus.core.temp.reader;

import cn.chenhuanming.octopus.core.temp.ExcelConfig;

/**
 * @author zhuangzf
 * @date 2019/1/16 19:20
 */
public class DefaultReadExcelConfig implements ReadExcelConfig {

    private ExcelConfig excelConfig;
    public DefaultReadExcelConfig(ExcelConfig excelConfig) {
        this.excelConfig = excelConfig;
    }

    @Override
    public void setExcelConfig(ExcelConfig excelConfig) {
        this.excelConfig = excelConfig;
    }

    @Override
    public ExcelConfig getExcelConfig() {
        return excelConfig;
    }
}
