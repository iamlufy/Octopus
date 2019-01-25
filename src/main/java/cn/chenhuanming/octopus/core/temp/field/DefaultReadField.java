package cn.chenhuanming.octopus.core.temp.field;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhuangzf
 * @date 2019/1/17 9:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DefaultReadField  extends AbstractField{
    /**
     * excel单元格信息属性
     */
    private FieldProperty fieldProperty;

}
