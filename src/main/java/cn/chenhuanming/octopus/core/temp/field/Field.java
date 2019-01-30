package cn.chenhuanming.octopus.core.temp.field;

import cn.chenhuanming.octopus.core.temp.field.impl.MappedField;

import java.util.List;

/**
 * @author chenhuanming
 * Created at 2018/12/7
 */
public interface Field  {

    FieldProperty getFieldProperty();

    List<? extends MappedField> getChildren();

}
