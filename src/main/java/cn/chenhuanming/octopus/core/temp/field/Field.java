package cn.chenhuanming.octopus.core.temp.field;

import cn.chenhuanming.octopus.core.read.ImportValidation;
import cn.chenhuanming.octopus.core.temp.field.impl.MappedField;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author chenhuanming
 * Created at 2018/12/7
 */
public interface Field extends ImportValidation {

    FieldProperty getFieldProperty();

    List<Field> getChildren();

    boolean isLeaf();

    Method getPusher();

}
