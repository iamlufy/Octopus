package cn.chenhuanming.octopus.core.temp;

import cn.chenhuanming.octopus.core.temp.field.Field;
import cn.chenhuanming.octopus.core.temp.field.impl.FullField;
import cn.chenhuanming.octopus.core.temp.field.impl.MappedField;
import cn.chenhuanming.octopus.formatter.FormatterContainer;

import java.util.List;

/**
 * 定义excel文件与代码映射关系
 *
 * @author zhuangzf
 */
public interface ExcelConfig {

    /**
     * Class
     *
     * @return Class
     */
    Class getClassType();

    /**
     * 格式
     *
     * @return
     */
    FormatterContainer getFormatterContainer();

    /**
     * 属性
     *
     * @return FullField
     */
    List<Field> getFields();


}
