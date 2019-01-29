package cn.chenhuanming.octopus.core.temp;

import cn.chenhuanming.octopus.core.temp.field.MappedField;
import cn.chenhuanming.octopus.formatter.FormatterContainer;

import java.util.List;

/**
 * @author zhuangzf
 */
public interface ExcelConfig {

    Class getClassType();

    FormatterContainer getFormatterContainer();

    List<? extends MappedField> getFields();


}
