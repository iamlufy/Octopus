package cn.chenhuanming.octopus.core.temp.field.impl;

import cn.chenhuanming.octopus.core.read.ImportValidation;
import cn.chenhuanming.octopus.core.temp.field.Field;
import cn.chenhuanming.octopus.core.temp.field.FieldProperty;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 匹配配置文件的属性，只包含内容
 *
 * @author zhuangzf
 * @date 2019/1/21 19:26
 */
@Data
public class MappedField implements Field,ImportValidation {

    protected boolean blankable;

    protected List<String> options;

    protected Pattern regex;
    /**
     * excel单元格信息属性
     */
    protected FieldProperty fieldProperty;


    protected List<? extends MappedField> children;

    private Method pusher;


    public boolean isLeaf() {
        return getChildren() == null || getChildren().size() == 0;
    }
}
