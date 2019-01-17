package cn.chenhuanming.octopus.core.temp.reader;

import cn.chenhuanming.octopus.core.temp.ExcelConfig;

/**
 * 写入excel配置接口类
 *
 * @author zhuangzf
 */
public interface ReadExcelConfig {
    /**
     * 组合excelConfig，不通过继承
     */
    void setExcelConfig(ExcelConfig excelConfig);

    ExcelConfig getExcelConfig();
}
