package cn.chenhuanming.octopus.core.temp.field;

import cn.chenhuanming.octopus.core.read.ImportValidation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author zhuangzf
 * @date 2019/1/29 10:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FullField extends MappedField  implements ImportValidation {

    private boolean blankable;

    private List<String> options;

    private Pattern regex;


    private Method picker;
    private Method pusher;

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

    public FullField(FieldProperty fieldProperty,List<MappedField> children) {
        this.setFieldProperty(fieldProperty);
        this.setChildren(children);

    }


}
