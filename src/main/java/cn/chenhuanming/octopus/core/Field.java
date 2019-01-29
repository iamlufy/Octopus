package cn.chenhuanming.octopus.core;

import cn.chenhuanming.octopus.core.read.ImportValidation;
import cn.chenhuanming.octopus.core.read.style.FieldCellStyle;
import cn.chenhuanming.octopus.core.read.style.HeaderCellStyle;
import cn.chenhuanming.octopus.core.temp.field.FieldProperty;
import cn.chenhuanming.octopus.formatter.Formatter;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * @author chenhuanming
 * Created at 2018/12/7
 */
public interface Field  {

    /**
     * 获取基本属性内容
     *
     * @return
     */
    FieldProperty getFieldProperty();





}
