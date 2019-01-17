package cn.chenhuanming.octopus.core.temp;

import cn.chenhuanming.octopus.core.temp.field.Field;
import cn.chenhuanming.octopus.formatter.FormatterContainer;

import java.util.List;

/**
 * @author zhuangzf
 */
public interface ExcelConfig {

    Class getClassType();

    void setClassType(Class clazz);

    FormatterContainer getFormatterContainer();

    void setFormatterContainer(FormatterContainer formatterContainer);


    List<Field> getFields();

    void setFields( List<Field> list);

}
