package cn.chenhuanming.octopus.core.temp.field;

import cn.chenhuanming.octopus.core.read.ImportValidation;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author zhuangzf
 * @date 2019/1/21 19:26
 */
@Data
public class MappedField implements ImportValidation {

    private boolean blankAble;

    private List<String> options;

    protected Pattern regex;


    /**
     * excel单元格信息属性
     */
    private FieldProperty fieldProperty;
    /**
     * excel单元格格式
     */
    private FieldStyle fieldStyle;

    private List<MappedField> children;


    protected Method picker;
    protected Method pusher;

    public MappedField() {

    }
    public MappedField(FieldProperty fieldProperty) {
        this.fieldProperty = fieldProperty;
        blankAble = true;
    }
    public MappedField(FieldProperty fieldProperty,FieldStyle fieldStyle) {
        this.fieldStyle = fieldStyle;
        this.fieldProperty = fieldProperty;
        blankAble = true;
    }

    public MappedField(FieldProperty fieldProperty,List<MappedField> children) {
        this.fieldProperty = fieldProperty;
        this.children = children;

    }

    public boolean isLeaf() {
        return getChildren() == null || getChildren().size() == 0;
    }

    @Override
    public boolean isBlankable() {
        return false;
    }
}
