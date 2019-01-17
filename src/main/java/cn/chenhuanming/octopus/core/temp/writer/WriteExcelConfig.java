package cn.chenhuanming.octopus.core.temp.writer;

import cn.chenhuanming.octopus.core.temp.ExcelConfig;

/**
 * 读取excel配置接口类
 *
 * @author zhuangzf
 */
public interface WriteExcelConfig {
    /**
     * 组合excelConfig，不通过继承
     */
    void setExcelConfig(ExcelConfig excelConfig);

    ExcelConfig getExcelConfig();
}
