package cn.chenhuanming.octopus.core.temp.field;

import lombok.Data;

import java.util.List;

/**
 * @author zhuangzf
 * @date 2019/1/21 19:26
 */
@Data
public class MappedField  {

    /**
     * excel单元格信息属性
     */
    private FieldProperty fieldProperty;
    /**
     * excel单元格格式
     */
    private FieldStyle fieldStyle;

    private List<? extends MappedField> children;


}
