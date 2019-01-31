package cn.chenhuanming.octopus.core.temp.field.impl;

import cn.chenhuanming.octopus.core.read.ImportValidation;
import cn.chenhuanming.octopus.core.temp.field.Field;
import cn.chenhuanming.octopus.core.temp.field.FieldProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 匹配配置文件的属性，含有扩展属性
 *
 * @author zhuangzf
 * @date 2019/1/29 10:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FullField extends MappedField {




    protected Method picker;


    /**
     * excel单元格格式
     */
    private FieldStyle fieldStyle;

    public FullField() {

    }
    public FullField(FieldProperty fieldProperty) {
        this.setFieldProperty(fieldProperty) ;
        this.setBlankable(true);
    }
    public FullField(FieldProperty fieldProperty,FieldStyle fieldStyle) {
        this.setFieldStyle(fieldStyle);
        this.setFieldProperty(fieldProperty);
        this.setBlankable(true);
    }

    public FullField(FieldProperty fieldProperty,List<Field> children) {
        this.setFieldProperty(fieldProperty);
        this.setChildren(children);

    }


}
