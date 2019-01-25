package cn.chenhuanming.octopus.core.temp.field;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuangzf
 * @date 2019/1/21 19:53
 */
@Data
public abstract class AbstractField {

    private MappedField mappedField;

    private Method picker;
    /**
     * 单元格内容设置的反射method
     */
    private Method pusher;


}
