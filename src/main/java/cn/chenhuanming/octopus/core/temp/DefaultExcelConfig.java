package cn.chenhuanming.octopus.core.temp;

import cn.chenhuanming.octopus.core.temp.field.MappedField;
import cn.chenhuanming.octopus.formatter.FormatterContainer;
import lombok.Data;

import java.util.List;

/**
 * @author zhuangzf
 * @date 2019/1/16 19:39
 */
@Data
public class DefaultExcelConfig implements ExcelConfig {
    private Class classType;
    private FormatterContainer formatterContainer;
    private List<? extends MappedField> fields;
}
